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

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.akraino.portal.dao.PodDAO;
import org.akraino.portal.entity.Pod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PodDAOImpl implements PodDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Pod> getPods() {

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Pod> criteria = builder.createQuery(Pod.class);

		Root<Pod> root = criteria.from(Pod.class);
		criteria.select(root);

		Query<Pod> query = getSession().createQuery(criteria);

		return query.getResultList();

	}

	@Override
	public Pod getPod(Long podId) {

		EntityManager em = getSession().getEntityManagerFactory().createEntityManager();

		return em.find(Pod.class, podId);
	}

	@Override
	public void saveOrUpdate(Pod pod) {
		getSession().saveOrUpdate(pod);

	}

	@Override
	public void merge(Pod pod) {
		getSession().merge(pod);

	}

	@Override
	public void deletePod(Pod pod) {
		getSession().delete(pod);

	}

}