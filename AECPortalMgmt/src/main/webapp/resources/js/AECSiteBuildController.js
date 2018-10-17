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
angular.module('PortalManagement').controller('AECSiteBuildController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller, appContext) {
    $controller('commonController', {
        $scope: $scope
    });
    $scope.addNodeMenu = false;
    $scope.showRackTable = false;
    $scope.showNode = false;
    $scope.showHardware = false;
    $scope.showSoftware = false;
    $scope.showNodeTable = false;
    $scope.showHardwareTable = false;
    $scope.showSoftwareTable = false;
    $scope.createPod = false;
    $scope.showSoftwareForm = false;
    $scope.showNodeHeader = false;
    $scope.tabs = [{
        title: 'Hardware',
        url: 'hardware.html'
    }, {
        title: 'Software',
        url: 'software.html'
    }];
    $scope.currentTab = 'hardware.html';
    $scope.onClickTab = function(tab) {
        $scope.currentTab = tab.url;
    }
    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    }
    $scope.myStyle= {
            "width":"200px"
            
            
        }
    Blueprints = [{
        "name": "Rover",
        "pod": {
            "name": "RoverPod",
            "racks": [{
                "name": "SingleNode",
                "type": "Control,Compute,Storage,Network",
                "nodes": [{
                    "name": "SingleNodeServer",
                    "type": "Control,Compute,Storage,Network",
                    "softwares": [{
                            "name": "Container",
                            "type": "Kubernetes",
                            "version": "v1.10.2"
                        },
                        {
                            "name": "Network",
                            "type": "OVS",
                            "version": "OpenVSwitch"
                        },
                        {
                            "name": "OS",
                            "type": "Ubuntu",
                            "version": "16.04"
                        }
                    ],
                    "hardwares": [{
                        "name": "Dell",
                        "type": "Dell 45"
                    }],
                    "$$hashKey": "object:12"
                }]
            }]
        },
        
    },{
        "name": "Unicycle",
        "pod": {
            "name": "UnicyclePod",
            "racks": [{
                "name": "multiNode",
                "type": "Control,Compute,Storage,Network",
                "nodes": [{
                    "name": "multiNodeServer",
                    "type": "Control,Compute,Storage,Network",
                    "softwares": [{
                    	"name": "Container",
                        "type": "Kubernetes",
                        "version": "v1.10.2"
                    },
                    {
                        "name": "Network",
                        "type": "OVS",
                        "version": "OpenVSwitch"
                    },
                    {
                        "name": "OS",
                        "type": "Ubuntu",
                        "version": "16.04"
                    },
                    {
                        "name": "Container",
                        "type": "Calico",
                        "version": "2.6.5"
                    },
                    {
                        "name": "Storage",
                        "type": "Ceph",
                        "version": "10.2.7"
                    }
                    
                    ],
                    "hardwares": [{
                        "name": "HP",
                        "type": "Gen 10"
                    },
                    {
                        "name": "Dell",
                        "type": "Dell 45"
                    }],
                    
                }]
            }]
        },
       
    }] //response.data;
    $scope.BlueprintTable = Blueprints;
    $scope.podList = [{
            "name": "pod1",
            "racks": [{
                    "name": "Rack1",
                    "type": "compute"
                },
                {
                    "name": "Rack2",
                    "type": "compute"
                },
                {
                    "name": "Rack3",
                    "type": "compute"
                },
                {
                    "name": "Rack4",
                    "type": "compute"
                }
            ]
        },
        {
            "name": "pod2",
            "racks": [{
                "name": "rack2",
                "type": "compute"
            }]
        }
    ]

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
    $scope.BlueprintSelected = function(blueprintName,pod,index)
    {
    	$scope.saveBlueprint = false;
    	$scope.selectedRow = index;
    	$scope.showBlueprintHeader = "Blueprint Details";
    	$scope.showNode = false;
    	$scope.showNode1 = false;
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
    $scope.selectedPod = function() {
        if ($scope.selectPodName.name != null)
            var currentPod = $scope.podList.find(function(element) {
                return element.name === $scope.selectPodName.name;
            });
            //var newRack = $scope.createRackObject("All Racks", "", "");
            $scope.rackSelectList = currentPod.racks;
            var rackList = new Array();
            
            for (var i=0;i<$scope.rackSelectList.length;i++){
            	rackList.push($scope.rackSelectList[i].name);
            		
            	
            }
            rackList.push("All Racks");
            $scope.rackSelect = rackList;
            /*console.log( $scope.rackSelectList);
            var currRacks = currentPod.racks;
            
            //currRacks.push(newRack)
            $scope.rackSelect =  currRacks;
            $scope.rackSelect.push(newRack);
            console.log($scope.rackSelect);*/
            //$scope.showRackTable = true;
            
    }
    $scope.createBlueprint = function() {
    	$scope.check ==0;
    	$scope.saveBlueprint = false;
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
    	 $scope.showNode1 = false;
    }
    $scope.createNode = function(rackName) {
        $scope.showNode = true;
        $scope.showNodeHeader = true;
        $scope.showNodeForm = true;
        $scope.rackNName = rackName;
        //$scope.rackNType = rackType;
        $scope.nodeHeader = "Add Nodes to rack " +$scope.rackNName;
        console.log($scope.rackNName);
        var currentBlueprint = Blueprints.find(function(element){
            return element.name === $scope.blueprintName;
        });
        if(currentBlueprint == undefined){
        if($scope.rackNName == "All Racks"){
        	
        	console.log($scope.rackSelectList);
        	var Racks = new Array();
        	 for ( var j =0 ; j<   $scope.rackSelectList.length; j++){
 	        	//if rack is empty initialize it with empty Racks
        		 var Nodes = new Array();
 	            for(var i = 0;i<52;i++){
 	    	     var newNode =$scope.createNodeObject(i,"", "",[],[]);
 	    	   
 	   	   		 Nodes.push(newNode);
 	   	   		 //$scope.nodeList = Nodes;
 	   	   
            
             
 	   	   	 }	 
 	           var newRack = $scope.createRackObject($scope.rackSelectList[j].name, $scope.rackSelectList[j].type, Nodes);
	             Racks.push(newRack);
	             $scope.rackList = Racks;
 	        }
        	 var newPod = $scope.createPodObject($scope.selectPodName.name, $scope.rackList);
	         var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
	         Blueprints.push(newBlueprint);
         console.log($scope.rackList);
        }
        else{
        	var rackArray = new Array();
        	var nodeArray = new Array();
        	 for(var i = 0;i<52;i++){
	    	     var newNodeArray =$scope.createNodeObject(i,"", "",[],[]);
	   	   		 nodeArray.push(newNodeArray);
	   	   		 $scope.nodeList = nodeArray;
	   	   	 }
        	 var newRackArray = $scope.createRackObject($scope.rackNName, "", nodeArray);
        	 rackArray.push(newRackArray);
             $scope.rackList = rackArray;
        	 var newPodArray = $scope.createPodObject($scope.selectPodName.name, $scope.rackList);
	         var newBlueprintArray = $scope.createBlueprintObject($scope.blueprintName, newPodArray);   
	         Blueprints.push(newBlueprintArray);
        }
        }
        else{
        	var currRack = currentBlueprint.pod.racks.find(function(element){
	             return element.name === $scope.rackNName;
	          });
        	
        	 if (currRack == undefined){
        		 if($scope.rackNName == "All Racks"){
        			 for ( var j =0 ; j<   $scope.rackSelectList.length; j++){
        				 var currRackcheck = currentBlueprint.pod.racks.find(function(element){
        		             return element.name ===  $scope.rackSelectList[j].name;
        		          });
        				 if(currRackcheck == undefined){
        					 var nodesArr = new Array();
        					 for(var i = 0 ;i<52;i++){
        			    	     var newNodeArr =$scope.createNodeObject(i,"", "",[],[]);
        			   	   		 nodesArr.push(newNodeArr);
        			   	   		 $scope.nodeList = nodesArr;
        			   	   	 } 
        					 var newRackArr = $scope.createRackObject($scope.rackSelectList[j].name, $scope.rackSelectList[j].type, nodesArr);
        					 currentBlueprint.pod.racks.push(newRackArr);
        				 }	
        			 }
        			 $scope.rackList= currentBlueprint.pod.racks;
        			 
        		 }else{
             	var nodeStr = new Array();
             	 for(var i = 0;i<52;i++){
     	    	     var newNodeStr =$scope.createNodeObject(i,"", "",[],[]);
     	   	   		 nodeStr.push(newNodeStr);
     	   	   		 $scope.nodeList = nodeStr;
     	   	   	 }
             	var rackStr = new Array();
             	 var newRackStr = $scope.createRackObject($scope.rackNName, "", nodeStr);
             	rackStr.push(newRackStr);
                  $scope.rackList = rackStr;
             	 //var newPod = $scope.createPodObject($scope.selectPodName.name, $scope.rackList);
     	         //var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
                  currentBlueprint.pod.racks.push(rackStr);
        		 }
        		 
        	 }
        	 else{
        		 var rackData = new Array();
        		 rackData.push(currRack);
        		 console.log(currRack);
        		 $scope.rackList = rackData;
        	 }
        }
    }
    $scope.addNodeTable = function(rackNumber,rackName){
    	$scope.addNodeMenu = true;
    	$scope.rackNName = rackName;
    	$scope.nodeName ="";
    	$scope.nodeType = "";
    	$scope.rackNumber = rackNumber;
    	$scope.idSelectedVote = rackNumber;
       
       
    	
    }
    $scope.addNodes = function() {
    	if($scope.nodeName != "" && $scope.nodeType !=""){
    	var currentBlueprint = Blueprints.find(function(element){
            return element.name === $scope.blueprintName;
        });
    	var currRack = currentBlueprint.pod.racks.find(function(element){
            return element.name === $scope.rackNName;
         }); 
    	var newNodeData =$scope.createNodeObject($scope.rackNumber,$scope.nodeName, $scope.nodeType, [], []); 
		 currRack.nodes[$scope.rackNumber] =newNodeData;
		 $scope.rackList = currentBlueprint.pod.racks;
		 console.log( $scope.rackList);
		 $scope.nodeList =  currRack.nodes;
           // $scope.showNodeTable = true;
         console.log(Blueprints);
         $scope.addNodeMenu = false;
         $scope.idSelectedVote = null;
    	}
    }
    $scope.cancel = function(){
    	$scope.addNodeMenu = false;
    	$scope.idSelectedVote = null;
    }
    $scope.idSelectedVote = null;
    $scope.setSelected = function(idSelectedVote,rackName) {
       $scope.idSelectedVote = idSelectedVote;
       $scope.rackNumber = idSelectedVote;
       $scope.addNodeMenu = true;
       $scope.rackNName = rackName;
    }
    $scope.hoverIn = function(name){
    	if(name == ""){
        this.hoverEdit = true;
    	}
    	
    };

    $scope.hoverOut = function(){
        this.hoverEdit = false;
    };
    $scope.addHardwareSoftware = function(NodeName,nodeType,index) {
    	
        $scope.showHardware = true;
        $scope.showSoftware = true;
        $scope.showSoftwareHardware = true;
        $scope.nodeHName = NodeName;
        $scope.nodeHType = nodeType;
        $scope.showSoftwareForm = true;
        $scope.showHardwareForm = true;
        $scope.showHardwareTable = false;
        $scope.headerdSoftware = true;
        $scope.headerHardware = true;
        $scope.selectedNodeRow = index;
        if($scope.check ==1){
        	$scope.selectedNodeRow = index;
        	$scope.showSoftwareForm = false;
        	$scope.showSoftwareTable = true;
        	$scope.showHardwareTable = true;
        	$scope.showHardwareForm = false;
        	 $scope.headerdSoftware = false;
 	        $scope.headerHardware = false;
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
    $scope.addSoftwareHardwrae = function(NodeName){
    	//$scope.addSoftwareHardwareMenu = true;
    	$scope.nodeName = NodeName;
    	$scope.myVar = "hardware";
    	//$scope.showHardwareForm = true;
    	
    	}
    $scope.newValue = function(value) {
        if(value == "hardware"){
        	$scope.showHardwareForm = true;
        	$scope.showSoftwareForm = false;        }
        else{
        	$scope.showHardwareForm = false;
        	$scope.showSoftwareForm = true;
        }
     }
    $scope.addHardware = function(){
    	$scope.showHardwareTable = true;
    }
    
    $scope.createNodeTable = function(rackName, rackType,index) {
    	$scope.selectedRackRow = index;
    	$scope.showNodeHeader = false;
        $scope.showNode1 = true;
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
       
       
});