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

package org.akraino.portal.daoimpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.akraino.portal.dao.AccessDAO;
import org.akraino.portal.entity.UserSession;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccessDAOImpl implements AccessDAO {

    private static final Logger logger = Logger.getLogger(AccessDAOImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public void createUserSession(UserSession usersession) {
        
        logger.info("createUserSession");
        
        getSession().save(usersession);

    }

    @Override
    public void updateUserSession(UserSession usersession) {

        logger.info("updateUserSession");
        
        getSession().update(usersession);

    }

    @Override
    public UserSession getUserSession(String userId) {
        
        logger.info("getUserSession");
        
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<UserSession> criteria = builder.createQuery(UserSession.class);

        Root<UserSession> root = criteria.from(UserSession.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("loginId"), userId));

        Query<UserSession> query = getSession().createQuery(criteria);

        return query.getSingleResult();

    }

    @Override
    public void deleteUserSession(String userId) {
        
        logger.info("deleteUserSession");

        UserSession userSession = getUserSession(userId);
        
        getSession().delete(userSession);

    }
}