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
angular.module('PortalManagement').controller('AECSiteBuildController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller) {
    $scope.siteNameDetail = localStorage.getItem("accessSiteName");
    if ($scope.siteNameDetail != null) {
        $scope.siteReplace = "Rover,Unicycle," + $scope.siteNameDetail.replace("null,", "");
    } else {
        $scope.siteReplace = "Rover,Unicycle";
    }

    $scope.sitebuildSiteName = new Array();


    $scope.sitebuildSiteName = $scope.siteReplace.split(",");
    $controller('commonController', {
        $scope: $scope
    });
    $scope.showsiteBuildTable = function() {
        if ($scope.selectsiteBlueprint == 'Rover') {

        } else if ($scope.selectsiteBlueprint == 'Unicycle') {

        } else {

            $scope.sitebuildDetails = $scope.$eval(localStorage.getItem($scope.selectsiteBlueprint));
            $scope.sitebuildTable = true;
        }
    }
    $scope.opensiteBuildpopup = function(index) {
        $scope.displaySitebuildName = $scope.sitebuildDetails[index].nodeName;
        ngDialog.open({
            scope: $scope,
            template: 'sitebuildtemplateForm',
            closeByDocument: false,
            controller: 'PopUpSiteBuildController',
            appendClassName: 'ngdialog-custom',
            width: '800px'
        });
    }
});
angular.module('PortalManagement').controller('PopUpSiteBuildController', function($scope, $http, $filter, filterFilter, $state, ngDialog, $controller) {
    $scope.displaySitebuildSiteName = $scope.$parent.sitebuildSiteName;
    $scope.cancel = function() {
        $scope.closeThisDialog();
    };
});