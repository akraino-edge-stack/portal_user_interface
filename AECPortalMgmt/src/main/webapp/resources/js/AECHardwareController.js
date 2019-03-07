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
var popUpData = new Array();
var blueprintList = new Array();
var dataArray = "";
var controlNodeTypeCount = 0;
var abc = new Array();
angular.module('PortalManagement').controller('AECHardwareController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller, $rootScope, $controller,appContext) {

    $scope.stepNumber = 1;
    $scope.hardwareButton = "Add Node";
    $scope.stepName = "Add Control Nodes";
    $scope.maxStep = 1;
    $scope.hardwareDetails = "";
    $scope.addNodesButton = false;
    $scope.showSaveButton = false;
    $scope.tokenId = localStorage.getItem("tokenId");
    $scope.displayForm = true;

   
    $controller('PopUpHardwareController', {
        $scope: $scope
    });
    $http({
        method: 'GET',
        url: appContext+ '/blueprint.txt',
        //url: 'http://' + hostUrl + '/AECPortalMgmt/blueprint.txt',
        headers: {
            'Content-Type': "application/json",
            'Accept': "application/json"
        }
    }).then(function(response) {
        $scope.blueprints = response.data;
        $scope.selectedBlueprint = $scope.blueprints[0];
    }, function(error) {});
    $scope.selectedBluePrintChange = function() {
        if ($scope.selectedBlueprint.name == 'Rover') {
            $scope.hardwareButton = "Add Node";
            $scope.maxStep = 1;
            $scope.displayForm = true;
            $scope.checkBlueprint = "";
            $scope.addNodesButton = false;
            $scope.stepNumber = 1;
            //console.log($scope.selectedBlueprint.name);

        } else if ($scope.selectedBlueprint.name == 'Unicycle') {
            $scope.hardwareButton = "Add Control Nodes";
            $scope.maxStep = 4;
            $scope.displayForm = true;
            $scope.checkBlueprint = "";
            $scope.addNodesButton = false;
            $scope.stepNumber = 1;
            //console.log($scope.selectedBlueprint.name);
        } else {
            $scope.displayForm = false;
            $scope.checkBlueprint = "Not Supported";

            //console.log($scope.selectedBlueprint.name);
        }

    }
    $scope.addNodes = function() {
        $scope.showSaveButton = false;
        if ($scope.siteBuildName == null) {
            $scope.errorMessage = "Please provide site name."
            //$scope.showerrorMessage =true;
        } else {
            $scope.storesiteBuild = $scope.selectedBlueprint.name + "-" + $scope.siteBuildName;
            abc = localStorage.getItem($scope.storesiteBuild);
            //$scope.hardwareDetails ="";
            if (abc == null) {

                ngDialog.open({
                    scope: $scope,
                    template: 'hardwaretemplateForm',
                    closeByDocument: false,
                    controller: 'PopUpHardwareController',
                    appendClassName: 'ngdialog-custom',
                    width: '800px'
                });
            } else {
                $scope.hardwareDetails = $scope.$eval(abc);
                $scope.showHardwareTable = true;
                $scope.$parent.addNodesButton = true;
            }
        }
    }
    $scope.cancelAll = function() {
        $scope.showSaveButton = true;
        $scope.showHardwareTable = false;
        $scope.siteBuildName = null;

    }
    $scope.saveNodes = function() {

        //$scope.Save = angular.toJson($scope.hardwareDetails);
        $scope.storesiteBuildName = $scope.selectedBlueprint.name + "-" + $scope.siteBuildName;

        window.localStorage.setItem($scope.storesiteBuildName, JSON.stringify($scope.hardwareDetails));

        localStorage.setItem("accessSiteName", localStorage.getItem("accessSiteName") + "," + $scope.storesiteBuildName);
        //console.log(localStorage.getItem("accessSiteName") + $scope.siteBuildName);
        $scope.hardwareDetails = "";
        $scope.showHardwareTable = false;
        $scope.selectedBluePrintChange();
        $scope.siteBuildName = null;
        popUpData = [];
        ngDialog.open({
            scope: $scope,
            template: 'savetemplateForm',
            closeByDocument: false,
            controller: 'PopUpSaveHardwareController',
            appendClassName: 'ngdialog-custom',
            width: '300px'
        });

    }
});
angular.module('PortalManagement').controller('PopUpHardwareController', function($scope, $http, $filter, filterFilter, $state) {
    //console.log($scope.$parent.selectedBlueprint);
	 $scope.data = {
             nodeName: "",
             nodeType: "",
             hwModel: "",

             blueprintSiteBuildName: ""
         };
    if ($scope.$parent.selectedBlueprint != undefined) {
        if ($scope.$parent.selectedBlueprint.name == 'Rover') {
            $scope.nodeType = "Control,Compute,N/W,Storage";
           
            $scope.data.blueprintSiteBuildName = $scope.$parent.selectedBlueprint.name + '-' + $scope.siteBuildName;
            $scope.blueprintSiteBuildName = $scope.$parent.selectedBlueprint.name + '-' + $scope.siteBuildName;
        } else {
        	 
            $scope.blueprintSiteBuildName = $scope.$parent.selectedBlueprint.name + '-' + $scope.siteBuildName;
            $scope.data.blueprintSiteBuildName = $scope.$parent.selectedBlueprint.name + '-' + $scope.siteBuildName;
            $scope.readControlType = true;
            //console.log($scope.$parent.hardwareDetails);
            if ($scope.$parent.hardwareDetails == "" || $scope.$parent.hardwareDetails.length < 3) {
                $scope.nodeType = "Control";
            } else if ($scope.$parent.hardwareDetails.length > 2 && $scope.$parent.hardwareDetails.length <= 6) {
                $scope.nodeType = "Compute";
            } else if ($scope.$parent.hardwareDetails.length > 6 && $scope.$parent.hardwareDetails.length <= 8) {
                $scope.nodeType = "N/w";
            } else if ($scope.$parent.hardwareDetails.length == 9) {
                $scope.nodeType = "Storage";
            }
            $scope.data = {
                nodeName: "",
                nodeType: "",
                hwModel: "",

                blueprintSiteBuildName: ""
            };

        }
    }
    $scope.submitSiteBuildDetails = function() {
        if ($scope.data.nodeName != "" && $scope.data.hwModel != "" && $scope.data.ipAddress != "" && $scope.data.vCPU != "" && $scope.data.RAM != "" && $scope.data.Disk != "") {
            $scope.$parent.showHardwareTable = true;
            /*$scope.$parent.nodeName.push($scope.nodeName);
            $scope.$parent.nodeType.push($scope.nodeType);
            a["nodeName"] =$scope.$parent.nodeName;
            a["nodeType"] =$scope.$parent.nodeType;*/
            if ($scope.$parent.selectedBlueprint.name == 'Rover') {
                //console.log(popUpData);

                $scope.$parent.hardwareDetails = "";


                $scope.data.nodeType = $scope.nodeType;
                dataArray = $scope.data;
                popUpData.push(dataArray);
                $scope.$parent.hardwareDetails = popUpData;
                if ($scope.$parent.hardwareDetails.length == 1) {
                    //$scope.$parent.addNodesButton = true;
                    $scope.$parent.showSaveButton = true;

                } else {
                    //alert($scope.$parent.hardwareDetails.length);
                }
                $scope.closeThisDialog();
            } else {
                $scope.data.blueprintSiteBuildName = $scope.$parent.selectedBlueprint.name + '-' + $scope.siteBuildName;
                $scope.data.nodeType = $scope.nodeType;
                dataArray = $scope.data;
                popUpData.push(dataArray);
                $scope.$parent.hardwareDetails = popUpData;
                if ($scope.$parent.hardwareDetails.length == 3) {
                    $scope.$parent.stepNumber = 2;
                    $scope.$parent.stepName = "Add Compute Nodes";
                    $scope.$parent.hardwareButton = "Add Compute Nodes";
                } else if ($scope.$parent.hardwareDetails.length == 7) {
                    $scope.$parent.stepNumber = 3;
                    $scope.$parent.stepName = "Add N/w Nodes";
                    $scope.$parent.hardwareButton = "Add N/w Nodes";
                } else if ($scope.$parent.hardwareDetails.length == 9) {
                    $scope.$parent.stepNumber = 4;
                    $scope.$parent.stepName = "Add Storage Nodes";
                    $scope.$parent.hardwareButton = "Add Storage Nodes";
                } else if ($scope.$parent.hardwareDetails.length == 10) {
                    $scope.$parent.addNodesButton = true;
                    $scope.$parent.showSaveButton = true;
                }
                $scope.closeThisDialog();
            }

        }
    }
    //nsole.log("hi" +$scope.siteName);
    $scope.cancel = function() {
        $scope.closeThisDialog();
    };
});
angular.module('PortalManagement').controller('PopUpSaveHardwareController', function($scope, $http, $filter, filterFilter, $state) {
	 $scope.cancel = function() {
	        $scope.closeThisDialog();
	    };
});