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
var AECPortalController = angular.module('PortalManagement', ['ngDialog', 'ui.router', 'base64','myApp.config','ngStorage','ui.bootstrap', 'ngResource','ngFileUpload','ngMaterial']);
AECPortalController.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login')
    $stateProvider
        .state('common', {
            templateUrl: 'views/indexMain.html',
            abstract: true,
        })
        .state('login', {
            url: "/login",
            controller: 'login',
            templateUrl: 'views/login.html'
        })
        .state('sites', {
            url: "/sites",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECSitesController',
                    templateUrl: 'views/sites.html'
                }
            }
        })
        .state('pods', {
            url: "/pods",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECPodsController',
                    templateUrl: 'views/createPods.html'
                }
            }
        })
        .state('sitebuild', {
            url: "/sitebuild",
            params: {
                siteInfo: null
              },
            parent: "common",
            views: {
                "main": {
                    controller: 'AECSiteBuildController',
                    
                    templateUrl: 'views/siteBuilds.html'
                    
                }
            }
        })
        .state('hardware', {
            url: "/hardware",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECHardwareController',
                    templateUrl: 'views/hardware.html'
                }
            }
        })
        .state('software', {
            url: "/software",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECSoftwareController',
                    templateUrl: 'views/software.html'
                }
            }
        })
        .state('config', {
            url: "/config",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECConfigController',
                    templateUrl: 'views/config.html'
                }
            }
        })
        .state('addons', {
            url: "/addons",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECaddOnsController',
                    templateUrl: 'views/addons.html'
                }
            }
        })
        .state('eteOps', {
            url: "/eteOps",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECeteOpsController',
                    templateUrl: 'views/eteOps.html'
                }
            }
        })
        .state('eteSec', {
            url: "/eteSec",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECeteSecController',
                    templateUrl: 'views/eteSec.html'
                }
            }
        })
        .state('Inv', {
            url: "/Inv",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECInvController',
                    templateUrl: 'views/Inv.html'
                }
            }
        })
        
        .state('eteTest', {
            url: "/eteTest",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECeteTestController',
                    templateUrl: 'views/eteTest.html'
                }
            }
        })
        .state('siteBuilds', {
            url: "/siteBuilds",
            
            
            
            parent: "common",
            views: {
                "main": {
                    controller: 'AECsiteBuildsController',
                    templateUrl: 'views/siteBuilds.html'
                }
            }
        })
        .state('tools', {
            url: "/tools",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECtoolsController',
                    templateUrl: 'views/toolbox.html'
                }
            }
        })
        .state('newSite', {
            url: "/newSite",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECnewSiteController',
                    templateUrl: 'views/newSite.html'
                }
            }
        })
        .state('eteChomp', {
            url: "/eteChomp",
            parent: "common",
            views: {
                "main": {
                    controller: 'AECeteChompController',
                    templateUrl: 'views/eteChomp.html'
                }
            }
        })
        
});
/*var unloadFunction = function( event){
    event.returnValue = "do you really want to leave this page";
    alert("unloading");
};
window.addEventListener('beforeunload', unloadFunction); */


AECPortalController.factory('beforeUnload', function ($rootScope, $window) {
    // Events are broadcast outside the Scope Lifecycle
    
    $window.onbeforeunload = function (e) {
        var confirmation = {};
        var event = $rootScope.$broadcast('onBeforeUnload', confirmation);
        if (event.defaultPrevented) {
            return confirmation.message;
        }
    };
    
    $window.onunload = function () {
        $rootScope.$broadcast('onUnload');
    };
    return {};
})
.run(function (beforeUnload) {
    // Must invoke the service at least once
});



AECPortalController.controller('login',function($scope, $http, $filter, filterFilter, $state, $base64,$rootScope,$controller,appContext) {
    //$scope.userid;
    //$scope.password;
    //$scope.signIn = "Sign In";
	//console.log(appConfig.apiRoot);
	$rootScope.tokenId ="";
    $scope.usernameVal = '';
    $scope.passwordVal = '';
    	$rootScope.message = "Please enter credentials";
    $scope.$state = $state;
    
    var baseURL = window.location.protocol + '//' + window.location.host;
    console.log('Base URL for current frame is: ' + baseURL);
    
    //$controller('commonController', { $scope: $scope }); 
    $scope.goLogin = function() {
        var arr = $scope.passwordVal;
        //$state.transitionTo('sites');
        if ($scope.usernameVal == '' && $scope.passwordVal == '') {
            $scope.userMessage = 'Please enter a username.';
            $scope.passwordMessage = 'Please enter a password.';
        } else if ($scope.usernameVal == '') {
            $scope.userMessage = 'Please enter a username.';
            $scope.passwordMessage = '';
        } else if ($scope.passwordVal == '') {
            $scope.passwordMessage = 'Please enter a password.';
            $scope.userMessage = '';
        } else if (arr.length < 6) {
            $scope.passwordMessage = 'Please enter a valid password.';
            $scope.userMessage = '';
        } 
       else {
    	   $scope.passwordMessage = '';
    	   $scope.userMessage ='';
            var userPwd = $scope.usernameVal + ":" + $scope.passwordVal;
            var auth = $base64.encode(userPwd);
            $http({
                method: 'POST',
                url: appContext+'/login',
                //url: 'http://'+hostUrl+'/AECPortalMgmt/login',
                headers: {
                    'Authorization': "Basic " + auth,
                    'Content-Type': "application/json",
                    'Accept': "application/json"
                },
                data: {
                }
            }).
            then(function(response) {
                if (response.data.statusCode == 200) {
                	$rootScope.tokenId = response.data.tokenId;
                	localStorage.setItem("tokenId",response.data.tokenId);
                    $state.transitionTo('sites');
                }
                else if (response.data.statusCode == 401){
                	$scope.passwordVal= null;
                	$scope.passwordMessage = 'Invalid Credentials, please try again...';
                	
                	localStorage.removeItem("tokenId");
                }
            }, function(error) {
                if (error.status == 401) {
                    $scope.passwordMessage = 'Invalid Credentials, please try again...';
                    $scope.passwordVal ="";
                    localStorage.removeItem("tokenId");
                } else if (error.status  == 400) {
                    $scope.passwordMessage = 'Session Invalid, please login again...';
                    $scope.passwordVal ="";
                    localStorage.removeItem("tokenId");
                }
                else if (error.status == 307) {
                    $scope.passwordMessage = 'Session expired,Please try again...';
                    $scope.passwordVal ="";
                    localStorage.removeItem("tokenId");
                }
            });
            //$state.transitionTo('sites');
        }
    }
    $scope.goLogout = function() {
    	/*AECPortalController.run(function($rootScope,$templateCache){
    		$rootScope.$on('$viewContentLoaded',function(){
    			$templateCache.remove('views/sites.html')
    			//$window.location.reload();
    		});
    		});*/
    	//$scope.tokenId = $rootScope.tokenId;
        $http({
            method: 'POST',
            url: appContext+'/logout',
            //url: 'http://'+hostUrl+'/AECPortalMgmt/logout',
            headers: {
                'Content-Type': "application/json",
                'Accept': "application/json",
                'tokenId' : $rootScope.tokenId
            },
        data:{
        }
            /* data: {
                 'username': $scope.usernameVal,
                 'passowrd': $scope.passwordVal
             }*/
        }).then(function(response) {
            if (response.data.statusCode == 200) {
            	//$cookies.remove("JSESSIONID");
            	$rootScope.tokenId ="";
            	localStorage.removeItem("tokenId");
                $state.transitionTo('login');
                $rootScope.message = 'User logged out, please login again...';
            }
        }, function(response) {
            $scope.message = 'Unknown error,Try again later';
        });
    }
});

