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

import org.akraino.portal.common.LDAPAuthentication;
import org.akraino.portal.common.LoginUtil;
import org.akraino.portal.common.StringUtil;
import org.akraino.portal.data.AccessResponse;
import org.akraino.portal.entity.UserSession;
import org.akraino.portal.service.AccessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessController {

	private static final Logger logger = Logger.getLogger(AccessController.class);

	@Autowired
	LDAPAuthentication ldapAuth;

	@Autowired
	AccessService accessService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccessResponse> login(@RequestHeader(value = "Authorization") String base64Auth,
			HttpServletRequest req) {

		AccessResponse response = new AccessResponse();

		try {

			base64Auth = base64Auth.replaceAll("Basic ", "");

			String userPwd = LoginUtil.decode(base64Auth);

			String username = LoginUtil.getUserName(userPwd);

			String pwd = LoginUtil.getPassword(userPwd);
			
			logger.info("User attempt to log in:" + username);

			response = ldapAuth.authenticateUser(username, pwd);

			if (response.getMessage().equals(LDAPAuthentication.AUTHORIZED)) {

				// Authentication successful, valid user

				UserSession user = accessService.getUserSession(username);

				HttpSession session = req.getSession(true);

				String sessionId = session.getId();

				String sessionToken = LoginUtil.encode(username) + ":" + sessionId;

				session.setAttribute("sessionToken", sessionToken);

				if (user != null && StringUtil.notEmpty(user.getTokenId())) {

					// user session exists, re-activate session
					accessService.updateUserSession(username, sessionId);

				} else {
					// create new user session
					accessService.createUserSession(username, sessionId);
				}

				response.setStatusCode("200");
				response.setTokenId(sessionToken);
				response.setMessage(LDAPAuthentication.AUTHORIZED);
				
				logger.info("User authorized:" + username);

			} else {
				// Authentication failed, invalid user
				response.setStatusCode("401");
				response.setTokenId(null);
				response.setMessage(LDAPAuthentication.UNAUTHORIZED);

				logger.error("Authentication failed, invalid user-" + username);
			}

		} catch (Exception e) {
			logger.error("failed to login-", e);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccessResponse> logout(@RequestHeader(value = "tokenId") String authToken) {

		AccessResponse response = new AccessResponse();
		try {
			
			String userName = LoginUtil.decode(LoginUtil.getUserName(authToken));

			accessService.deleteUserSession(authToken);

			response.setTokenId(null);
			response.setStatusCode("200");
			
			logger.info("User logged out" + userName);

		} catch (Exception e) {

			logger.error("failed to logout", e);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
