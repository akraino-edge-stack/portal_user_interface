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
angular.module('PortalManagement').controller('AECeteTestController', function($scope, $http, $sce, ngDialog, $filter,$rootScope,$controller,hostUrl,camundaUrl) {
    $scope.sortingOrder = '';
    $scope.signOut = "Sign Out";
    $scope.reverse = false;
    $scope.filteredItems = [];
    $scope.groupedItems = [];
    $scope.itemsPerPage = 6;
    $scope.pagedItems = [];
    $scope.currentPage = 0;
    $scope.tempestSelectButton = true;
    $scope.tokenId = localStorage.getItem("tokenId");
    $controller('commonController', { $scope: $scope }); 
    var searchMatch = function(haystack, needle) {
        if (!needle) {
            return true;
        }
        return haystack.ignoreCase().toindexOf(needle.ignoreCase()) !== -1;
    }
    $scope.search = function() {
        $scope.filteredItems = $filter('filter')($scope.tempestSites, function(item) {
            for (var attr in item) {
                if (searchMatch(item[attr], $scope.query))
                    return true;
            }
            return false;
        });
        if ($scope.sortingOrder !== '') {
            $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
        }
        $scope.currentPage = 0;
        $scope.groupToPages();
    }
    $scope.groupToPages = function() {
        $scope.pagedItems = [];
        for (var i = 0; i < $scope.filteredItems.length; i++) {
            if (i % $scope.itemsPerPage === 0) {
                $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [$scope.filteredItems[i]];
            } else {
                $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
            }
        }
    }
    $scope.range = function(start, end) {
        var ret = [];
        if (!end) {
            end = start;
            start = 0;
        }
        for (var i = start; i < end; i++) {
            ret.push(i);
        }
        return ret;
    }
    $scope.prevPage = function() {
        if ($scope.currentPage > 0) {
            $scope.currentPage--;
        }
    }
    $scope.nextPage = function() {
        if ($scope.currentPage < $scope.pagedItems.length - 1) {
            $scope.currentPage++;
        }
    }
    $scope.setPage = function() {
        $scope.currentPage = this.n;
    }
    eteTestSitesDisplay = function() {
        $http({
            method: 'GET',
            url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/0',
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
                	'tokenId' : $scope.tokenId
            }
        }).then(function(response) {
            $scope.tempestSites = response.data;
            $scope.search();
            $scope.showtempestSitesTable = true;
        }, function(error) {
        	 $scope.errorHandle(error);
        });
    }
    eteTestSitesDisplay();
    $scope.selectedtempestRegionChange = function() {
        if ($scope.selectedtempestRegion == null) {
            eteTestSitesDisplay();
        } else {
            $http({
                method: 'GET',
                url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/' + $scope.selectedtempestRegion.regionId,
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json",
                    'tokenId' : $scope.tokenId
                }
            }).then(function(response) {
                $scope.tempestSites = response.data;
                $scope.showtempestSitesTable = true;
                $scope.search();
            }, function(error) {
            	 $scope.errorHandle(error);
            });
        }
    }
    $scope.tempestUpdate = function(index) {
        $scope.siteIndex = index;
        $scope.tempestSelectButton = false;
    }
    $scope.runTempest = function(siteIndex) {
    	$scope.tempestSites[siteIndex].tempestStatus = 'In Progress...';
        $http({
     method: 'POST',
     url: 'http://'+camundaUrl+'/tempest/',
     data: {
        "remoteserver": "192.168.2.45",
        "username":"root", 
        "password":"akraino,d" , 
        "portnumber": 22 ,
        "srcdir": "/opt/tempest", 
        "destdir": "/opt/tempest/test_automation/openstack_tempest", 
        "filename":"test_run.sh", 
        "fileparams": "OS_USER_DOMAIN_NAME=Default OS_PROJECT_DOMAIN_NAME=Default OS_PASSWORD=password", 
        "deploymentverifier":"test_status.sh",
        "verifierparams":"TIMEOUT=1600", 
        "noofiterations":0,
        "waittime":15,
        "filetrasferscript":"/opt/tempest/mv.sh"
     },
     headers: {
         'Content-Type': "application/json",
         'Accept': "application/json",
     }
 }).then(function(response) {
 	if (response.data.status == '200') {
        $scope.tempestSites[siteIndex].tempestStatus = 'Completed...';
        //console.log();
    	}
    	else {
    		$scope.tempestSites[siteIndex].tempestStatus = response.data.message;
        }
    }, function(error) {
    	$scope.tempestSites[siteIndex].tempestStatus = "Error"
    });
}
    $scope.opentempestDialog = function(index) {
        $scope.siteName = $scope.tempestSites[index].edgeSiteName;
        $scope.osUsername = "admin";
        $scope.osPassword = "password";
        $scope.osregionName = "RegionOne";
        $scope.nameSpace = "openstack";
        ngDialog.open({
            scope: $scope,
            template: 'tempestForm',
            closeByDocument: false,
            controller: 'PopUptempestController',
            appendClassName: 'ngdialog-custom',
            width: '800px'
        });
    }
    $http({
        method: 'GET',
        url: 'http://'+hostUrl+'/AECPortalMgmt/regions/',
        headers: {
            'Content-Type': "application/json",
            'Accept': "application/json",
            'tokenId' : $scope.tokenId
        }
    }).then(function(response) {
        $scope.tempestregions = response.data;
    }, function(error) {
    	$scope.errorHandle(error);
    });
});
angular.module('PortalManagement').controller('PopUptempestController', function($scope, ngDialog) {
    $scope.cancel = function() {
        $scope.closeThisDialog();
    };
    $scope.submit = function() {
        if ($scope.osUsername != undefined && $scope.osPassword != undefined && $scope.nameSpace != undefined && $scope.osregionName != undefined) {
            $scope.closeThisDialog('cancel');
        }
    };
});