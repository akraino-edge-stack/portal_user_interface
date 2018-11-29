angular.module('PortalManagement').controller('AECeteChompController', function AppCtrl($scope, $http, $filter, filterFilter, $state, ngDialog, $controller, appContext,$interval,$rootScope) {
	
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
             console.log($rootScope.chompData[0].latency);
             $scope.data = $rootScope.chompData[0].latency;
          } else {

          }
      }, function(error) {

      });
	 $scope.options = {width: 500, height: 300, 'bar': 'aaa'};
     
     $scope.hovered = function(d){
         $scope.barValue = d;
         $scope.$apply();
     };
     $scope.barValue = 'None';
 })
angular.module('PortalManagement').directive('barChart', function($rootScope){
     var chart = d3.custom.barChart();
     return {
         restrict: 'E',
         replace: true,
         template: '<div class="chart"></div>',
         scope:{
             height: '=height',
             data: '=data',
             hovered: '&hovered'
         },
         link: function(scope, element, attrs) {
             var chartEl = d3.select(element[0]);
             chart.on('customHover', function(d, i){
                 scope.hovered({args:d});
             });

             scope.$watch('data', function (newVal, oldVal) {
                 chartEl.datum(newVal).call(chart);
             });

             scope.$watch('height', function(d, i){
                 chartEl.call(chart.height(scope.height));
             })
         }
     }
 })
 angular.module('PortalManagement').directive('chartForm', function($rootScope){
     return {
         restrict: 'E',
         replace: true,
         controller: function AppCtrl ($scope) {
             $scope.update = function(d, i){ $scope.data = $rootScope.chompData[0].latency; };
             function randomData(){
                 return $rootScope.chompData[0].latency;
             }
         },
         template: '<div class="form">' +
                 ' <h3>POD Create Success Rate</h3>' +
                 '' +
                
                 '<br />Latency Rate: {{barValue}}</div>'
     }
});

//'<br /><button ng-click="">Update Data</button>' +
