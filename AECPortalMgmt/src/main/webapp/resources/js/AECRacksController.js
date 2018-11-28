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

var RackArray = new Array();

angular.module('PortalManagement').controller('AECRacksController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller, appContext) {
RackArray =  
	 [{
    "name": "Rack-3",
    "type": "control"
},
{
    "name": "Rack-4",
    "type": "compute"
},
{
    "name": "Rack-5",
    "type": "compute"
},
{
    "name": "Rack-6",
    "type": "compute"
}]
$scope.createBlueprintObject = function(name, pod) {
    var blueprint = {
        name: "",
        pod: ""
    };
    blueprint.name = name;
    blueprint.pod = pod;
    return blueprint;
}
$scope.createPodObject = function(name, rack) {
    var pod = {
        name: "",
        racks: new Array()
    };
    pod.name = name;
    pod.racks = rack;
    return pod;
}
$scope.createRackObject = function(name, rackType, node) {
    var rack = {
        name: "",
        type: "",
        nodes: new Array()
    };
    rack.name = name;
    rack.type = rackType;
    rack.nodes = node;
    return rack;
}
$scope.createNodeObject = function(number, name, nodeType, software, hardware) {
    var node = {
        number: "",
        name: "",
        type: "",
        softwares: new Array(),
        hardwares: new Array()
    };
    node.number = number;
    node.name = name;
    node.type = nodeType;
    node.softwares = software;
    node.hardwares = hardware;
    return node;
}
$scope.createHardwareObject = function(name, type) {
    var hardware = {
        name: "",
        type: ""
    };
    hardware.name = name;
    hardware.type = type;
    return hardware;
}
$scope.createSoftwareObject = function(name, type, version) {
    var software = {
        name: "",
        type: "",
        version: ""
    };
    software.name = name;
    software.type = type;
    software.version = version;
    return software;
}
$scope.rackList = RackArray;
$scope.rackSelected = function(rackName,index){
	 
	    	$scope.showRack = true;
	    	//$scope.blueprintName = blueprintName;
	    	$scope.selectedRow = index;
	    	$scope.rackName = rackName;
	    	var currentRack = RackArray.filter(function(element){
	            return element.name === $scope.rackName;
	        });
	    	
	    	if(currentRack[0].nodes == undefined){
				/*var currRackcheck = currentPod[0].racks.find(function(element){
		             return element.name ===  currentPod[0].racks[j].name;
		          });*/
					 
	    					 var Nodes = new Array();
	    					 for(var i = 0 ;i<52;i++){
	    			    	     var newNode =$scope.createNodeObject(i,"", "",[],[]);
	    			   	   		 Nodes.push(newNode);
	    			   	   		 $scope.nodeList = Nodes;
	    			   	   	 } 
	    					 
	    		         	// var newRack = $scope.createRackObject(currentRack[0].name, currentRack[0].type, Nodes);
	    		         	//RackArray.push(newRack); 
	    		              //$scope.rackList = Racks;
	    		             // console.log(Racks);
	    		         	 //var newPod = $scope.createPodObject($scope.selectPodName.name, $scope.rackList);
	    		 	         //var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
	    		              //currentBlueprint.pod.racks.push(Racks); 
	    					 currentRack[0].nodes = Nodes;
	    				 }
	    			 
	    	
	    			 
	    	$scope.nodeList= currentRack[0].nodes;
	    	
			 console.log($scope.nodeList);
	    	
	    		 
			 
	
}
});
angular.module('PortalManagement').controller(
		  'myPopoverCtrl1', function($scope,$http, ngDialog) {
			  
		      // query popover
		      $scope.myPopover = {
              
		        isOpen: false,

		        templateUrl: 'myPopoverTemplate.html',
              
		        open: function open(type,name) {
		        	
		        	if(type == ""){
		        		$scope.myPopover.isOpen = false;
		        		ngDialog.open({
		    	            scope: $scope,
		    	            template: 'Node.html',
		    	            closeByDocument: false,
		    	            controller: 'addNodeController',
		    	            appendClassName: 'ngdialog-custom',
		    	            width: '850px'
		    	        });
		        
		         
		        }
		        	else{
		        		 $scope.myPopover.isOpen = true;
				          $scope.myPopover.nodeName = name;
				          $scope.myPopover.nodeType = type;
		        		
		        	}
		        },

		        close: function close() {
		          $scope.myPopover.isOpen = false;
		        }
		      };

		    });
angular.module('PortalManagement').controller(
		  'addNodeController', function($scope,$http, ngDialog) {});
			  
		  
