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

import org.akraino.portal.dao.RegionDAO;
import org.akraino.portal.entity.Region;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RegionDAOImpl implements RegionDAO {
	
	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 protected Session getSession(){
		  return sessionFactory.getCurrentSession();
	 }

	@Override
	@SuppressWarnings("unchecked")
	public List<Region> listAllRegions() {
		
		Criteria criteria = getSession().createCriteria(Region.class);
		  return (List<Region>) criteria.list();
	}
	
}
