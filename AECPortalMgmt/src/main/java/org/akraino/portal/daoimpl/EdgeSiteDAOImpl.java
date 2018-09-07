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

import org.akraino.portal.dao.EdgeSiteDAO;
import org.akraino.portal.entity.EdgeSite;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EdgeSiteDAOImpl implements EdgeSiteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<EdgeSite> listAllEdgeSites(int regionId) {
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<EdgeSite> criteria = builder.createQuery(EdgeSite.class);

		Root<EdgeSite> root = criteria.from(EdgeSite.class);
		criteria.select(root);
		
		if (regionId > 0) {
			criteria.where(builder.equal(root.get("regionId"), regionId));
		}

		Query<EdgeSite> query = getSession().createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public void updateEdgeSite(EdgeSite edgeSite) {
		
		getSession().update(edgeSite);
		
	}

	@Override
	public EdgeSite getEdgeSiteDetails(String siteName) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<EdgeSite> criteria = builder.createQuery(EdgeSite.class);

		Root<EdgeSite> root = criteria.from(EdgeSite.class);
		criteria.select(root);
		
		criteria.where(builder.equal(root.get("edgeSiteName"), siteName));

		Query<EdgeSite> query = getSession().createQuery(criteria);

		return query.getSingleResult();
	}
}