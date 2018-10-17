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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.akraino.portal.dao.AddOnsDAO;
import org.akraino.portal.entity.EdgeSite;
import org.akraino.portal.entity.Onap;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddOnsDAOImpl implements AddOnsDAO {

	private static final Logger logger = Logger.getLogger(AddOnsDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOnap(Onap onap) {

		logger.info("saveOnap");
		
		getSession().saveOrUpdate(onap);

	}

	@Override
	public List<Onap> getOnapList() {
		
		logger.info("getOnapList");

		EntityManager em = getSession().getEntityManagerFactory().createEntityManager();

		@SuppressWarnings("unchecked")
		List<Object[]> objectList = em
				.createQuery("SELECT onap, edgeSite FROM Onap onap RIGHT JOIN onap.edgeSite edgeSite").getResultList();
		List<Onap> onapList = new ArrayList<Onap>();
		for (Object[] object : objectList) {
			if (object[0] == null) {
				Onap onap = new Onap();
				onap.setEdgeSite((EdgeSite) object[1]);
				onapList.add(onap);
			} else {
				onapList.add((Onap) object[0]);
			}
		}

		return onapList;
	}

	@Override
	public Onap getOnap(String siteName) throws NoResultException {
		
		logger.info("getOnap");
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);

		Root<Onap> onapRoot = criteria.from(Onap.class);
		Root<EdgeSite> edgeSiteRoot = criteria.from(EdgeSite.class);
		criteria.multiselect(onapRoot, edgeSiteRoot);
		criteria.where(builder.equal(edgeSiteRoot.get("edgeSiteName"), siteName));

		Query<Object[]> query = getSession().createQuery(criteria);
		List<Object[]> list = query.getResultList();

		Onap onap = null;

		for (Object[] objects : list) {
			onap = (Onap) objects[0];
		}

		return onap;
	}
}
