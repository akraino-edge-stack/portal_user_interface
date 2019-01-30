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

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.akraino.portal.dao.EdgeSiteYamlTemplateDAO;
import org.akraino.portal.entity.EdgeSiteYamlTemplate;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EdgeSiteYamlTemplateDAOImpl implements EdgeSiteYamlTemplateDAO {

	private static final Logger logger = Logger.getLogger(EdgeSiteYamlTemplateDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<EdgeSiteYamlTemplate> getYamlTemplates() {
		
		logger.info("getYamlTemplates");

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<EdgeSiteYamlTemplate> criteria = builder.createQuery(EdgeSiteYamlTemplate.class);
		
		Root<EdgeSiteYamlTemplate> root = criteria.from(EdgeSiteYamlTemplate.class);
        criteria.select(root);
		
        Query<EdgeSiteYamlTemplate> query = getSession().createQuery(criteria);
        
        return query.getResultList();
		
	}

	@Override
	public void save(EdgeSiteYamlTemplate template) {
		
		logger.info("save");
		
		getSession().saveOrUpdate(template);
	}

	@Override
	public void deleteAll() {
		// clean all j2 files
		
		Query<?> query = getSession().createQuery("delete from EdgeSiteYamlTemplate");
		 
		int result = query.executeUpdate();
		
		if (result > 0) {
			logger.info("all j2 templates are cleaned up");
		}
		
	}
}
