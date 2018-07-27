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
var Blueprints = new Array();
var displayNodes = new Array();
angular.module('PortalManagement').controller('AECSiteBuildController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller,hostUrl) {
	Blueprints = [
 	   {
 		      "name":"Rover",
 		      "pod":{
 		         "name":"RoverPod",
 		         "racks":[
 		            {
 		               "name":"SingleNode",
 		               "type":"Control,Compute,Storage,Network",
 		               "nodes":[
 		                  {
 		                     "name":"SingleNodeServer",
 		                     "type":"Control,Compute,Storage,Network",
 		                     "softwares":[
 		                        {
 		                           "name":"Container",
 		                           "type":"Kubernetes",
 		                           "version":"v1.10.2"
 		                        },
 		                        {
 		                           "name":"Network",
 		                           "type":"OVS",
 		                           "version":"OpenVSwitch"
 		                        },
 		                        {
 		                           "name":"OS",
 		                           "type":"Ubuntu",
 		                           "version":"16.04"
 		                        }
 		                     ],
 		                     "hardwares":[
 		                        {
 		                           "name":"Dell",
 		                           "type":"Dell 45"
 		                        }
 		                     ],
 		                     "$$hashKey":"object:12"
 		                  }
 		               ]
 		            }
 		         ]
 		      },
 		      "$$hashKey":"object:10"
 		   }
 		]//response.data;
 $scope.BlueprintTable = Blueprints;
	 $scope.podList = [{"name":"pod1",
			"racks":[{"name":"rack1",
				"type":"compute"}]
			  },
			  {"name":"pod2",
				"racks":[{"name":"rack2",
				"type":"compute"}]
			  }
			]
	    $scope.showRackTable = false;
	    $scope.showNode = false;
	    $scope.showHardware = false;
	    $scope.showSoftware = false;
	    $scope.showNodeTable = false;
	    $scope.showHardwareTable = false;
	    $scope.showSoftwareTable = false;
	    $scope.createPod = false;
	    $scope.showSoftwareForm = false;
	    $scope.tabs = [{
            title: 'Hardware',
            url: 'hardware.html'
        }, {
            title: 'Software',
            url: 'software.html'
    }];
    $scope.currentTab = 'hardware.html';
    $scope.onClickTab = function (tab) {
        $scope.currentTab = tab.url;
    }
    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    }
	    $scope.selectedPod = function() {
	        if ($scope.selectPodName.name == null) {
	        } else {
	            var currentPod = $scope.podList.find(function(element) {
	                return element.name === $scope.selectPodName.name;
	            });
	            $scope.rackSelectList = currentPod.racks;
	            console.log($scope.rackList);
	            //$scope.showRackTable = true;
	        }
	    }
	    $scope.createBlueprint = function() {
	    	$scope.createPod = true;
	    	 $scope.showNode = false;
	 	    $scope.showHardware = false;
	 	    $scope.showSoftware = false;
	    	$scope.showBlueprintHeader = "Create Blueprints";
	    	$scope.createForm = true;
	    	$scope.showRackTable = false;
	    	 $scope.showSoftwareHardware = false;
	    	 $scope.showNodeTable = false;
	    	 $scope.showSoftwareTable = false;
	    	 $scope.blueprintName ="";
	    	 $scope.check = 0;
	    }
	    $scope.createNode = function(rackName, rackType) {
	        $scope.showNode = true;
	        $scope.showNodeForm = true;
	        $scope.rackNName = rackName;
	        $scope.rackNType = rackType;
	        $scope.nodeHeader = "Add Nodes to rack " +$scope.rackNName;
	        console.log($scope.rackNName);
	        if($scope.rackNName == undefined){
	        	$scope.showNode = false;
		        $scope.showNodeForm = false;
	        }
	    }
	    $scope.createNodeTable = function(rackName, rackType) {
	    	 $scope.nodeHeader = "Nodes";
	        $scope.showNode = true;
	        $scope.showNodeForm = true;
	        $scope.rackNName = rackName;
	        $scope.rackNType = rackType;
	        if($scope.check ==1){
	        	$scope.showNodeForm = false;
	        	$scope.showNodeTable = true;
	        var currentBlueprint = $scope.BlueprintTable.find(function(element) {
                return element.name === $scope.blueprintName;
            });
	        var currentRack = currentBlueprint.pod.racks.find(function(element){
	        	return element.name === $scope.rackNName;
	        });
	        $scope.nodeList = currentRack.nodes;
	        }
	    }
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
	    $scope.createNodeObject = function(name, nodeType, software, hardware) {
	    	var node = {
	                name: "",
	                type: "",
	                softwares: new Array(),
	                hardwares: new Array()
	            };
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
	    $scope.createSoftwareObject = function(name, type,version) {
	    	 var software = {
	    	            name: "",
	    	            type: "",
	    	            version :""
	    	        };
	    	        software.name = name;
	    	        software.type = type;
	    	        software.version = version;
	    	        return software;
	    }
	    $scope.addNodes = function() {
	    	var currentBlueprint = Blueprints.find(function(element){
	            return element.name === $scope.blueprintName;
	        });
	    	if(currentBlueprint == undefined){
	    		var Nodes = new Array();
		         var newNode =$scope.createNodeObject($scope.nodeName, $scope.nodeType, [], []);
		         //newNode.hardwares.push(newHardware);
		         Nodes.push(newNode);
		         $scope.nodeList = Nodes;
		            $scope.showNodeTable = true;
		         console.log(Nodes);
		         var Racks = new Array();
		         var newRack = $scope.createRackObject($scope.rackNName, $scope.rackNType, Nodes);
		         Racks.push(newRack);
		         //console.log(newRack);
		         var newPod = $scope.createPodObject($scope.selectPodName.name, Racks);
		         //console.log(newPod);
		         var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
		         Blueprints.push(newBlueprint);
		         //console.log(Blueprints);
	    	}else{
	    		var currRack = currentBlueprint.pod.racks.find(function(element){
		             return element.name === $scope.rackNName;
		          });
	    		 if (currRack == undefined){
		             console.log("Rack  not found adding new Node and Rack");
		             var Nodes = new Array();
		             var newNode =$scope.createNodeObject($scope.nodeName, $scope.nodeType, [], []);
		             Nodes.push(newNode);
		             console.log(newNode);           
		             $scope.nodeList = Nodes;
			            $scope.showNodeTable = true;
		             //var Racks = new Array();
		             var newRack = $scope.createRackObject($scope.rackNName, $scope.rackNType, Nodes);
		             //Racks.push(newRack);
		             console.log(newRack);                      
		             currentBlueprint.pod.racks.push(Racks);
		             console.log(Blueprints);
		          }
	    		 else{
	    			 var newNode =$scope.createNodeObject($scope.nodeName, $scope.nodeType, [], []); 
	    			 currRack.nodes.push(newNode);
	    			 $scope.nodeList =  currRack.nodes;
			            $scope.showNodeTable = true;
		             console.log(Blueprints);
	    		 }
	    	}
	    }
	    $scope.addHardwareSoftware = function(NodeName,nodeType) {
	        $scope.showHardware = true;
	        $scope.showSoftware = true;
	        $scope.showSoftwareHardware = true;
	        $scope.nodeHName = NodeName;
	        $scope.nodeHType = nodeType;
	        $scope.showSoftwareForm = true;
	        $scope.showHardwareForm = true;
	        $scope.showHardwareTable = false;
	        $scope.headerdSoftware = true;
	        $scope.headerdHardware = true;
	        if($scope.check ==1){
	        	$scope.showSoftwareForm = false;
	        	$scope.showSoftwareTable = true;
	        	$scope.showHardwareTable = true;
	        	$scope.showHardwareForm = false;
	        	 $scope.headerdSoftware = false;
	 	        $scope.headerdHardware = false;
	        var currentBlueprint = $scope.BlueprintTable.find(function(element) {
                return element.name === $scope.blueprintName;
            });
	        var currentRack = currentBlueprint.pod.racks.find(function(element){
	        	return element.name === $scope.rackNName;
	        });
	        var currentNode = currentRack.nodes.find(function(element){
	        	return element.name === $scope.nodeHName;
	        });
	        $scope.HardwareName = currentNode.hardwares.name;
	        $scope.hardwareList = currentNode.hardwares;
	        $scope.softwareList = currentNode.softwares;
	      console.log($scope.hardwareList);
	        }
	    }
	    $scope.addHardware = function(){
	       console.log($scope.HardwareName);
	        var currentBlueprint = Blueprints.find(function(element){
	            return element.name === $scope.blueprintName;
	        });
	        if(currentBlueprint == undefined){
	         var newHardware = $scope.createHardwareObject($scope.HardwareName,$scope.hardwareType);
	         var hardwares = new Array();
	         hardwares.push(newHardware);
	         $scope.hardwareList = hardwares;
	         $scope.showHardwareTable = true;
	         var Nodes = new Array();
	         var newNode =$scope.createNodeObject($scope.nodeHName, $scope.nodeHType, [], []);
	         newNode.hardwares.push(newHardware);
	         Nodes.push(newNode);
	         console.log(newNode);
	         var Racks = new Array();
	         var newRack = $scope.createRackObject($scope.rackNName, $scope.rackNType, Nodes);
	         Racks.push(newRack);
	         console.log(newRack);
	         var newPod = $scope.createPodObject($scope.selectPodName.name, Racks);
	         console.log(newPod);
	         var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
	         Blueprints.push(newBlueprint);
	         console.log(Blueprints);
	        }
	        else{
	         var newHardware = $scope.createHardwareObject($scope.HardwareName,$scope.hardwareType);
	         var currRack = currentBlueprint.pod.racks.find(function(element){
	             return element.name === $scope.rackNName;
	          });
	          if (currRack == undefined){
	             console.log("Rack  not found adding new Node and Rack");
	             var Nodes = new Array();
	             var newNode =$scope.createNodeObject($scope.nodeHName, $scope.nodeHType, [], []);
	             newNode.hardwares.push(newHardware);
	             $scope.hardwareList = newNode.hardwares;
		         $scope.showHardwareTable = true;
	             Nodes.push(newNode);
	             console.log(newNode);           
	             //var Racks = new Array();
	             var newRack = $scope.createRackObject($scope.rackNName, $scope.rackNType, Nodes);
	             //Racks.push(newRack);
	             console.log(newRack);                      
	             currentBlueprint.pod.racks.push(Racks);
	             console.log(Blueprints);
	          } else{
	             console.log("Rack Found, Finding Node to add hardware" );
	             var currNode = currRack.nodes.find(function(element){
	                 return element.name === $scope.nodeHName;
	             });
	             if (currNode == undefined){
	                 console.log("Node Not Found, Creating new node");
	                 var newNode =$scope.createNodeObject($scope.nodeHName, $scope.nodeHType, [], []);
	                 newNode.hardwares.push(newHardware);
	                 $scope.hardwareList = newNode.hardwares;
			         $scope.showHardwareTable = true;
	                 currRack.nodes.push(newNode);
	                 console.log(currentBlueprint);
	             }else {
	                 console.log("Node Found, Adding hardware to node")
	                 currNode.hardwares.push(newHardware);
	                 $scope.hardwareList =currNode.hardwares;
			         $scope.showHardwareTable = true;
	                 console.log(currentBlueprint);
	             }
	          }
	        console.log($scope.hardwareList);
	    }
	    }
	    $scope.addSoftware = function(){
	    	$scope.saveBlueprint = true;
	    	var currentBlueprint = Blueprints.find(function(element){
	            return element.name === $scope.blueprintName;
	        });
	        if(currentBlueprint == undefined){
	         var newSoftware = $scope.createSoftwareObject($scope.softwareName,$scope.softwareType,$scope.softwareVersion);
	         var softwares = new Array();
	         softwares.push(newSoftware);
	         $scope.softwareList = softwares;
	         $scope.showSoftwareTable = true;
	         var Nodes = new Array();
	         var newNode =$scope.createNodeObject($scope.nodeHName, $scope.nodeHType, [], []);
	         newNode.softwares.push(newSoftware);
	         Nodes.push(newNode);
	         console.log(newNode);
	         var Racks = new Array();
	         var newRack = $scope.createRackObject($scope.rackNName, $scope.rackNType, Nodes);
	         Racks.push(newRack);
	         console.log(newRack);
	         var newPod = $scope.createPodObject($scope.selectPodName.name, Racks);
	         console.log(newPod);
	         var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
	         Blueprints.push(newBlueprint);
	         console.log(Blueprints);
	        }
	        else{
	        	 var newSoftware = $scope.createSoftwareObject($scope.softwareName,$scope.softwareType,$scope.softwareVersion);
	         var currRack = currentBlueprint.pod.racks.find(function(element){
	             return element.name === $scope.rackNName;
	          });
	          if (currRack == undefined){
	             console.log("Rack  not found adding new Node and Rack");
	             var Nodes = new Array();
	             var newNode =$scope.createNodeObject($scope.nodeHName, $scope.nodeHType, [], []);
	             var newNode =$scope.createNodeObject($scope.nodeHName, $scope.nodeHType, [], []);
		         newNode.softwares.push(newSoftware);
		         Nodes.push(newNode);
		         $scope.softwareList = newNode.softwares;
		         $scope.showSoftwareTable = true;
		         console.log(newNode);           
	             //var Racks = new Array();
	             var newRack = $scope.createRackObject($scope.rackNName, $scope.rackNType, Nodes);
	             //Racks.push(newRack);
	             console.log(newRack);                      
	             currentBlueprint.pod.racks.push(Racks);
	             console.log(Blueprints);
	          } else{
	             console.log("Rack Found, Finding Node to add hardware" );
	             var currNode = currRack.nodes.find(function(element){
	                 return element.name === $scope.nodeHName;
	             });
	             if (currNode == undefined){
	                 console.log("Node Not Found, Creating new node");
	                 var newNode =$scope.createNodeObject($scope.nodeHName, $scope.nodeHType, [], []);
	                 newNode.softwares.push(newSoftware);
	                 $scope.softwareList = newNode.softwares;
			         $scope.showSoftwareTable = true;
	                 currRack.nodes.push(newNode);
	                 console.log(currentBlueprint);
	             }else {
	                 console.log("Node Found, Adding hardware to node")
	                 currNode.softwares.push(newSoftware);
	                 $scope.softwareList = currNode.softwares;
			         $scope.showSoftwareTable = true;
	                 console.log(currentBlueprint);
	             }
	          }
	        console.log(currentBlueprint);
	    }
	    }
	    $scope.BlueprintSelected = function(blueprintName,pod)
	    {
	    	$scope.showBlueprintHeader = "Blueprint Details";
	    	$scope.showNode = false;
	        $scope.showNodeForm = false;
	        $scope.showSoftwareHardware = false;
	    	$scope.createForm = false;
	        $scope.blueprintName = blueprintName;
	        $scope.selectPodName = $scope.pod;
	        $scope.showNodeTable = false;
	        $scope.check = 1;
	        $scope.createPod = true;
	        $scope.rackSelectList = [];
	        var currentBlueprint = $scope.BlueprintTable.find(function(element) {
	                    return element.name === $scope.blueprintName;
	                });
	                $scope.rackList = currentBlueprint.pod.racks;
	                 $scope.showRackTable = true;
	    }
});