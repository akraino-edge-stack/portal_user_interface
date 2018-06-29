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
angular.module('PortalManagement').controller('commonController', function($scope, $http, $sce, ngDialog, $filter,$rootScope,$state) {
  $scope.errorHandle = function(error){
	  if (error.status == 400) {
		  localStorage.removeItem("tokenId");
		  $state.transitionTo('login');
		  localStorage.removeItem("tokenId");
		  $rootScope.message = 'Session Invalid, please login again...';
      } else if (error.status== 401) {
    	  localStorage.removeItem("tokenId");
    	  $state.transitionTo('login');
    	  $rootScope.message = 'Invalid Credentials, please try again...';
      }
      else if (error.status == 307) {
    	  localStorage.removeItem("tokenId");
    	  $state.transitionTo('login');
    	  $rootScope.message = 'Session expired,Please try again...';
      }
  }
});