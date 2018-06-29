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
angular.module('PortalManagement').controller('AECSoftwareController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller) {
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

        } else if ($scope.selectSoftwareBlueprint == 'Unicycle') {

        } else {
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
});
angular.module('PortalManagement').controller('PopUpSoftwareController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller) {
    $scope.displaysoftwareSiteName = $scope.$parent.selectSoftwareBlueprint;
    $scope.cancel = function() {
        $scope.closeThisDialog();
    };
});