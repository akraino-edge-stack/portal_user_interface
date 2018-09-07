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

package org.akraino.portal.service;

import java.util.Date;

import javax.persistence.NoResultException;

import org.akraino.portal.common.LoginUtil;
import org.akraino.portal.dao.AccessDAO;
import org.akraino.portal.entity.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessService {

	@Autowired
	private AccessDAO accessDAO;

	public void createUserSession(String userid, String tokenId) {

		UserSession userSession = new UserSession();
		userSession.setLoginId(userid);
		userSession.setTokenId(tokenId);
		userSession.setCreatedDate(new Date());

		accessDAO.createUserSession(userSession);

	}

	public void updateUserSession(String userid, String tokenId) {

		UserSession userSession = accessDAO.getUserSession(userid);

		userSession.setTokenId(tokenId);

		accessDAO.updateUserSession(userSession);

	}

	public UserSession getUserSession(String userId) {

		UserSession userSession = null;
		
		try {
			
			userSession = accessDAO.getUserSession(userId);
			
		} catch (NoResultException ne) {
			// add logger statement
		}
		
		return userSession;

	}

	public void deleteUserSession(String authToken) {

		String userId = LoginUtil.decode(LoginUtil.getUserName(authToken));

		accessDAO.deleteUserSession(userId);

	}

}
