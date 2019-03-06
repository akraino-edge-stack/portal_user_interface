 /* Copyright (c) 2018 AT&T Intellectual Property. All rights reserved.
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
 
 var pod = {
     podname: "value",
     racks: "value"
 };
 var pods = new Array();

 angular.module('PortalManagement').controller('AECPodsController', function($scope, $http, $sce, ngDialog, $filter, $rootScope, $controller, appContext,$timeout) {
         $scope.rackTableShow = false;
         $scope.textInputValidation = true;
         $scope.showPodDetails = false;
         $scope.showCreatePods = false;
         $scope.showSaveButton = false;
         $scope.editPodButton = true;
         $scope.deletePodButton = true;
         $scope.editRackButton = true;
         $scope.deleteRackButton = true;
         $scope.readOnlyPod = false;
         $scope.readOnlyRack = false;
         $scope.showPod = true;
         $scope.tokenId = localStorage.getItem("tokenId");
         $controller('commonController', { $scope: $scope }); 

         $scope.currentPage = 1;
         $scope.currentrackPage = 1;

         $scope.addRacks = function() {
             $scope.textInputValidation = true;
             if ($scope.podName != null && $scope.rackName != null && $scope.rackType != null) {
                 var currPod = pods.find(function(element) {
                     return element.podname === $scope.podName;
                 });

                 console.log(currPod);

                 if (currPod == undefined) {
                     var newPOD = {
                        podname: "value",
                         racks: "value"
                     };
                     var rack = {
                             rackname: "value",
                             rackPersonality: "value"
                     };
                     var newRacks = new Array();

                     newPOD.podname = $scope.podName;
                     rack.rackname = $scope.rackName;
                     rack.rackPersonality = $scope.rackType;
                     newRacks.push(rack);
                     newPOD.racks = newRacks;
                     pods.push(newPOD);
                 } else {
                     var newRack = {
                             rackname: "value",
                             rackPersonality: "value"
                     };
                     newRack.rackname = $scope.rackName;
                     newRack.rackPersonality = $scope.rackType;
                     currPod.racks.push(newRack)
                 }
                 $scope.podsTable = JSON.stringify(pods);
                 console.log(JSON.stringify(pods));
                 $scope.showRackTable($scope.podName);
                 $scope.showSaveButton = true;
                 //Find pod by name
             } 
         }
         $scope.update = function(podIndex) {
             //console.log(hostIndex)
             $scope.podName = podIndex;
             $scope.editPodButton = false;
             $scope.deletePodButton = false;
            

         }
         $scope.rackupdate = function(rackIndex) {
             $scope.rackName = rackIndex;
             $scope.editRackButton = false;
             $scope.deleteRackButton = false;
             $scope.readOnlyPod = true;
             $scope.readOnlyRack = true;

         }
         $scope.editRack = function() {
             $scope.showSaveButton = true;
             $scope.rackSelection = false;
             $scope.editRackButton = true;
             $scope.deleteRackButton = true;
            // var h = pods.length;
             console.log($scope.rackName + $scope.podName);
             for (var i = 0; i < pods.length; i++) {
                 if(pods[i].podname == $scope.podName){
                 for (var j = 0; j < pods[i].racks.length; j++) {
                     if (pods[i].racks[j].rackname == $scope.rackName) {
                         pods[i].racks[j].rackname = $scope.rackName;
                         pods[i].racks[j].rackPersonality = $scope.rackType;
                         
                     }
                     
                 }
                 } 

             }
             $scope.processMessage = "Rack has been edited,successfully.Please save the POD";
             $scope.showProcessMessage = true;
             $timeout(function() {
                 $scope.showProcessMessage = false;
                 
              }, 5000);
         }
         $scope.deleteRack = function() {
             $scope.showSaveButton = true;
             $scope.rackSelection = false;
             $scope.showProcessMessage = true;
             $scope.editRackButton = true;
             $scope.deleteRackButton = true;
            // var h = pods.length;
             for (var i = 0; i < pods.length; i++) {

                if(pods[i].podname == $scope.podName){
                 for (var j = 0; j < pods[i].racks.length; j++) {
                     console.log(pods[i].racks.length);
                     if (pods[i].racks[j].rackname == $scope.rackName) {
                         pods[i].racks.splice(j, 1);



                     }
                 }



                 }



             }
             $scope.processMessage = "Rack has been deleted,successfully.Please save the POD";
             console.log( $scope.processMessage);
             $scope.showProcessMessage = true;
             $timeout(function() {
                 $scope.showProcessMessage = false;
                 
              }, 5000);


         }
             $scope.editPOD = function() {
                 $scope.showPod = false;
                 $scope.podHeader = "Edit Pod";
                 $scope.editPodButton = true;
                 $scope.deletePodButton = true;
                 $scope.showCreatePods = true;
                 $scope.showPodDetails = false;
                 //$scope.podName = $scope.podData[$scope.podIndex].name;
                 console.log($scope.podName);
                 $scope.check = 1;
                 $scope.showRackTable($scope.podName);
                 $scope.showSaveButton = true;
                 $scope.readOnlyPod = true;

             }
             $scope.deletePOD = function() {
                 $scope.editPodButton = true;
                 $scope.deletePodButton = true;
                 $scope.showCreatePods = false;
                 $scope.showPodDetails = false;
                 
                 //$scope.podName = $scope.podData[$scope.podIndex].name;
                 
                 for (var i = 0; i < pods.length; i++) {

                     if(pods[i].podname == $scope.podName){
                         pods.splice(i, 1);
                      }



                      }
                 var savePodDelete = JSON.stringify(pods);
                 //$scope.savePods = JSON.parse($scope.savePod);
                 $scope.savePod = savePodDelete.substring(1,savePodDelete.length-1);
                 $http({
                     method: 'PUT',
                     url :appContext+'/pod/',
                     //url: 'http://' + hostUrl + '/AECPortalMgmt/pod/',
                     //dataType: 'json',
                     data: $scope.savePod,
                     headers: {
                        'Content-Type': "application/json",
                        'Accept': "application/json",
                         'tokenId' : $scope.tokenId
                     }
                 }).then(function(response) {
                     console.log(response.data);
                     $scope.processMessage = "POD has been saved successfully.";
                     console.log( $scope.processMessage);
                     $scope.showProcessMessage = true;
                     $timeout(function() {
                         $scope.showProcessMessage = false;
                         
                      }, 5000);

                 }, function(error) {
                     
                     
                 });
                 console.log(pods);
                 $scope.processPodMessage = "POD has been deleted,successfully.";
                 console.log( $scope.processPodMessage);
                 $scope.showProcessPodMessage = true;
                 $timeout(function() {
                     $scope.showProcessPodMessage = false;
                     
                  }, 5000);
                 $scope.podData = pods;

             }
             $scope.showRackTable = function(podName) {
                 $scope.rackTableShow = true;
                 $scope.textInputValidation = false;
                 $scope.rackName = null;
                 $scope.rackType = null;
                 console.log(podName);
                 $scope.name = podName;
                 var currPodlist = pods.find(function(element) {
                     return element.podname === $scope.name;
                 });
                 $scope.parcels = currPodlist.racks;
                 $scope.totalRacks = $scope.parcels.length;
                 $scope.rackPerPage = 6;
                 $scope.rackPaginate = function(value) {
                     var begin, end, index;
                     begin = ($scope.currentrackPage - 1) * $scope.rackPerPage;
                     end = begin + $scope.rackPerPage;
                     index = $scope.parcels.indexOf(value);
                     return (begin <= index && index < end);
                 };
                
             }
             $scope.showPod = function(index) {
                 console.log(index);
                 $scope.showPodDetails = true;
                 $scope.showCreatePods = false;
                 $scope.showPodName = index;
                 var currPodItem = pods.find(function(element) {
                     return element.podname === $scope.showPodName
                 });
                 $scope.racksDetail = currPodItem.racks;
                 $scope.clickedPodName = $scope.showPodName;
             }
             $scope.createPOD = function() {
                 $scope.showPod = false;
                 $scope.podHeader = "Create Pod";
                 $scope.readOnlyPod = false;
                 $scope.readOnlyRack = false;
                 $scope.showCreatePods = true;
                 $scope.showPodDetails = false;
                 $scope.rackName = null;
                 $scope.rackType = null;
                 $scope.podName = null;
                 $scope.check = 0;
             }
             $scope.cancelAll = function() {
                 $scope.showCreatePods = false;
                 $scope.rackTableShow = false;
                 $scope.podName = "";
                 $scope.rackType ="";
                 $scope.rackName = "";
                 $scope.selection = false;
                 $scope.rackSelection = false;
                 $scope.editPodButton = true;
                 $scope.deletePodButton = true;
                 $scope.showSaveButton = false;
                 $scope.showPod = true;

             }
             $http({
                 method: 'GET',
                 url: appContext+ '/pod/',
                 //url: 'http://' + hostUrl + '/AECPortalMgmt/pod/',
                 headers: {
                     'Content-Type': "application/json",
                     'Accept': "application/json",
                     'tokenId' : $scope.tokenId

                 }
             }).then(function(response) {
                 $scope.podData = response.data;
                 pods = $scope.podData;
                 $scope.totalItems = $scope.podData.length;
                 $scope.numPerPage = 6;
                 console.log($scope.podData);
             }, function(error) {
                 $scope.errorHandle(error);
             });

             $scope.paginate = function(value) {
                 var begin, end, index;
                 begin = ($scope.currentPage - 1) * $scope.numPerPage;
                 end = begin + $scope.numPerPage;
                 index = $scope.podData.indexOf(value);
                 return (begin <= index && index < end);
             };
             $scope.saveRacks = function(){
                 $scope.showPod = true;
                 $scope.showCreatePods = false;
                 console.log("Save Pod");
                 var savePods = JSON.stringify(pods);
                 //$scope.savePods = JSON.parse($scope.savePod);
                 $scope.savePod = savePods.substring(1,savePods.length-1);
                 console.log($scope.savePod);
                 if($scope.check == 1){
                 $http({
                     method: 'PUT',
                     url: appContext+'/pod/',
                    // url: 'http://' + hostUrl + '/AECPortalMgmt/pod/',
                     //dataType: 'json',
                     data: $scope.savePod,
                     headers: {
                        'Content-Type': "application/json",
                        'Accept': "application/json",
                         'tokenId' : $scope.tokenId
                     }
                 }).then(function(response) {
                     console.log(response.data);
                     $scope.processMessage = "POD has been saved successfully.";
                     console.log( $scope.processMessage);
                     $scope.showProcessMessage = true;
                     $timeout(function() {
                         $scope.showProcessMessage = false;
                         
                      }, 5000);

                 }, function(error) {
                     
                     
                 });
             }
             
             else{
                 $http({
                     method: 'POST',
                     url: appContext+'/pod/',
                     //url: 'http://' + hostUrl + '/AECPortalMgmt/pod/',
                     //dataType: 'json',
                     data: $scope.savePod,
                     headers: {
                        'Content-Type': "application/json",
                        'Accept': "application/json",
                         'tokenId' : $scope.tokenId
                     }
                 }).then(function(response) {
                     console.log(response.data);
                     $scope.processMessage = "POD has been saved successfully.";
                     console.log( $scope.processMessage);
                     $scope.showProcessMessage = true;
                     $timeout(function() {
                         $scope.showProcessMessage = false;
                         
                      }, 5000);

                 }, function(error) {
                     
                     
                 });
             }
             }

         });