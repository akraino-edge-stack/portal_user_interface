angular.module('PortalManagement').controller('AECnewSiteController', function($scope, $http, $sce, ngDialog, $filter, filterFilter,$rootScope,$controller,appContext,$localStorage,$window,$templateCache,Upload,$state) {
	$controller('commonController', { $scope: $scope });
	$scope.showSiteDetails = false;
	$scope.siteListtatus = "Build";
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
        	//console.log(response.data);
        	 $scope.siteList = response.data.sort(function(a, b){
        		    //note the minus before -cmp, for descending order
        		    return $scope.cmp( 
        		        [$scope.cmp(a.region.regionName, b.region.regionName), $scope.cmp(a.edgeSiteName, b.edgeSiteName)], 
        		        [$scope.cmp(b.region.regionName, a.region.regionName), $scope.cmp(b.edgeSiteName, a.edgeSiteName)]
        		    );
        		});
        	
           // $scope.search();
           
        }, function(error) {
        	 $scope.errorHandle(error);
        });
    }
allSitesDisplay();
$scope.refreshRegionChange = function() {
   
    allSitesDisplay();
}
$scope.addNewSite = function(){
	ngDialog.open({
        scope: $scope,
        template: 'createSiteForm',
        closeByDocument: false,
        controller: 'popUpcreateSiteController',
        appendClassName: 'ngdialog-custom',
        width: '800px'
    });
}
$scope.createPod = function(siteInfo){
	
	$state.go('sitebuild', {siteInfo: siteInfo})
}
$scope.showSelectedSite = function(index){
	$scope.showSiteDetails = true;
	$scope.selectedSiteName = $scope.siteList[index].edgeSiteName;
	$scope.selectedRegionName = $scope.siteList[index].region.regionName;
	$scope.selectedBlueprint =  $scope.siteList[index].blueprint;
	$scope.selectedpodName = $scope.siteList[index].podName;
	$scope.selectedpodType = $scope.siteList[index].podType;
	
}

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
$scope.deploySite = function(index){
	 $scope.siteList[index].deployStatus = 'In Progress';
     $scope.siteList[index].edgeSiteDeployCreateTarStatus = 'In Progress';
     $http({
         method: 'POST',
         url: appContext+'/edgeSites/deploy',
         data:  {
         "sitename":$scope.siteList[index].edgeSiteName,
         "file1":"/opt/akraino/yaml_builds/tools/1prom-gen.sh", 
         "file1params":$scope.siteList[index].edgeSiteName,
         "winscpfilepath":"/opt/akraino/yaml_builds/tools/2genesis.sh",
         "winscpfileparams":$scope.siteList[index].edgeSiteName,
         "remoteserver":$scope.siteList[index].edgeSiteIP,
         "port": 22,
         "username": $scope.siteList[index].edgeSiteUser, 
         "password": $scope.siteList[index].edgeSitePwd ,
         "destdir1":"/root/akraino/configs/promenade-bundle",
         "remotefile1":"genesis.sh",
         "destdir2":"/root/akraino",
         "remotefile2":"deploy_site.sh",
     	"blueprint":$scope.siteList[index].blueprint
     	},
         headers: {
             'Content-Type': "application/json",
             'Accept': "application/json",
         }
     }).then(function(response) {
         if (response.status == 200) {
             //$scope.siteList[index].deployStatus = 'Completed';
         } else {
             $scope.siteList[index].deployStatus = response.data.message;
         }
     }, function(error) {
     	$scope.siteList[index].deployStatus = 'Deploy error';
     });
}
$scope.buildEdgeSite = function(index) {
	
    $scope.siteList[index].edgeSiteBuildStatus = 'In Progress...';
    $http({
        method: 'POST',
        url: appContext +'/edgeSites/build',
        data: {
        	"sitename": $scope.siteList[index].edgeSiteName,
            "filepath": '/opt/akraino/yaml_builds/tools/generate_yamls.sh',
            "targetfolder": '/opt/akraino/yaml_builds/site/'+ $scope.siteList[index].edgeSiteName,
            "fileparams": $scope.siteList[index].edgeSiteName,
            "blueprint":$scope.siteList[index].blueprint,
        },
        headers: {
            'Content-Type': "application/json",
            'Accept': "application/json",
        }
    }).then(function(response) {
        if (response.status == '200') {
            //$scope.siteList[index].edgeSiteBuildStatus = 'build complete';
            
            //$scope.buildCompleteDate = new Date();
           // updateEdgeSiteStatus($scope.siteList[index].edgeSiteName, $scope.siteList[index].buildStatus,buildCompleteDate);
        } else {
            $scope.siteList[index].edgeSiteBuildStatus = response.data.message;
           // $scope.buildCompleteDate = new Date();
            //updateEdgeSiteStatus($scope.siteList[index].edgeSiteName, $scope.siteList[index].buildStatus,buildCompleteDate);
        }
    }, function(error) {
    	$scope.siteList[index].edgeSiteBuildStatus = "build error..";
    
    });
}
var loadPopUp = function() {
	ngDialog.open({
        scope: $scope,
        template: 'yamltemplateForm',
        closeByDocument: false,
        controller: 'PopUpYamlController',
        appendClassName: 'ngdialog-custom',
        width: '800px',
        height:'450px'
        	
    });
}
$scope.viewYamlBuildFile = function(index) {
	
    $http({
        method: 'GET',
        //url: appContext+'/regions/',
        url: appContext+'/edgeSites/files/build/' + $scope.siteList[index].edgeSiteName,
        //url: 'http://'+hostUrl+'/AECPortalMgmt/files/' + $scope.siteList[index].edgeSiteName,
        headers: {
            "Content-Type": "text/plain",
            'tokenId' : $scope.tokenId
        }
    }).then(function(response) {
        $scope.buildyamloutput = response.data;
        //console.log($scope.buildyamloutput);
        loadPopUp();
        
        /*var file = new Blob([response], {type: 'application/text'});
        var fileURL = URL.createObjectURL(file);
        $scope.content = $sce.trustAsResourceUrl(fileURL);
        $scope.viewBuildFileFlag = true;*/
        //console.log(response);
    }, function(error) {
    	//console.log(error);
    	//$scope.errorHandle(error);
    });
   
	
}
});
angular.module('PortalManagement').controller(
		  'popUpcreateSiteController', function($scope,$http,appContext,Upload) {
			  $scope.createSite = function(){
					$http({	
					     method: 'POST',
					     url: appContext+'/edgeSites/site',
					     data: {
					    	 name:$scope.siteName,
					    	 regionId:$scope.selectedRegion.regionId,
					    	 location:$scope.siteLocation
					    	 
					    	
					     },
					     headers: {
					         'Content-Type': "application/json",
					         'Accept': "application/json",
					     }
							
						        }).then(function(response) {
						        	if (response.status == 200) {
						        		$scope.refreshRegionChange();	
						            } 
						        	else{
						        		
						        	}
						        }, function(error) { 
						        	
						        });
					        $scope.closeThisDialog('cancel');
						}
				
		  });			  
		  
