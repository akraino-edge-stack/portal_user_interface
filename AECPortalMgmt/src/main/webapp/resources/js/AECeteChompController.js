/* 
 * Copyright (c) 2018 AT&T Intellectual Property. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
angular.module('PortalManagement').controller('AECeteChompController', function ($scope, $http, $filter, filterFilter, $state, ngDialog, $controller, appContext,$interval,$rootScope) {
	$rootScope.chompData = [];
	var array = new Array();
	var data;
	$scope.createData = function(name, value) {
        var dataLatency = {
            name: "",
            value: ""
        };
        dataLatency.name = name;
        dataLatency.value = value;
        return dataLatency;
    }
	$scope.chompDataCall = function(){
	$http({
          method: 'GET',
          url: appContext + '/chomp/',
          headers: {
              'Content-Type': "application/json",
              'Accept': "application/json",
          }
      }).then(function(response) {
          if (response.status == 200) {
              $rootScope.chompData = response.data;
             //console.log($rootScope.chompData[0].latency);
             $scope.LatencyData = $rootScope.chompData[0].latency;
              data = [{
           	  x:  $scope.LatencyData ,
           	    type: "histogram",
           	 xbins:
           		 {
           		 size:1
           		 }
           	 
           	}];
              var layout = {
                	  height: 400,
                	  width: 500,
                	  yaxis:{
                	  title:'Count'
                	  },
                	  xaxis:{
                	  title:'Latency',
                	  range:[0, 8],
                	        
                	       		  
                	  }
                	  
                	};
                	Plotly.newPlot('tester', data, layout,{displayModeBar: false});
             var  count = {};
             $scope.LatencyData.forEach(function(i) { count[i] = (count[i]||0) + 1.0;});
             console.log(count);
             /*for(var i =0 ;i< $scope.LatencyData.length;i++){
            	$scope.exg = $scope.createData(i,$scope.LatencyData[i]);
            	array.push($scope.exg);
             }*/
             for (var element in count) {
            	  console.log(element + ' = ' + count[element]);
            	  $scope.exg = $scope.createData(element,count[element]);
              	array.push($scope.exg);
            	} 
             $scope.data = array;
             console.log($scope.data);
             /*$scope.data = [
                 {"name":"a","value":7},
                 {"name":"ab","value":7},
                 {"name":"abc","value":7},
                 {"name":"abd","value":7},
                 {"name":"abe","value":6}]; */
          } else {
          }
      }, function(error) {
      });
}
	$scope.chompDataCall();
	 $scope.options = {width: 500, height: 300, 'bar': 'aaa'};
     $scope.hovered = function(d){
         $scope.barValue = d;
         $scope.$apply();
     };
     $scope.barValue = 'None';
     var num = 25;
     //console.log(num);
    	});
 //console.log($scope.data[0].value);
     //$scope.data = [7.0, 7.0, 7.0, 7.0, 7.0, 6.0, 7.0, 6.0];
/*angular.module('PortalManagement').directive('histogram', ['$parse', '$window', function($parse, $window){
	return{
		restrict: "E", 
		replace: false,
		template: "<svg class='histogram-chart'></div>",
		link: function(scope, elem, attrs) {
			var exp = $parse(attrs.data);
			var d3 = $window.d3;
            /*
                Sortable barchart. Largely taken from: 
                http://bl.ocks.org/mbostock/3885705
            */ 
			// Aesthetic settings 
			/*var margin = {top: 20, right: 50, bottom: 20, left: 50},
			    width = 600 - margin.left - margin.right,
			    height = 300 - margin.top - margin.bottom, 
			    barColor = "steelblue", 
			    axisColor = "whitesmoke", 
			    axisLabelColor = "grey",
			    yText = "Latency(seconds)", 
			    xText = "Interval(5 Mins)";
			// Inputs to the d3 graph 
			var data = scope[attrs.data];
			// A formatter for counts.
			var formatCount = d3.format(",.0f");
			// Set the scale, separate the first bar by a bar width from y-axis
			var x = d3.scale.ordinal()
			    .rangeRoundBands([0, width], 0.1, 1);
			var z = d3.scale.ordinal()
		    .rangeRoundBands([0, width], 0.1, 1);
			var zAxis = d3.svg.axis()
		    .scale(z)
		    .orient("bottom");
			var y = d3.scale.linear()
			    .range([height, 0]);
			var xAxis = d3.svg.axis()
			    .scale(x)
			    .orient("bottom");
			var yAxis = d3.svg.axis()
			    .scale(y)
			    .orient("left")
			    .tickFormat(formatCount);
			// Initialize histogram 
			var svg = d3.select(".histogram-chart")
			    .attr("width", width + margin.left + margin.right)
			    .attr("height", height + margin.top + margin.bottom)
			  .append("g")
			    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			function drawAxis(){
				data.forEach(function(d) {
					d.value = +d.value;
				});
				x.domain(data.map(function(d) { return d.name; }));
				y.domain([0, d3.max(data, function(d) { return d.value; })]);
				// Draw x-axis 
				svg.append("g")
					.attr("class", "x-axis")
					.attr("transform", "translate(0," + height + ")")
					.call(zAxis)
					.append("text")
					.attr("y", 6)
					.attr("dy", "1.0em")
					.attr("x", width )
					.style("text-anchor", "middle")
					.style("fill", axisLabelColor)
					.text(xText);
				// Draw y-axis 
				svg.append("g")
					.attr("class", "y-axis")
					.call(yAxis)
					.append("text")
					.attr("transform", "rotate(-90)")
					.attr("y", 6)
					.attr("dy", "-2.5em")
					.style("text-anchor", "end")
					.style("fill", axisLabelColor)
					.text(yText);
				// Change axis color 
				d3.selectAll("path").attr("fill", axisColor);
			}
			function updateAxis(){
				data.forEach(function(d) {
					d.value = +d.value;
				});
				x.domain(data.map(function(d) { return d.name; }));
				y.domain([0, d3.max(data, function(d) { return d.value; })]);
				svg.selectAll("g.y-axis").call(yAxis);
				svg.selectAll("g.x-axis").call(xAxis);
			}
			function updateHistogram(){
				// Redefine scale and update axis 
                if (!d3.select('g.y-axis').node()){
                    drawAxis();
                } else {                
                    updateAxis(); 
                }
				// Select 
                var bar = svg.selectAll(".barInfo").data(data);
                var bEnter = bar.enter().append("g")
					.attr("class", "barInfo");
                bEnter.append("rect")
					.attr("class", "bar");
                bEnter.append("text")
                    .attr("class","numberLabel");
                // update
                //console.log(bar.data());
                bar.select("rect")
                    .attr("x", function(d){ return x(d.name) })
					.attr("width", 46)
					.attr("y", function(d){ console.log(d); return y(d.value) })
					.attr("height", function(d) { return height - y(d.value); })
					.attr("fill", barColor);
                bar.select("text")
                    .attr("y", function(d){ return y(d.value) })
					.attr("x", function(d){ return x(d.name) })
					.attr("dy", "-1px")
					.attr("dx", 5 )
					.attr("text-anchor", "middle")
					.attr("class", "numberLabel")
					.text(function(d) { return formatCount(d.value); });               
			}
			// Render the graph when data is changed. 
			scope.$watchCollection(exp, function(newCollection, oldCollection, scope) {
				data = newCollection;
			 	updateHistogram();
			 });
			var sortByVal = false; 
			d3.select(".sortButton").on("click", function(){
				sortByVal = !sortByVal;
				change(sortByVal);
			});
			var sortTimeout = setTimeout(1000);
			function change(sortByVal) {
				clearTimeout(sortTimeout);
				// Copy-on-write since tweens are evaluated after a delay.
				var x0 = x.domain(data.sort(sortByVal
				    ? function(a, b) { return b.value - a.value; }
				    : function(a, b) { return d3.ascending(a.name, b.name); })
				    .map(function(d) { return d.name; }))
				    .copy();
				var transition = svg.transition().duration(750),
				    delay = function(d, i) { return i * 50; };
				transition.selectAll([".bar", ".numberLabel"])
				    .delay(delay)
				    .attr("x", function(d) { return x0(d.name); });
				transition.select(".x-axis")
				    .call(xAxis)
				  .selectAll("g")
				    .delay(delay);
				}
			}
	};
}]);*/