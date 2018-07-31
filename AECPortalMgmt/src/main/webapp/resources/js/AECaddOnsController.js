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
angular.module('PortalManagement').controller('AECaddOnsController', function($scope, $http, $sce, ngDialog, $filter,$rootScope,$controller,hostUrl,camundaUrl) {
    $scope.regionHeader = 'Region';
    $scope.SiteHeader = 'Sites';
    $scope.Action = 'Action';
    $scope.signOut = "Sign Out";
    $scope.sortingOrder = '';
    $scope.reverse = false;
    $scope.filteredItems = [];
    $scope.groupedItems = [];
    $scope.itemsPerPage = 6;
    $scope.pagedItems = [];
    $scope.currentPage = 0;
    $scope.addOnselectionButton = true;
    $scope.tokenId =localStorage.getItem("tokenId");
    $controller('commonController', { $scope: $scope }); 
    var searchMatch = function(haystack, needle) {
        if (!needle) {
            return true;
        }
        return haystack.ignoreCase().toindexOf(needle.ignoreCase()) !== -1;
    }
    $scope.search = function() {
        $scope.filteredItems = $filter('filter')($scope.addOnsites, function(item) {
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
    $scope.uploadOnapfile = function(index){
    	
    	ngDialog.open({
            scope: $scope,
            template: 'addOnUploadForm',
            closeByDocument: false,
            controller: 'addOnUploadController',
            appendClassName: 'ngdialog-custom',
            width: '800px'
        });
    	
    }
    var alladdOnsSitesDisplay = function() {
        $http({
            method: 'GET',
            url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/0',
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
                'tokenId' : $scope.tokenId
            }
        }).then(function(response) {
            $scope.addOnsites = response.data;
            $scope.search();
            $scope.showaddOnSitesTable = true;
        }, function(error) {});
    }
    alladdOnsSitesDisplay();
    $scope.refreshRegionChange = function() {
        alladdOnsSitesDisplay();
    }
    $scope.selectedaddOnRegionChange = function() {
        if ($scope.selectedaddOnRegion == null) {
            alladdOnsSitesDisplay();
        } else {
            $http({
                method: 'GET',
                url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/' + $scope.selectedaddOnRegion.regionId,
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json",
                    'tokenId' : $scope.tokenId
                }
            }).then(function(response) {
                $scope.addOnsites = response.data;
                $scope.showaddOnSitesTable = true;
                $scope.search();
            }, function(error) {
            	$scope.errorHandle(error);
            });
        }
    }
    $scope.addOnUpdate = function(index) {
    	if($scope.itemsPerPage > 6){
    	    $scope.rowIndex = ($scope.currentPage-1)*$scope.itemsPerPage+index+1;
    	        console.log($scope.rowIndex);
    	        $scope.siteIndex  = $scope.rowIndex;
    	    	}
    	    	else{
    	    		$scope.siteIndex = index;
    	    	}
    	        
    	//$scope.rowIndex = ($scope.currentPage-1)*$scope.itemsPerPage+index+1;
        //$scope.siteIndex =  index;
    	$scope.addOnsites[$scope.siteIndex].addOnselection = true;
        $scope.addOnselectionButton = false;
    }
    $scope.upDateplaceHolder = function(Data){
    	$scope.readOnapData = Data;
    	$scope.httpProxy = $scope.readOnapData.httpProxy;
        $scope.httpsProxy = $scope.readOnapData.httpsProxy;
        $scope.noProxy = $scope.readOnapData.noProxy;
        $scope.pubKey = $scope.readOnapData.onapVMPubKey;
        $scope.onapRepo = $scope.readOnapData.onapRepo;
        $scope.netName = $scope.readOnapData.publicNetName;
        $scope.cidr = $scope.readOnapData.publicSubnetCIDR;
        $scope.allocStart = $scope.readOnapData.publicSubnetAllocStart;
        $scope.allocEnd = $scope.readOnapData.publicSubnetAllocEnd;
        $scope.dnsName = $scope.readOnapData.publicSubnetDNSNameServer;
        $scope.gatewayIP = $scope.readOnapData.publicSubnetGtwyIP;
        //$scope.phingressController= $scope.readOnapData.publicSubnetGtwyIP;*/
        //console.log();
    }
    $scope.installOnap = function(siteIndex) {
    	$scope.addOnsites[siteIndex].onapStatus = 'In Progress...';
    	
        $http({
            method: 'POST',
            url: 'http://'+camundaUrl+'/onap/',
            data: {
            	 "sitename": $scope.addOnsites[siteIndex].edgeSiteName,
                "remoteserver": $scope.addOnsites[siteIndex].edgeSiteIP,
                "username": $scope.addOnsites[siteIndex].edgeSiteUser,
                "password": $scope.addOnsites[siteIndex].edgeSitePwd,
                "portnumber": 22,
                "srcdir": "/opt/akraino/onap/", 
                "destdir": "/opt",
                "filename": "INSTALL.sh",
                "deploymentverifier": "test_status.sh",
                "noofiterations": 0,
                "waittime": 15,
                "filetrasferscript": "/opt/akraino/onap/mv.sh", 
                "filetransferparams": $scope.addOnsites[siteIndex].edgeSiteIP,
            },
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
            }
        }).then(function(response) {
        	console.log(response.status);
        	if (response.status == 200) {
          
            $http({
                method: 'POST',
                url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/status',
                data:{
                "siteName": $scope.addOnsites[siteIndex].edgeSiteName,
                "onapStatus":"In Progress" 
                	
                },
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json",
                    'tokenId' : $scope.tokenId
                }
            }).then(function(response) {
            	
            }, function(error) {
            	$scope.errorHandle(error);
            });
        	}
        	else{
        		$scope.addOnsites[siteIndex].onapStatus = response.data.message;
        	}
        }, function(error) { 
        	$scope.addOnsites[siteIndex].onapStatus = 'Install Error...';
        });
    }
    $scope.openDialog = function(index) {
    	if($scope.itemsPerPage > 6){
    	    $scope.rowIndex = ($scope.currentPage-1)*$scope.itemsPerPage+index+1;
    	        console.log($scope.rowIndex);
    	        index  = $scope.rowIndex;
    	    	}
    	    	else{
    	    		index = index;
    	   }
        $scope.siteName = $scope.addOnsites[index].edgeSiteName;
        $scope.readOnapData = "";
        $http({
            method: 'GET',
            url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/onap/' + $scope.siteName,
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
                'tokenId' : $scope.tokenId
            }
        }).then(function(response) {
        	$scope.upDateplaceHolder(response.data);
        }, function(error) {
        	$scope.errorHandle(error);
        });
        ngDialog.open({
            scope: $scope,
            template: 'addOnstemplateForm',
            closeByDocument: false,
            controller: 'PopUpController',
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
        $scope.addOnregions = response.data;
       
    }, function(error) {
    	$scope.errorHandle(error);
    });
});
angular.module('PortalManagement').controller('PopUpController', function($scope,$http, ngDialog,hostUrl) {
//scope.siteName = $scope.addOnsites[index].edgeSiteName;
	//nsole.log("hi" +$scope.siteName);
    $scope.cancel = function() {
        $scope.closeThisDialog();
    };
    $scope.submit = function() {
        if ($scope.allocEnd != undefined && $scope.httpsProxy != undefined && $scope.httpsProxy != undefined && $scope.noProxy != undefined && $scope.pubKey != undefined && $scope.onapRepo != undefined && $scope.netName != undefined && $scope.cidr != undefined && $scope.allocStart != undefined && $scope.dnsName != undefined && $scope.gatewayIP != undefined)
        {
            $scope.closeThisDialog('cancel');
            $http({
                method: 'POST',
                url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/onap/' + $scope.siteName,
                data: {
                	"siteName": $scope.siteName,
                		"publicNetName":$scope.netName,
                		"publicSubnetCIDR":$scope.cidr,
                		"publicSubnetAllocStart":$scope.allocStart,
                		"publicSubnetAllocEnd":$scope.allocEnd,
                		"publicSubnetDNSNameServer":$scope.dnsName,
                		"publicSubnetGtwyIP":$scope.gatewayIP ,
                		"onapVMPubKey": $scope.pubKey,
                		"onapRepo":$scope.onapRepo,
                		"httpProxy":$scope.httpProxy,
                		"httpsProxy":$scope.httpsProxy,
                		"noProxy": $scope.noProxy
                },
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json",
                    'tokenId' : $scope.$parent.tokenId
                }
            }).then(function(response) {
            }, function(error) {
            	$scope.errorHandle(error);
            });
        }
    };
});
angular.module('PortalManagement').controller('addOnUploadController', function($scope,$http, ngDialog,$localStorage,hostUrl,Upload) {
	$scope.addOnUpload = function(index,file){
				
		file.upload = Upload.upload({
			url:'http://'+hostUrl+'/AECPortalMgmt/addon/onap/upload',
			method:'POST',
			file:file,
			data:{
				"siteName" :$scope.addOnsites[index].edgeSiteName
                
              
            },
            headers: {'Content-Type': undefined}
		}).then(function(response){
			if(response.data.statusCode == '200'){
			$scope.addOnsites[index].fileUploadMessage = "File uploaded,successfully.";
			$scope.addOnsites[index].fileUploadStatus = "Completed";
			console.log(response.statusCode);
			}
		},function(response){
			console.log(response);
		});
		 $scope.closeThisDialog('cancel');
		 
	}
	$scope.cancel = function() {
        $scope.closeThisDialog();
    };

});