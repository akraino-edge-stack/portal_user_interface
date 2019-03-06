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

import org.akraino.portal.dao.PodMetricsDAO;
import org.akraino.portal.entity.PodMetrics;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PodMetricsDAOImpl implements PodMetricsDAO {

    private static final Logger logger = Logger.getLogger(PodMetricsDAOImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        
        logger.info("get Hibernate session");
        
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public void createPodMetrics(PodMetrics podMetrics) {
        
        getSession().saveOrUpdate(podMetrics);

    }

}
