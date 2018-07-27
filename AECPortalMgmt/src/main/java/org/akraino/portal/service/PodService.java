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

import java.sql.SQLException;
import java.util.List;

import org.akraino.portal.dao.PodDAO;
import org.akraino.portal.entity.Pod;
import org.akraino.portal.entity.Rack;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PodService {

	private static final Logger logger = Logger.getLogger(PodService.class);
	
	@Autowired
	private PodDAO podDAO;
	
	public void savePod(Pod pod) throws ClassNotFoundException, SQLException {
		
		podDAO.save(pod);
		
		for (Rack rack : pod.getRacks()) {
			rack.setPod(pod);
			saveRack(rack);
		}
	}
	
	private void saveRack(Rack rack) throws ClassNotFoundException, SQLException {
		podDAO.save(rack);
	}
	
	public List<Pod> getPods() throws ClassNotFoundException, SQLException {
		
		return podDAO.getPods();

	}
	
}
