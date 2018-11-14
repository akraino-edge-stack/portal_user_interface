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
