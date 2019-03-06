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
angular.module('PortalManagement').controller('AECSoftwareController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller,appContext) {
     $controller('commonController', { $scope: $scope }); 
    $scope.NodeName ="Site Ipaddress";
    $scope.NodeType ="Software Name";
    $scope.Model ="Version";
    $scope.softwareSiteNameDetail = localStorage.getItem("accessSiteName");
    if ($scope.softwareSiteNameDetail != null) {
        $scope.softwareSiteReplace = "Rover,Unicycle," + $scope.softwareSiteNameDetail.replace("null,", "");
    } else {
        $scope.softwareSiteReplace = "Rover,Unicycle";
    }
    $scope.softwareSiteName = new Array();
    $scope.softwareSiteName = $scope.softwareSiteReplace.split(",");
    console.log($scope.softwareSiteName);
    
    $controller('commonController', {
        $scope: $scope
    });
    $scope.showBlueprintTable = function() {
        if ($scope.selectSoftwareBlueprint == 'Rover') {
            $scope.NodeName ="Site Ipaddress";
            $scope.NodeType ="Software Name";
            $scope.Model ="Version";
            $http({
                method: 'GET',
                url: appContext+'/softwareRover.txt',
               // url: 'http://' + hostUrl + '/AECPortalMgmt/softwareRover.txt',
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json"
                }
            }).then(function(response) {
                 $scope.softwareDetails  = response.data;
                 $scope.showsoftwareTable = true;
            }, function(error) {});

        } else if ($scope.selectSoftwareBlueprint == 'Unicycle') {
            $scope.NodeName ="Site Ipaddress";
            $scope.NodeType ="Software Name";
            $scope.Model ="Version";
            $http({
                method: 'GET',
                url: appContext+'/softwareUnicycle.txt',
                //url: 'http://' + hostUrl + '/AECPortalMgmt/softwareUnicycle.txt',
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json"
                }
            }).then(function(response) {
                $scope.softwareDetails  = response.data;
                $scope.showsoftwareTable = true;
            }, function(error) {});


        } else {
            $scope.NodeName ="Node(host)";
            $scope.NodeType ="Node Type";
            $scope.Model ="H/W Model";
            console.log(localStorage.getItem($scope.selectSoftwareBlueprint));
            $scope.softwareDetails = $scope.$eval(localStorage.getItem($scope.selectSoftwareBlueprint));
            $scope.showsoftwareTable = true;
        }
    }
    $scope.showSoftwarePopup = function(index) {
        $scope.displaySiteName = $scope.softwareDetails[index].nodeName;
        ngDialog.open({
            scope: $scope,
            template: 'softwaretemplateForm',
            closeByDocument: false,
            controller: 'PopUpSoftwareController',
            appendClassName: 'ngdialog-custom',
            width: '800px'
        });
    }
    $http({
        method: 'GET',
        url: appContext+'/blueprint.json',
        //url: 'http://' + hostUrl + '/AECPortalMgmt/blueprint.json',
        headers: {
            'Content-Type': "application/json",
            'Accept': "application/json"
        }
    }).then(function(response) {
         $scope.softwareDetails  = response.data;
         console.log($scope.softwareDetails );
         $scope.showsoftwareTable = true;
    }, function(error) {});

    
});
angular.module('PortalManagement').controller('PopUpSoftwareController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller) {
    $scope.displaysoftwareSiteName = $scope.$parent.selectSoftwareBlueprint;
    $scope.cancel = function() {
        $scope.closeThisDialog();
    };
});