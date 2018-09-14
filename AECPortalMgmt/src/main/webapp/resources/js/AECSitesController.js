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
angular.module('PortalManagement').controller('AECSitesController', function($scope, $http, $sce, ngDialog, $filter, filterFilter,$rootScope,$controller,appContext,$localStorage,$window,$templateCache,Upload) {
	$scope.signOut = "Sign Out";
    $scope.regionHeader = 'Region';
    $scope.SiteHeader = 'Sites';
    $scope.buildHeader = 'Action';
    $scope.buildStatusHeader = 'Build Status';
    $scope.deployHeader = 'Action';
    $scope.deployStatusHeader = 'Deploy Status';
    $scope.sortingOrder = '';
    $scope.reverse = false;
    $scope.filteredItems = [];
    $scope.groupedItems = [];
    $scope.itemsPerPage = 4;
    $scope.pagedItems = [];
    $scope.currentPage = 0;
    $scope.selectionButton = true;
    $scope.size = 10;
    $scope.fileUploadStatus ="";
    $scope.showParameters = false;
    var sortarray = [{field:'region.regionName', direction:'asc'}, {field:'edgeSiteName', direction:'asc'}];
    $scope.tokenId = localStorage.getItem("tokenId");
    $controller('commonController', { $scope: $scope }); 
    $scope.update = function(hostIndex) {
    	for(var i =0;i< $scope.sites.length; i++){
    	$scope.sites[i].selection = false;
    	}
    	console.log($scope.sites.selection);
    if($scope.itemsPerPage > 4){
    	$scope.rowIndex = ($scope.currentPage-1)*$scope.itemsPerPage+hostIndex+1;
        $scope.hostIndex = $scope.rowIndex;
    }
    else{
        $scope.hostIndex = hostIndex;
    }
    $scope.selectionButton = false;
    $scope.sites[$scope.hostIndex].selection = true;
    //$scope.sites[index].viewBuildFile = 'view yaml build file';
    }
    $scope.callblueprint=function(index){
    	//$scope.sites[index].blueprintType = 'Rover';
   }
    
    
    
    
    $scope.uploadFile = function(siteName){
    	/*$scope.sideMenu = true;
    	$scope.sideInfoBar = false;
    	$scope.sideVNFMenu = false;
    	$scope.sideFileMenu = false;
    	$scope.siteIPaddress == null;
		$scope.siteUsername == null;
		$scope.sitePassword == null;
		$scope.file == null;*/
    	//$mdSidenav('right').toggle();
    	var selectedSites = $scope.sites.find(function(element) {
            return element.edgeSiteName === siteName
        });
    	if(selectedSites != undefined){
   	 $scope.siteIPaddress = selectedSites.edgeSiteIP;
   	 $scope.siteUsername = selectedSites.edgeSiteUser;
   	 $scope.sitePassword = selectedSites.edgeSitePwd;
    	}
   	 //console.log($scope.popupSiteIP);
    	ngDialog.open({
            scope: $scope,
            template: 'siteUpload',
            closeByDocument: false,
            controller: 'PopUpUploadController',
            appendClassName: 'ngdialog-custom',
            width: '800px'
        });
    	
    }
    
    
    $scope.loadSitePopup = function(index) {
    	/*$scope.sideInfoBar = true;
    	$scope.sideMenu = false;
    	$scope.sideVNFMenu = false;
    	$scope.sideFileMenu = false;*/
    	if($scope.itemsPerPage > 4){
    	  $scope.rowIndex = ($scope.currentPage-1)*$scope.itemsPerPage+index+1;
          console.log($scope.rowIndex);
          index = $scope.rowIndex;
      	}
      	else{
          index = index;
      	}
        $scope.selectedSites = $scope.sites[index].edgeSiteName;
        $scope.popupregionName = $scope.sites[index].region.regionName;
        $scope.popupsiteName = $scope.selectedSites;
        $scope.popUpedgeSiteBuildStatus = $scope.sites[index].edgeSiteBuildStatus;
        $scope.popUpedgeSiteDeployCreateTarStatus = $scope.sites[index].edgeSiteDeployCreateTarStatus;
        $scope.popUpedgeSiteDeployGenesisNodeStatus = $scope.sites[index].edgeSiteDeployGenesisNodeStatus;
        $scope.popUpedgeSiteDeployToolStatus = $scope.sites[index].edgeSiteDeployDeployToolStatus;
        $scope.popUpbuildDate = $scope.sites[index].buildDate;
        $scope.popUpdeployDate = $scope.sites[index].deployDate;
        $scope.popUpdeployStatus = $scope.sites[index].deployStatus;
        $scope.popUpVnf = $scope.sites[index].vCDNStatus;
        $http({
            method: 'GET',
            url: appContext+'/files/' + $scope.sites[index].edgeSiteName,
            //url: 'http://'+hostUrl+'/AECPortalMgmt/files/' + $scope.sites[index].edgeSiteName,
            headers: {
                "Content-Type": "text/plain",
                'tokenId' : $scope.tokenId
            }
        }).then(function(response) {
            $scope.Data = response.data;
        }, function(error) {});
        ngDialog.open({
            scope: $scope,
            template: 'sitetemplateForm',
            closeByDocument: false,
            controller: 'PopUpSiteController',
            appendClassName: 'ngdialog-custom',
            width: '800px'
        });
    }
    var searchMatch = function(haystack, needle) {
        if (!needle) {
            return true;
        }
        return haystack.ignoreCase().toindexOf(needle.ignoreCase()) !== -1;
    }
    $scope.search = function() {
        $scope.filteredItems = $filter('filter')($scope.sites, function(item) {
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
    var allSitesDisplay = function() {
        $http({
            method: 'GET',
            url: appContext+'/edgeSites/0',
            //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/0',
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
                'tokenId' : $scope.tokenId
            }
        }).then(function(response) {
        	console.log(response.data);
        	 $scope.sites = response.data.sort(function(a, b){
        		    //note the minus before -cmp, for descending order
        		    return $scope.cmp( 
        		        [$scope.cmp(a.region.regionName, b.region.regionName), $scope.cmp(a.edgeSiteName, b.edgeSiteName)], 
        		        [$scope.cmp(b.region.regionName, a.region.regionName), $scope.cmp(b.edgeSiteName, a.edgeSiteName)]
        		    );
        		});
        	 console.log($scope.sites);
            $scope.search();
            $scope.showSitesTable = true;
        }, function(error) {
        	 $scope.errorHandle(error);
        });
    }
    allSitesDisplay();
    $scope.refreshRegionChange = function() {
        $scope.selection = false;
    	$scope.hostIndex = "";
    	$scope.selectedRegion = null;
        allSitesDisplay();
    }
    $scope.selectedRegionChange = function() {
        if ($scope.selectedRegion == null) {
            allSitesDisplay();
        } else {
            $http({
                method: 'GET',
                url: appContext+'/edgeSites/0',
                //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/' + $scope.selectedRegion.regionId,
                headers: {
                    'Content-Type': "application/json",
                    'Accept': "application/json",
                    'tokenId' : $scope.tokenId
                }
            }).then(function(response) {
                $scope.sites = response.data.sort(function(a, b){
        		    //note the minus before -cmp, for descending order
        		    return $scope.cmp( 
        		        [$scope.cmp(a.region.regionName, b.region.regionName), $scope.cmp(a.edgeSiteName, b.edgeSiteName)], 
        		        [$scope.cmp(b.region.regionName, a.region.regionName), $scope.cmp(b.edgeSiteName, a.edgeSiteName)]
        		    );
        		});
               var abc =$scope.sites.filter(function(d) { return d.region.regionId === $scope.selectedRegion.regionId });
              	 $scope.sites = abc;
                $scope.showSitesTable = true;
                $scope.search();
            }, function(error) {
            	$scope.errorHandle(error);
            });
        }
    }
    $scope.buildEdgeSite = function(index) {
    	
        $scope.sites[index].edgeSiteBuildStatus = 'In Progress...';
        $http({
            method: 'POST',
            url: appContext +'/edgeSites/build',
            data: {
            	"sitename": $scope.sites[index].edgeSiteName,
                "filepath": '/opt/akraino/yaml_builds/tools/generate_yamls.sh',
                "targetfolder": '/opt/akraino/yaml_builds/site/'+ $scope.sites[index].edgeSiteName,
                "fileparams": $scope.sites[index].edgeSiteName,
                "blueprint":$scope.sites[index].blueprintType,
            },
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
            }
        }).then(function(response) {
            if (response.data.status == '200') {
                //$scope.sites[index].edgeSiteBuildStatus = 'build complete';
                
                //$scope.buildCompleteDate = new Date();
               // updateEdgeSiteStatus($scope.sites[index].edgeSiteName, $scope.sites[index].buildStatus,buildCompleteDate);
            } else {
                $scope.sites[index].edgeSiteBuildStatus = response.data.message;
                $scope.buildCompleteDate = new Date();
                //updateEdgeSiteStatus($scope.sites[index].edgeSiteName, $scope.sites[index].buildStatus,buildCompleteDate);
            }
        }, function(error) {
        	$scope.sites[index].edgeSiteBuildStatus = "build error..";
        
        });
    }
    $scope.airshipDeploy = function(index){
    	
    	$http({
	            method: 'POST',
	            url: appContext+'/edgeSites/deploy',
	            data: {
	            	 "sitename": $scope.sites[index].edgeSiteName,
	            	 "filepath":"/opt/akraino/redfish/install_server_os.sh  ", 
	            	 "fileparams": "/opt/akraino/redfish/install_server_os.sh --rc /opt/akraino/server-build/"+ $scope.sites[index].edgeSiteName + " --no-confirm", 
	            	 "winscpdir": "/opt/akraino/airshipinabottle_deploy", 
	            	 "winscpfilepath": "mv.sh", 
	            	 "winscpfileparams":$scope.sites[index].edgeSiteIP, 
	            	 "remotserver":$scope.sites[index].edgeSiteIP,
	            	 "port": 22,
	            	 "username": $scope.sites[index].edgeSiteUser,
	            	 "password": $scope.sites[index].edgeSitePwd,
	            	 "destdir":"/opt",
	            	 "remotefilename": "akraino_airship_deploy.sh",
	                 "blueprint":$scope.sites[index].blueprint
	            	},
	            headers: {
	                'Content-Type': "application/json",
	                'Accept': "application/json",
	            }
	        }).then(function(response) {
	            if (response.status == 200) {
	                
	            	/*$http({
	                    method: 'POST',
	                    url: appContext+'/edgeSites/status',
	                    //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/status',
	                    data:{
	                    "siteName": $scope.sites[index].edgeSiteName,
	                    "deployStatus":"In Progress" 
	                    	
	                    },
	                    headers: {
	                        'Content-Type': "application/json",
	                        'Accept': "application/json",
	                        'tokenId' : $scope.tokenId
	                    }
	                }).then(function(response) {
	                	
	                }, function(error) {
	                	$scope.errorHandle(error);
	                });*/
	            } else {
	                $scope.sites[index].deployStatus = response.data.message;
	            }
	        }, function(error) {
	        	$scope.sites[index].deployStatus = 'Deploy error';
	        });
	        
    }
    $scope.deployEdgeSite = function(index) {
    	
    	//console.log(bin2String($scope.sites[index].inputFile));
    	if($scope.sites[index].blueprintType == 'Rover'){
    	     
    		 $scope.sites[index].deployStatus = 'In Progress';
    		 $scope.airshipDeploy(index);
    		 /*$http({
    	            method: 'POST',
    	            url: 'http://'+hostUrl+'/AECPortalMgmt/copyinput',
    	            data: {
    	                "siteName":$scope.sites[index].edgeSiteName,
    	                "blueprint":$scope.sites[index].blueprint
    	                
    	            },
    	            headers: {
    	                'Content-Type': "application/json",
    	                'Accept': "application/json",
    	                'tokenId' : $scope.tokenId
    	            }
    	        }).then(function(response) {
    	        	if (response.data.status == '200') {
    	        		
    	        		
    	            } 
    	        	else{
    	        		$scope.sites[index].deployStatus = 'Deploy error';	
    	        	}
    	        }, function(error) {
    	        	$scope.errorHandle(error);
    	        });*/    
    		 
 	
    	}
    	else{
        $scope.sites[index].deployStatus = 'In Progress';
        $scope.sites[index].edgeSiteDeployCreateTarStatus = 'In Progress';
        $http({
            method: 'POST',
            url: appContext+'/edgeSites/deploy',
            data:  {
            "sitename":$scope.sites[index].edgeSiteName,
            "file1":"/opt/akraino/yaml_builds/tools/1prom-gen.sh", 
            "file1params":$scope.sites[index].edgeSiteName,
            "winscpfilepath":"/opt/akraino/yaml_builds/tools/2genesis.sh",
            "winscpfileparams":$scope.sites[index].edgeSiteName,
            "remotserver":$scope.sites[index].edgeSiteIP,
            "port": 22,
            "username": $scope.sites[index].edgeSiteUser, 
            "password": $scope.sites[index].edgeSitePwd ,
            "destdir1":"/root/akraino/configs/promenade-bundle",
            "remotefile1":"genesis.sh",
            "destdir2":"/root/akraino",
            "remotefile2":"deploy_site.sh",
        	"blueprint":$scope.sites[index].blueprint
        	},
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
            }
        }).then(function(response) {
            if (response.status == 200) {
                //$scope.sites[index].deployStatus = 'Completed';
            } else {
                $scope.sites[index].deployStatus = response.data.message;
            }
        }, function(error) {
        	$scope.sites[index].deployStatus = 'Deploy error';
        });
    }
    }
   $scope.viewYamlBuildFile = function(index) {
    	if($scope.itemsPerPage > 4){
        	$scope.rowIndex = ($scope.currentPage-1)*$scope.itemsPerPage+index+1;
           index = $scope.rowIndex;
        }
        else{
        }
        $http({
            method: 'GET',
            //url: appContext+'/regions/',
            url: appContext+'/edgeSites/files/build/' + $scope.sites[index].edgeSiteName,
            //url: 'http://'+hostUrl+'/AECPortalMgmt/files/' + $scope.sites[index].edgeSiteName,
            headers: {
                "Content-Type": "text/plain",
                'tokenId' : $scope.tokenId
            }
        }).then(function(response) {
            $scope.buildyamloutput = response.data;
            console.log($scope.buildyamloutput);
            loadPopUp();
            
            /*var file = new Blob([response], {type: 'application/text'});
            var fileURL = URL.createObjectURL(file);
            $scope.content = $sce.trustAsResourceUrl(fileURL);
            $scope.viewBuildFileFlag = true;*/
            console.log(response);
        }, function(error) {
        	console.log(error);
        	//$scope.errorHandle(error);
        });
       
    	
    }
    loadPopUp = function() {
    	ngDialog.open({
            scope: $scope,
            template: 'yamltemplateForm',
            closeByDocument: false,
            controller: 'PopUpYamlController',
            appendClassName: 'ngdialog-custom',
            width: '800px',
            height:'450px'
            	
        });
       /* ngDialog.open({
            template: 'yamlbuildfile.html',
            className: 'ngdialog-theme-plain',
            scope: $scope,
            appendClassName: 'ngdialog-custom',
            width: '800px',
            height: '450px',
            data: $scope.buildyamloutput
        });*/
    }
    $scope.viewInputfile = function(index){
    	/*$scope.sideInfoBar = false;
    	$scope.sideMenu = false;
    	$scope.sideVNFMenu = false;
    	$scope.sideFileMenu = true;*/
    	if($scope.itemsPerPage > 4){
      	  $scope.rowIndex = ($scope.currentPage-1)*$scope.itemsPerPage+index+1;
            console.log($scope.rowIndex);
            index = $scope.rowIndex;
        	}
        	else{
            index = index;
        	}
    	$scope.inputFileData = atob($scope.sites[index].inputFile);
    	console.log($scope.inputFileData);
    	
    	 ngDialog.open({
             scope: $scope,
             template: 'inputtemplateForm',
             closeByDocument: false,
             controller: 'PopUpinputFileController',
             appendClassName: 'ngdialog-custom',
             width: '800px',
             height: '450px'
         });
    	
    }
    $scope.updateEdgeSiteStatus = function(siteName, status ,Date) {
        $scope.siteName = siteName;
        $scope.status = status;
        $scope.Date = Date;
        $http({
            method: 'POST',
            url: appContext+'/edgeSites/status',
            //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/status',
            data: {
                "siteName": $scope.siteName,
                "buildStatus": $scope.status,
                "buildDate":$scope.Date
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
    /*$scope.cancel = function(){
    	$scope.sideVNFMenu = false;
    	$scope.sideMenu = false;
    	$scope.sideInfoBar = false;
    	$scope.sideFileMenu = false;
    	
    }*/
   $scope.vnfOnboard = function(index){
	   $scope.popupsiteName = $scope.sites[index].edgeSiteName;
       $scope.popupregionName = $scope.sites[index].region.regionName;
       console.log($scope.popupregionName);
    	/*$scope.sideVNFMenu = true;
    	$scope.sideMenu = false;
    	$scope.sideInfoBar = false;
    	$scope.sideFileMenu = false;*/
    	/*$scope.vnfType ="";
    	$scope.popupsiteName = $scope.sites[index].edgeSiteName;
        $scope.popupregionName = $scope.sites[index].regionName;
        $scope.showParameters = false;
    	$scope.showOnboard = true;
    	$scope.callreadVnf = function(){
    	$scope.readHeatTemplate($scope.vnfType);
    	if($scope.vnfType =="vCDN"){
    	$scope.showParameters = true;
    	$scope.showOnboard = false;
    	}
    	else{
    		$scope.showParameters = false;
    		$scope.showOnboard = true;
    		
    	}
    	}
    	$scope.osDomainname ="Default";
    	$scope.osProjectname ="Default";
    	$scope.osUsername = "admin";
    	$scope.osPassword ="password";
    	$scope.osRegionname = "RegionOne";
    	$scope.osNetworkname = "public";*/
        ngDialog.open({
            scope: $scope,
            template: 'vnftemplateForm',
            closeByDocument: false,
            controller: 'PopUpvnfController',
            appendClassName: 'ngdialog-custom',
            width: '800px'
        });
    }
    $scope.readHeatTemplate = function(vnfName){
    	$scope.vnf = vnfName;
    	//$scope.heattemplateoutput ="hi";
    	 $http({
             method: 'GET',
             url: appContext+'/files/heat/' + $scope.vnf,
             //url: 'http://'+hostUrl+'/AECPortalMgmt/files/heat/' + $scope.vnf,
             headers: {
                 "Content-Type": "text/plain",
                 'tokenId' : $scope.tokenId
             }
         }).then(function(response) {
             $scope.heattemplateoutput = response.data;
         }, function(error) {
         	$scope.errorHandle(error);
         });
    }
    /*$scope.upload = function(index,file){
		if($scope.siteIPaddress == null || $scope.siteUsername == null || $scope.sitePassword == null || $scope.file == null ){
			$scope.displayMessage = "Please provide all the details";
			$scope.showDisplayMessage = true;
		}
		else{
				
		file.upload = Upload.upload({
			 url: appContext+'/edgeSites/upload',
			//url:'http://'+hostUrl+'/AECPortalMgmt/edgeSites/upload',
			method:'POST',
			file:file,
			data:{
				"siteName" :$scope.sites[index].edgeSiteName,
                "blueprint":$scope.sites[index].blueprintType,
                "edgeSiteIP": $scope.siteIPaddress,
                "edgeSiteUser":$scope.siteUsername,
                "edgeSitePwd":$scope.sitePassword
            	},
            headers: {'Content-Type': undefined}
		}).then(function(response){
			if(response.data.statusCode == '200'){
			$scope.sites[index].fileUploadMessage = "File uploaded,successfully.";
			$scope.sites[index].fileUploadStatus = "Completed";
			$scope.refreshRegionChange();
			
			
			
			}
			else{
				$scope.sites[index].fileUploadMessage = response.data.message;	
			}
	
		},function(error){
			$scope.sites[index].fileUploadMessage = "Error";
		});
		 //$scope.closeThisDialog('cancel');
		$scope.showDisplayMessage =  false;
		 $scope.sideMenu = false;
		
	} 
	}*/
    /*$scope.onBoard = function(index){
    	$scope.sites[index].vCDNStatus = "In Progress.."
    	$scope.fileparams = "OS_USER_DOMAIN_NAME="+$scope.osDomainname+" OS_PROJECT_DOMAIN_NAME="+$scope.osProjectname+" OS_USERNAME="+$scope.osUsername+" OS_PASSWORD="+$scope.osPassword+" OS_REGION_NAME="+$scope.osRegionname+" NETWORK_NAME="+$scope.osNetworkname;
    	$http({	
         method: 'POST',
         url: appContext+'/edgeSites/onboardVNF',
         data: {
        	 "sitename": $scope.sites[index].edgeSiteName,
             "remoteserver": $scope.sites[index].edgeSiteIP,
             "username": $scope.sites[index].edgeSiteUser,
             "password": $scope.sites[index].edgeSitePwd,
             "portnumber": 22,
             "srcdir": "/opt/akraino/sample_vnf",
             "destdir": "/opt",
             "filename": "run_ats-demo.sh",
             "fileparams": $scope.fileparams,
             "noofiterations": 0,
             "waittime": 15,
             "filetrasferscript":"/opt/akraino/sample_vnf/mv.sh",
             "filetransferparams": $scope.sites[index].edgeSiteIP
         },
         headers: {
             'Content-Type': "application/json",
             'Accept': "application/json",
         }
    		
    	        }).then(function(response) {
    	        	if (response.status == 200) {
    	        		//$scope.$parent.sites[index].vCDNStatus = 'Completed';
    	        		/*$http({
    	                    method: 'POST',
    	                    url: appContext+'/edgeSites/status',
    	                    //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/status',
    	                    data:{
    	                    "siteName": $scope.sites[index].edgeSiteName,
    	                    "vCDNStatus":"In Progress" 
    	                    	
    	                    },
    	                    headers: {
    	                        'Content-Type': "application/json",
    	                        'Accept': "application/json",
    	                        'tokenId' : $scope.tokenId
    	                    }
    	                }).then(function(response) {
    	                	
    	                }, function(error) {
    	                	$scope.errorHandle(error);
    	                });*/
    	            /*} 
    	        	else{
    	        		$scope.sites[index].vCDNStatus = "Error"
    	        	}
    	        }, function(error) { 
    	        	$scope.sites[index].vCDNStatus = "Error"
    	        });
            //$scope.closeThisDialog('cancel');
    	$scope.sideVNFMenu = false;
    	}*/
	

    $http({
        method: 'GET',
        url: appContext+'/regions/',
        //url: 'http://'+hostUrl+'/AECPortalMgmt/regions/',
        headers: {
            'Content-Type': "application/json",
            'Accept': "application/json",
            'tokenId' : $scope.tokenId
        }
    }).then(function(response) {
        $scope.regions = response.data;
    }, function(error) {
    	$scope.errorHandle(error);
    });
    
});
angular.module('PortalManagement').controller('PopUpSiteController', function($scope,$http, ngDialog) {
	$scope.cancel = function() {
        $scope.closeThisDialog();
    };
});
angular.module('PortalManagement').controller('PopUpinputFileController', function($scope,$http, ngDialog) {
	$scope.cancel = function() {
        $scope.closeThisDialog();
    };
});
angular.module('PortalManagement').controller('PopUpYamlController', function($scope,$http, ngDialog) {
	$scope.cancel = function() {
        $scope.closeThisDialog();
    };
});
angular.module('PortalManagement').controller('PopUpvnfController', function($scope,$http, ngDialog,$localStorage,appContext) {
	$scope.showParameters = false;
	$scope.showOnboard = true;
	$scope.callreadVnf = function(){
	$scope.$parent.readHeatTemplate($scope.vnfType);
	if($scope.vnfType =="vCDN"){
	$scope.showParameters = true;
	$scope.showOnboard = false;
	}
	else{
		$scope.showParameters = false;
		$scope.showOnboard = true;
		
	}
	}
	$scope.osDomainname ="Default";
	$scope.osProjectname ="Default";
	$scope.osUsername = "admin";
	$scope.osPassword ="password";
	$scope.osRegionname = "RegionOne";
	$scope.osNetworkname = "public";
	$scope.onBoard = function(index){
		$scope.sites[index].vCDNStatus = "In Progress.."
		$scope.fileparams = "OS_USER_DOMAIN_NAME="+$scope.osDomainname+" OS_PROJECT_DOMAIN_NAME="+$scope.osProjectname+" OS_USERNAME="+$scope.osUsername+" OS_PASSWORD="+$scope.osPassword+" OS_REGION_NAME="+$scope.osRegionname+" NETWORK_NAME="+$scope.osNetworkname;
	console.log("fileparams" + $scope.fileparams);
	$http({	
     method: 'POST',
     url: appContext+'/edgeSites/onboardVNF',
     data: {
    	 "sitename": $scope.sites[index].edgeSiteName,
         "remoteserver": $scope.sites[index].edgeSiteIP,
         "username": $scope.sites[index].edgeSiteUser,
         "password": $scope.sites[index].edgeSitePwd,
         "portnumber": 22,
         "srcdir": "/opt/akraino/sample_vnf",
         "destdir": "/opt",
         "filename": "run_ats-demo.sh",
         "fileparams": $scope.fileparams,
         "noofiterations": 0,
         "waittime": 15,
         "filetrasferscript":"/opt/akraino/sample_vnf/mv.sh",
         "filetransferparams": $scope.sites[index].edgeSiteIP
     },
     headers: {
         'Content-Type': "application/json",
         'Accept': "application/json",
     }
		
	        }).then(function(response) {
	        	if (response.status == 200) {
	        		//$scope.$parent.sites[index].vCDNStatus = 'Completed';
	        		/*$http({
	                    method: 'POST',
                            url: appContext+'/edgeSites/status',
	                    //url: 'http://'+hostUrl+'/AECPortalMgmt/edgeSites/status',
	                    data:{
	                    "siteName": $scope.sites[index].edgeSiteName,
	                    "vCDNStatus":"In Progress" 
	                    	
	                    },
	                    headers: {
	                        'Content-Type': "application/json",
	                        'Accept': "application/json",
	                        'tokenId' : $scope.tokenId
	                    }
	                }).then(function(response) {
	                	
	                }, function(error) {
	                	$scope.errorHandle(error);
	                });*/
	            } 
	        	else{
	        		$scope.sites[index].vCDNStatus = "Error"
	        	}
	        }, function(error) { 
	        	$scope.sites[index].vCDNStatus = "Error"
	        });
        $scope.closeThisDialog('cancel');
	}
	$scope.cancel = function() {
        $scope.closeThisDialog();
    };
});
angular.module('PortalManagement').controller('PopUpUploadController', function($scope,$http, ngDialog,$localStorage,appContext,Upload) {
	
	$scope.upload = function(index,file){
		
  		file.upload = Upload.upload({
                        url: appContext+'/edgeSites/upload',
			//url:'http://'+hostUrl+'/AECPortalMgmt/edgeSites/upload',
			method:'POST',
			file:file,
			data:{
				"siteName" :$scope.sites[index].edgeSiteName,
                "blueprint":$scope.sites[index].blueprintType,
                "edgeSiteIP": $scope.siteIPaddress,
               
                "edgeSiteUser":$scope.siteUsername,
                "edgeSitePwd":$scope.sitePassword,
                "deploymentMode":$scope.deploymentMode
            	
            },
            headers: {'Content-Type': undefined}
		}).then(function(response){
			if(response.data.statusCode == '200'){
			$scope.sites[index].fileUploadMessage = "File uploaded,successfully.";
			$scope.sites[index].fileUploadStatus = "Completed";
			$scope.refreshRegionChange();
			console.log(response.statusCode);
			}
			else{
				$scope.sites[index].fileUploadMessage = response.data.message;	
			}
	
		},function(error){
			$scope.sites[index].fileUploadMessage = "Error";
		});
		 $scope.closeThisDialog('cancel');
		 
	}
	$scope.cancel = function() {
        $scope.closeThisDialog();
    };

	
});
