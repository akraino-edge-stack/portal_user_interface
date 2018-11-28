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
var Pods = new Array();
var Racks = new Array();
var abc;

angular.module('PortalManagement').controller('AECSiteBuildController', function($scope, $state,$http, $filter, filterFilter, $state, ngDialog, $controller, appContext,$base64) {
	abc = $state.params.siteInfo;
	if(abc != null){
		
		ngDialog.open({
            scope: $scope,
            template: 'InputFile.html',
            closeByDocument: false,
            controller: 'popUpaddnewRackController',
            appendClassName: 'ngdialog-custom',
            width: '1200px',
            height: '650px',
            showClose: false 
        });
	}
	console.log(abc);
	$scope.tokenId = localStorage.getItem("tokenId");
	
	$controller('commonController', {
        $scope: $scope
    });
    $scope.currentblueprintPage = 1;
    $scope.showPopover = function() {
        $scope.popoverIsVisible = true;
        console.log("hi");
    };

    $scope.hidePopover = function() {
        $scope.popoverIsVisible = false;
    };
    $scope.podSelect = true;
    $scope.deletePod = true;
    $scope.showPod = false;
    /*$scope.addNodeMenu = false;
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
            
            
        }*/
    Blueprints = [{
            "name": "Rover",
            "pod": {
                "name": "RoverPod",
                "racks": [{
                    "name": "SingleNode",
                    /*"type": "Control,Compute,Storage,Network",
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
                    }]*/
                }]
            },
            "$$hashKey": "object:10"
        },
        {
            "name": "Unicycle",
            "pod": {
                "name": "Pod-for-Uni",
                "racks": [{

                }]
            },


        },
        {
            "name": "ARVR1",
            "pod": {
                "name": "ARVR-Pod-1",
                "racks": [{

                }]
            },



        },
        {
            "name": "Crusier",
            "pod": {
                "name": "C-Pod-V1",
                "racks": [{

                }]
            },
        }

    ] //response.data;
    $scope.BlueprintTable = Blueprints;
    $scope.totalBlueprint = $scope.BlueprintTable.length;
    $scope.blueprintPerPage = 4;
    $scope.blueprintPaginate = function(value) {
        var begin, end, index;
        begin = ($scope.currentblueprintPage - 1) * $scope.blueprintPerPage;
        end = begin + $scope.blueprintPerPage;
        index = $scope.BlueprintTable.indexOf(value);
        return (begin <= index && index < end);
    };
    /*Pods = [{
            "name": "pod1",
            "racks": [{
                "name": "Rack-1",
                "type": "compute"
            }]
        },
        {
            "name": "pod-2",
            "racks": [{
                    "name": "Rack-2",
                    "type": "compute"
                },
                {
                    "name": "Rack-7",
                    "type": "compute"
                }
            ]
        },
        {
            "name": "pod-3",
            "racks": [{
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
                }
            ]
        }

    ]*/
    $scope.podDetails = function(){
    $http({
        method: 'GET',
        url: appContext + '/pod/',
      
        headers: {
            'Content-Type': "application/json",
            'Accept': "application/json",
            'tokenId' : $scope.tokenId
        }

    }).then(function(response) {
        if (response.status == 200) {
        	
        	 $scope.podList = response.data;
        	 $http({
                 method: 'GET',
                 url: appContext + '/edgeSites/0',
                 //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/0',
                 headers: {
                     'Content-Type': "application/json",
                     'Accept': "application/json",
                     'tokenId': $scope.tokenId
                 }
             }).then(function(response) {
                 console.log(response.data);
                 $scope.sites = response.data.sort(function(a, b) {
                     //note the minus before -cmp, for descending order
                     return $scope.cmp(
                         [$scope.cmp(a.region.regionName, b.region.regionName), $scope.cmp(a.edgeSiteName, b.edgeSiteName)],
                         [$scope.cmp(b.region.regionName, a.region.regionName), $scope.cmp(b.edgeSiteName, a.edgeSiteName)]
                     );
                 });
                 $scope.sitemap ={};
            	 $scope.sites.forEach(function(site) {$scope.sitemap[site.edgeSiteId] = site;});
            	 console.log($scope.sitemap);
            	 $scope.podList.forEach(function(pod) {
            		 pod.site =$scope.sitemap[pod.siteId];
            		});
            	 console.log( $scope.podList);


             }, function(error) {
                 $scope.errorHandle(error);
             });
        	
        	 $scope.totalPods = $scope.podList.length;
        	    $scope.podsPerPage = 4;
        	    $scope.currentpodPage = 1;
        	    $scope.podPaginate = function(value) {
        	        //console.log("calling");
        	        var begin, end, index;
        	        begin = ($scope.currentpodPage - 1) * $scope.podsPerPage;
        	        end = begin + $scope.podsPerPage;
        	        index = $scope.podList.indexOf(value);
        	        return (begin <= index && index < end);
        	    };
        	 Pods = response.data;
        } else {

        }
    }, function(error) {

    });
    }

    $scope.podDetails();


   
    /*$scope.podList = [{
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
    ]*/

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
    $scope.podSelected = function(pod, index) {
        $scope.showPod = true;
        //$scope.blueprintName = blueprintName;
        $scope.selectedRow = index;
        $scope.podName = pod;
        console.log("Hi" + pod);
        console.log(Pods);
        var currentPod = Pods.filter(function(element) {
            return element.podname === $scope.podName;
        });
        console.log(currentPod);
        var Racks = new Array();
        for (var j = 0; j < 1; j++) {
            /*var currRackcheck = currentPod[0].racks.find(function(element){
	             return element.name ===  currentPod[0].racks[j].name;
	          });*/

            var Nodes = new Array();
            for (var i = 0; i < 52; i++) {
            	if (i== 0 ){
            		var newNode = $scope.createNodeObject(i, "control", "control", [], []);
            	}
            	else if(i == 1 || i== 2){
            		var newNode = $scope.createNodeObject(i, "compute", "compute", [], []);
            		
            	}
            	else{
            		var newNode = $scope.createNodeObject(i, "", "", [], []);
            	}
                Nodes.push(newNode);
                $scope.nodeList = Nodes;
            }

            var newRack = $scope.createRackObject("Rack1", "Compute", Nodes);
            Racks.push(newRack);
            //$scope.rackList = Racks;
            console.log(Racks);
            //var newPod = $scope.createPodObject($scope.selectPodName.name, $scope.rackList);
            //var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
            //currentBlueprint.pod.racks.push(Racks);
        }



        $scope.rackList = Racks;
        currentPod[0].racks = Racks;
        console.log($scope.rackList);


    }
    $scope.addNode = function() {
        ngDialog.open({
            scope: $scope,
            template: 'addNode',
            closeByDocument: false,
            controller: 'popUpaddNodeController',
            appendClassName: 'ngdialog-custom',
            width: '900px'
        });
    }
    $scope.addRack = function() {
        $scope.rackList = Racks;
        $scope.showRackTable = true;
        ngDialog.open({
            scope: $scope,
            template: 'addRack',
            closeByDocument: false,
            controller: 'popUpaddRackController',
            appendClassName: 'ngdialog-custom',
            width: '800px'
        });
    }
    $scope.addRackCancel = function(){
    	$scope.showPod = false;
    }
    $scope.addPod = function() {
    	//$scope.siteName = "";
        $scope.podName = "";
        $scope.podType = "";
        $scope.podData = "";
        $scope.check = 0;
        ngDialog.open({
            scope: $scope,
            template: 'InputFile.html',
            closeByDocument: false,
            controller: 'popUpaddnewRackController',
            appendClassName: 'ngdialog-custom',
            width: '1200px',
            height: '650px'
        });
        /*ngDialog.open({
	            scope: $scope,
	            template: 'addPod',
	            closeByDocument: false,
	            controller: 'popUpaddPodController',
	            appendClassName: 'ngdialog-custom',
	            width: '800px'
	        });*/
    }
    $scope.addRackExample = function() {


    }

    $scope.editPod = function() {
    	$scope.podSelect = true;
 	   $scope.topodDelete = {};
      $scope.findPod = $scope.podList.filter(function(element) {
          return element.podname === $scope.findpodName;
      });
      $scope.check = 1;
      console.log($scope.findPod);
      console.log($scope.findPod[0].podjson);
      
      $scope.findpodData = $base64.decode($scope.findPod[0].podjson);
      console.log($scope.podData);
        ngDialog.open({
            scope: $scope,
            template: 'InputFile.html',
            closeByDocument: false,
            controller: 'popUpaddnewRackController',
            appendClassName: 'ngdialog-custom',
            width: '1200px',
            height: '650px'
        });
    }

    
    /*$scope.BlueprintSelected = function(blueprintName,pod,index)
    {
    	$scope.saveBlueprint = false;
    	$scope.selectedRow = index;
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
    $scope.selectedPod = function() {
        if ($scope.selectPodName.name == null) {} else {
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
    /* }
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
        	var Racks = new Array();
        	var Nodes = new Array();
        	 for(var i = 0;i<52;i++){
	    	     var newNode =$scope.createNodeObject(i,"", "",[],[]);
	   	   		 Nodes.push(newNode);
	   	   		 $scope.nodeList = Nodes;
	   	   	 }
        	 var newRack = $scope.createRackObject($scope.rackNName, "", Nodes);
        	 Racks.push(newRack);
             $scope.rackList = Racks;
        	 var newPod = $scope.createPodObject($scope.selectPodName.name, $scope.rackList);
	         var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
	         Blueprints.push(newBlueprint);
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
        					 var Nodes = new Array();
        					 for(var i = 0 ;i<52;i++){
        			    	     var newNode =$scope.createNodeObject(i,"", "",[],[]);
        			   	   		 Nodes.push(newNode);
        			   	   		 $scope.nodeList = Nodes;
        			   	   	 } 
        					 var newRack = $scope.createRackObject($scope.rackSelectList[j].name, $scope.rackSelectList[j].type, Nodes);
        					 currentBlueprint.pod.racks.push(newRack);
        				 }
        			 }
        			 $scope.rackList= currentBlueprint.pod.racks;
        			 
        		 }else{
             	var Nodes = new Array();
             	 for(var i = 0;i<52;i++){
     	    	     var newNode =$scope.createNodeObject(i,"", "",[],[]);
     	   	   		 Nodes.push(newNode);
     	   	   		 $scope.nodeList = Nodes;
     	   	   	 }
             	var Racks = new Array();
             	 var newRack = $scope.createRackObject($scope.rackNName, "", Nodes);
             	Racks.push(newRack);
                  $scope.rackList = Racks;
             	 //var newPod = $scope.createPodObject($scope.selectPodName.name, $scope.rackList);
     	         //var newBlueprint = $scope.createBlueprintObject($scope.blueprintName, newPod);   
                  currentBlueprint.pod.racks.push(Racks);
        		 }
        		 
        	 }
        	 else{
        		 var Racks = new Array();
        		 Racks.push(currRack);
        		 console.log(currRack);
        		 $scope.rackList = Racks;
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
    	var newNode =$scope.createNodeObject($scope.rackNumber,$scope.nodeName, $scope.nodeType, [], []); 
		 currRack.nodes[$scope.rackNumber] =newNode;
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
    }*/
    $scope.displayNode = function() {
        $scope.display = "Node";
        console.log($scope.display);
    }
    $scope.topodDelete = {};
    $scope.callCheck = function(index) {
    	
        	$scope.editIndex = ($scope.currentpodPage-1)*$scope.podsPerPage+index;
           
       
    	
       console.log($scope.editIndex);
        var count = 0;
        for (var prop in $scope.topodDelete) {
            if ($scope.topodDelete[prop]) {
                count = count + 1;
                $scope.findpodName = $scope.podList[$scope.editIndex].podname;
                console.log("Hi" +$scope.findpodName);
            }
        }
        console.log($scope.topodDelete);
        if (count == 0) {
            $scope.podSelect = true;
            $scope.deletePod = true;

        } else if (count >= 2) {
            $scope.podSelect = true;
            $scope.deletePod = false;

        } else {
            $scope.podSelect = false;
            $scope.deletePod = false;
        }
        //$scope.topodDelete = {};

    }
    $scope.deletePods = function() {
        console.log($scope.topodDelete);
        for (var prop in $scope.topodDelete) {
            if ($scope.topodDelete[prop])
                Pods.splice($scope.topodDelete[prop], 1);
        }
        $scope.topodDelete = {};


    }
    var allSitesDisplay = function() {
        $http({
            method: 'GET',
            url: appContext + '/edgeSites/0',
            //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/0',
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
                'tokenId': $scope.tokenId
            }
        }).then(function(response) {
            //console.log(response.data);
            $scope.sites = response.data.sort(function(a, b) {
                //note the minus before -cmp, for descending order
                return $scope.cmp(
                    [$scope.cmp(a.region.regionName, b.region.regionName), $scope.cmp(a.edgeSiteName, b.edgeSiteName)],
                    [$scope.cmp(b.region.regionName, a.region.regionName), $scope.cmp(b.edgeSiteName, a.edgeSiteName)]
                );
            });
            //console.log($scope.sites);
            $scope.siteInfo = $scope.sites.filter(function(element){
            	return element.edgeSiteName === abc;
            });
           //console.log($scope.siteInfo);
        }, function(error) {
            $scope.errorHandle(error);
        });
    }
    allSitesDisplay();


});
angular.module('PortalManagement').controller('popUpaddNodeController', function($scope, $http, ngDialog) {

    $scope.addNodes = function() {
        if ($scope.nodeName != "" && $scope.nodeType != "") {
            var currentPod = Pods.filter(function(element) {
                return element.name === $scope.$parent.podName;
            });
            console.log(currentPod);
            console.log($scope.rackName);
            var currRack = currentPod[0].racks.filter(function(element) {
                return element.name === $scope.rackName.name;
            });
            console.log(currRack);
            if (currRack[0].nodes == undefined) {
                var Nodes = new Array();
                for (var i = 0; i < 52; i++) {
                    var newNode = $scope.createNodeObject(i, "", "", [], []);
                    Nodes.push(newNode);
                    $scope.nodeList = Nodes;
                }
                currRack[0].nodes = Nodes;
                console.log($scope.nodeType);
                var newNode = $scope.$parent.createNodeObject($scope.rackPosition, $scope.nodeName, $scope.nodeType, $scope.softwareList, $scope.hardwareList);
                currRack[0].nodes[$scope.rackPosition] = newNode;
                $scope.$parent.rackList = currentPod[0].racks;
                console.log($scope.$parent.rackList);
                $scope.nodeList = currRack[0].nodes;
                // $scope.showNodeTable = true;
                console.log(currentPod[0]);

            }

            var newNode = $scope.$parent.createNodeObject($scope.rackPosition, $scope.nodeName, $scope.nodeType, $scope.softwareList, $scope.hardwareList);
            currRack[0].nodes[$scope.rackPosition] = newNode;
            $scope.$parent.rackList = currentPod[0].racks;
            console.log($scope.$parent.rackList);
            $scope.nodeList = currRack[0].nodes;
            // $scope.showNodeTable = true;
            console.log(currentPod[0]);
            //$scope.addNodeMenu = false;
            //$scope.idSelectedVote = null;
        }
    }
    $scope.addHardware = function() {
        console.log($scope.hardwareType);
        var Hardware = new Array();
        if ($scope.hardwareList != undefined) {
            Hardware = $scope.hardwareList;
            Hardware.push($scope.hardwareType);
        } else {
            Hardware.push($scope.hardwareType);
        }

        $scope.hardwareList = Hardware;
        console.log($scope.hardwareList);
    }
    $scope.addSoftware = function() {
        console.log($scope.softwareType);
        var Software = new Array();
        if ($scope.softwareList != undefined) {
            Software = $scope.softwareList;
            Software.push($scope.softwareType);
        } else {
            Software.push($scope.softwareType);
        }

        $scope.softwareList = Software;
        console.log($scope.softwareList);
    }

});
angular.module('PortalManagement').controller('popUpaddPodController', function($scope, $http, ngDialog) {
    var editPod = Pods.filter(function(element) {
        return element.name === $scope.podName;
    });
    if (editPod[0] != undefined) {
        $scope.podRackList = editPod[0].racks;
    }
    $scope.createPod = function(podName, podType, selectedRack) {


        var selectedPod = Pods.filter(function(element) {
            return element.name === podName;
        });
        console.log(selectedRack);
        if (selectedPod[0] == undefined) {
            var exampleRack = new Array();
            exampleRack.push(selectedRack);
            var newPod = $scope.$parent.createPodObject(podName, exampleRack);
            Pods.push(newPod);

            $scope.podRackList = exampleRack;
            console.log(Pods);
        } else {
            var exampleRack = new Array();
            console.log(selectedPod.racks);

            if (selectedPod[0].racks.length != undefined) {
                for (var i = 0; i <= selectedPod[0].racks.length; i++) {
                    if (selectedPod[0].racks[i] != undefined)
                        exampleRack.push(selectedPod[0].racks[i]);
                }
            } else {
                exampleRack.push(selectedPod[0].racks);
            }
            //console.log(exampleRack);
            var searchRack = exampleRack.filter(function(element) {
                return element.name === selectedRack.name;
            });
            if (searchRack[0] == undefined) {
                exampleRack.push(selectedRack);
            }
            selectedPod[0].racks = exampleRack;
            $scope.podRackList = exampleRack;
            console.log($scope.podRackList);
        }
        console.log(Pods);
    }

    /*if(selectedPod == undefined){
			var newPod = $scope.$parent.createPodObject(podName, selectedRacks);
			Pods.push(newPod);
			console.log(Pods);
			$scope.$parent.podList = Pods;
			console.log($scope.podList);
			$scope.selectPodList = Pods.find(function(element) {
	            return element.name === podName;
	        });
			$scope.selectPodrackList = $scope.selectPodList.racks;
			console.log($scope.selectPodrackList);
		}else{
			var exampleRack = new Array();
	   		 exampleRack.push(selectedPod.racks);
	   		 console.log( exampleRack);
	   		var selectedpushRack =  exampleRack.find(function(element) {
	            return element.name === selectedRack.name;
	        });
	   		console.log(selectedpushRack);
			if(selectedpushRack == undefined){
			
   		 exampleRack.push(selectedRacks);
			console.log(selectedRacks);
			
			selectedPod.racks = exampleRack;
			}
		  $scope.$parent.podList = Pods;
		  $scope.selectPodList = Pods.find(function(element) {
	            return element.name === podName;
	        });
			$scope.selectPodrackList = $scope.selectPodList.racks;
			console.log($scope.selectPodList);
		  
		}
		var arrayRack = new Array();
		for (var i=0; i<$scope.selectPodList.racks.length ;i++){
			arrayRack.push($scope.selectPodList.racks[i].name);
		}	
		console.log(arrayRack);
	}*/



});
angular.module('PortalManagement').controller('popUpaddRackController', function($scope, $http, ngDialog) {
    $scope.toDelete = {};
    $scope.createRack = function(rackName, rackType) {

        var Nodes = new Array();
        for (var i = 0; i < 52; i++) {
            var newNode = $scope.createNodeObject(i, "", "", [], []);
            Nodes.push(newNode);

        }
        var newRack = $scope.createRackObject(rackName, rackType, Nodes);
        Racks.push(newRack);
        $scope.rackList = Racks;
        console.log($scope.rackList);
        $scope.showRackTable = true;
        $scope.rackName = "";
        $scope.rackType = "";

    }
    $scope.deleteRack = function() {
        console.log($scope.toDelete);
        for (var prop in $scope.toDelete) {
            if ($scope.toDelete[prop])
                Racks.splice($scope.toDelete[prop], 1);
        }
        $scope.toDelete = {};
    }

});
angular.module('PortalManagement').controller(
    'myPopoverCtrl',
    function($scope, $http, ngDialog) {

        // query popover
        $scope.myPopover = {

            isOpen: false,

            templateUrl: 'myPopoverTemplate.html',

            open: function open(type, name) {

              /*  if (type == "") {
                    $scope.myPopover.isOpen = false;
                    ngDialog.open({
                        scope: $scope,
                        template: 'addNode',
                        closeByDocument: false,
                        controller: 'popUpaddNodeController',
                        appendClassName: 'ngdialog-custom',
                        width: '850px'
                    });


                } else {*/
                    $scope.myPopover.isOpen = true;
                    $scope.myPopover.nodeName = name;
                    console.log( $scope.myPopover.nodeName);
                    $scope.myPopover.nodeType = type;

                //}
            },

            close: function close() {
                $scope.myPopover.isOpen = false;
            }
        };

    });
/* $scope.myPopover = {

			nodeNeutronIP			isOpen: [],

						open: function isOpen(i) {
							if ($scope.items.length > 0) {
								$scope.myPopover.isOpen = fillArrayWith($scope.items.length, false);
							}
							$scope.myPopover.isOpen[i] = true;
						}
					};

					function fillArrayWith(n, value) {
						var arr = Array.apply(null, Array(n));
						return arr.map(function () {
							return value;
						});
					}*/
angular.module('PortalManagement').controller(
    'popUpaddnewRackController',
    function($scope, $http, appContext, Upload,$base64) {
        $scope.selectedTab = 1;
        console.log(abc);
        $scope.checkSelectBox = abc;
        if($scope.checkSelectBox == null){
        	$scope.checkSelectBox = "Select Site";
        }
        else{
        	$scope.checkSelectBox = abc;
        	$http({
                method: 'GET',
                url: appContext + '/edgeSites/0',
                //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/0',
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json",
                    'tokenId': $scope.tokenId
                }
            }).then(function(response) {
                //console.log(response.data);
                $scope.sitesData = response.data
                //console.log($scope.sitesData);
                $scope.siteInfo = $scope.sitesData.filter(function(element){
                	return element.edgeSiteName === abc;
                });
                console.log(abc);
                $scope.siteName = $scope.siteInfo[0];
               
            }, function(error) {
                $scope.errorHandle(error);
            });
            
        }
        
        
        $scope.idSelectedVote = null;
        if($scope.check == 1){
        	$scope.podData = JSON.parse($scope.$parent.findpodData);
        	$scope.podName = $scope.$parent.findPod[0].podname;
        	$scope.podType = $scope.$parent.findPod[0].podType;
        	$scope.hostIP= $scope.$parent.findPod[0].site.edgeSiteIP;
        	$scope.hostUserName= $scope.$parent.findPod[0].site.edgeSiteUser;
        	$scope.blueprint= $scope.$parent.findPod[0].site.blueprint;
        	$scope.hostPassword= $scope.$parent.findPod[0].site.edgeSitePwd ;
        	$scope.siteName = $scope.$parent.findPod[0].site;
        	console.log($scope.$parent.findpodData);

            $scope.whiteListData1 =  $scope.podData.sriovnets[0].whitelists;
            $scope.whiteListData2 =  $scope.podData.sriovnets[1].whitelists;
            $scope.slaves =  $scope.podData.networks.slaves;
            console.log($scope.slaves);
            $scope.log = [];
            $scope.log2 = [];
            $scope.slaves = [];
            angular.forEach($scope.whiteListData1, function (value, index) {
            	   $scope.log.push($scope.whiteListData1[index].address);
            	});
            $scope.log = $scope.log.join('\n');
            angular.forEach($scope.whiteListData2, function (value, index) {
         	   $scope.log2.push($scope.whiteListData2[index].address);
         	});
            angular.forEach($scope.slaves, function (value, index) {
         	   $scope.slaves.push($scope.slaves[index].name);
         	});
         $scope.log2 = $scope.log2.join('\n');
         $scope.slaves = $scope.slaves.join('\n');
         console.log($scope.slaves);
         
        }
        else if($scope.check == 0)
        	{
        	$scope.podName = "";
        	$scope.podData = "";
        	$scope.podType = "";
        	$scope.hostIP="";
        	$scope.hostUserName="";
        	$scope.blueprint="";
        	$scope.hostPassword="";
        	$scope.checkSelectBox ="Select Site";
        	 $scope.log = [];
             $scope.log2 = [];
             $scope.slaves = [];
        	}
         $scope.setstorageSelected = function(data,journal) {
        	$scope.idSelectedData = data;
        	$scope.dataVolume = data;
        	$scope.journalVolume = journal;
        }
        $scope.setSelected = function(idSelectedVote) {
           $scope.idSelectedVote = idSelectedVote;
           console.log(idSelectedVote);
           $scope.selectedValue = $scope.podData.masters.filter(function(element) {
               return element.name === $scope.idSelectedVote ;
           });
           if($scope.selectedValue == undefined){
        	   $scope.selectedValue = $scope.podData.workers.filter(function(element) {
                   return element.name === $scope.idSelectedVote ;
               });
        	   $scope.Type = "worker";
           }
           else{
        	   $scope.Type = "master"; 
           }
           $scope.nodeName = $scope.selectedValue[0].name;
           console.log($scope.nodeName);
          $scope.nodeIP = $scope.selectedValue[0].oob;
          $scope.nodeHostIP = $scope.selectedValue[0].host;
          $scope.nodeStorageIP = $scope.selectedValue[0].storage;
          $scope.nodePxEIP = $scope.selectedValue[0].pxe;
          $scope.nodeCalicoIP = $scope.selectedValue[0].ksn;
          $scope.nodeNeutronIP = $scope.selectedValue[0].neutron;
        }
       
      
       
      
        $scope.uploadFile = function(file) {
            console.log($scope.siteName);
            file.upload = Upload.upload({
                url: appContext + '/edgeSites/upload',
                //url:'http://'+hostUrl+'/AECPortalMgmt/edgeSites/upload',
                method: 'POST',
                file: file,
                data: {
                    "siteName": $scope.siteName.edgeSiteName,
                    "blueprint": $scope.blueprint,
                    "edgeSiteIP": $scope.hostIP,
                    "edgeSiteUser":$scope.hostUserName,
                    "edgeSitePwd":$scope.hostPassword,
                    "deployMode":"new"


                },
                headers: {
                    'Content-Type': undefined
                }
            }).then(function(response) {
                if (response.data.statusCode == '200') {
                    $scope.displayFile();
                } else {

                }

            }, function(error) {

            });


        }
        $scope.displayFile = function() {
            $http({
                method: 'GET',
                url: appContext + '/edgeSites/site/podInfo/' + $scope.siteName.edgeSiteName + '/' + $scope.blueprint,
               
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json",
                    'tokenId' : $scope.tokenId
                }

            }).then(function(response) {
                if (response.status == 200) {
                    $scope.podData = response.data;
                   
                    $scope.whiteListData1 = $scope.podData.sriovnets[0].whitelists;
                    $scope.whiteListData2 = $scope.podData.sriovnets[1].whitelists;
                    $scope.slaves = $scope.podData.networks.slaves;
                   
                    $scope.log = [];
                    $scope.log2 = [];
                    $scope.slaves = [];
                    angular.forEach($scope.whiteListData1, function (value, index) {
                    	   $scope.log.push($scope.whiteListData1[index].address);
                    	});
                    $scope.log = $scope.log.join('\n');
                    angular.forEach($scope.whiteListData2, function (value, index) {
                 	   $scope.log2.push($scope.whiteListData2[index].address);
                 	});
                    angular.forEach($scope.slaves, function (value, index) {
                 	   $scope.slaves.push($scope.slaves[index].name);
                 	});
                 $scope.log2 = $scope.log2.join('\n');
                 $scope.slaves = $scope.slaves.join('\n');
                 console.log($scope.slaves);
                 $scope.nodeName = $scope.podData.masters[0].name;
                 console.log($scope.nodeName);
                $scope.nodeIP = $scope.podData.masters[0].oob;
                $scope.nodeHostIP = $scope.podData.masters[0].host;
                $scope.nodeStorageIP = $scope.podData.masters[0].storage;
                $scope.nodePxEIP = $scope.podData.masters[0].pxe;
                $scope.nodeCalicoIP = $scope.podData.masters[0].ksn;
                $scope.nodeNeutronIP = $scope.podData.masters[0].neutron;
                $scope.type= "master";
                $scope.dataVolume = $scope.podData.storage.osds[0].data;
                $scope.journalVolume =  $scope.podData.storage.osds[0].journal;
                } else {

                }
            }, function(error) {

            });

        }
        $scope.savePod = function(){
        	$scope.closeThisDialog('cancel');
        	abc = null;
        	//$scope.$parent.podSelect = true;
     	   //$scope.$parent.topodDelete = {};
        	$scope.podData.site_name = $scope.siteName.edgeSiteName;
        	console.log(JSON.stringify($scope.podData));
        	$scope.encodePodJson = $base64.encode(JSON.stringify($scope.podData));
        	console.log($scope.encodePodJson);
        	$http({
                method: 'POST',
                url: appContext + '/pod/',
                data:{
               	"podName":$scope.podName,
               	"podType":$scope.podType,
               	"podJson":$scope.encodePodJson,
               	"siteId":$scope.siteName.edgeSiteId
                },
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json",
                    'tokenId' : $scope.tokenId
                }

            }).then(function(response) {
            	
                if (response.status == 200) {
                	 $scope.podDetails();
                	alert("Pod has been saved successfully.");
                
                } else {
                	alert("Error occured while saving the Pod.");
                }
            }, function(error) {

            });

        	
        }
       $scope.cancel = function(){
    	   abc = null;
    	   $scope.closeThisDialog('cancel');
    	   //$scope.$parent.podSelect = true;
    	   //$scope.$parent.topodDelete = {};
       }

    });