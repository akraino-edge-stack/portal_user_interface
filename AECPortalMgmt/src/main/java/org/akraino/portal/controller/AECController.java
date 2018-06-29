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
package org.akraino.portal.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.akraino.portal.common.DBConnection;
import org.akraino.portal.common.LDAPAuthentication;
import org.akraino.portal.common.LoginUtil;
import org.akraino.portal.common.StringUtil;
import org.akraino.portal.data.AuthenticationResponse;
import org.akraino.portal.data.UserSession;
import org.akraino.portal.service.AkrainoSiteService;
import org.akraino.portal.service.RegionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AECController {

	private static final Logger logger = Logger.getLogger(AECController.class);
	
	@Autowired
	RegionService regionService;
	
	@Autowired
	LDAPAuthentication ldapAuth;
	
	@RequestMapping(value = "/files/build/{siteName}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getBuildYamlFile(@PathVariable("siteName") String siteName) {
		
		logger.error("in view yaml file");
		AkrainoSiteService akrainoSiteService = new AkrainoSiteService(); 
		String generatedYamlFileContent = null;
	    try {
	    	generatedYamlFileContent = akrainoSiteService.getYamlContent(siteName);
		} catch (Exception e) {
			logger.error("error viewing yaml file:" + e);
		}
	   
	    
	    return new ResponseEntity<String>(generatedYamlFileContent, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/files/heat/{vnfName}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getHeatTemplateFile(@PathVariable("vnfName") String vnfName) {
		
		logger.error("get heat template file for VNF");
		AkrainoSiteService akrainoSiteService = new AkrainoSiteService(); 
		String heatFileContent = null;
	    try {
	    	heatFileContent = akrainoSiteService.getHeatContent(vnfName);
		} catch (Exception e) {
			logger.error("error - get heat template file for VNF:" + e);
		}
	   
	    
	    return new ResponseEntity<String>(heatFileContent, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> login(@RequestHeader(value="Authorization") String base64Auth,
			HttpServletRequest req) {
		
		
		AuthenticationResponse response = new AuthenticationResponse();
		
		AkrainoSiteService akrainoSiteService = new AkrainoSiteService(); 
		
	    try {

	    	base64Auth = base64Auth.replaceAll("Basic ", "");
	    	
	    	String userPwd = LoginUtil.decode(base64Auth);
	    	
	    	String username = LoginUtil.getUserName(userPwd);
	    	
	    	String pwd = LoginUtil.getPassword(userPwd);
	    	
	    	response = ldapAuth.authenticateUser(username, pwd);
	    	
	    	if (response.getMessage().equals(LDAPAuthentication.AUTHORIZED)) {
	    		
	    		// Authentication successful, valid user
	    		
	    		UserSession user = akrainoSiteService.getUserSession(username);
	    		
	    		HttpSession session = req.getSession(true);
	    		
	    		String sessionId = session.getId();
	    		
	    		String sessionToken = LoginUtil.encode(username) + ":" + sessionId;
	    		
	    		session.setAttribute("sessionToken", sessionToken);

	    		if (StringUtil.notEmpty(user.getTokenId())) {
	    			
	    			// user session exists, re-activate session
	    			akrainoSiteService.updateUserSession(username, sessionId);
	    			
	    		} else {
		    		// create new user session
		    		akrainoSiteService.createUserSession(username, sessionId);
	    		}

	    		response.setStatusCode("200");
		    	response.setTokenId(sessionToken);
		    	response.setMessage(LDAPAuthentication.AUTHORIZED);
		    	
	    	} else {
	    		// Authentication failed, invalid user
	    		response.setStatusCode("401");
		    	response.setTokenId(null);
		    	response.setMessage(LDAPAuthentication.UNAUTHORIZED);
		    	
		    	logger.error("Authentication failed, invalid user-" + username);
	    	}

	         
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("failed to login-" + e);
		}
	   
	    
	    return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
	}
	

	
	@RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> logout(@RequestHeader (value="tokenId") String authToken) {
		
		AuthenticationResponse response = new AuthenticationResponse();
	    try {
	    	 
	    	AkrainoSiteService akrainoSiteService = new AkrainoSiteService(); 
	    	
	    	akrainoSiteService.deleteUserSession(authToken);
	    	
	    	response.setTokenId(null);
	    	response.setMessage("User successfuly logged out");
	    	response.setStatusCode("200");
	    	
	        logger.info("User logged out");
	         
		} catch (Exception e) {
			
			logger.error("failed to logout-" + e);
		}
	   
	    
	    return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
	}

}
