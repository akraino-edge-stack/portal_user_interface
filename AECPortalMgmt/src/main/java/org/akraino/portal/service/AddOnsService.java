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

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import org.akraino.portal.dao.AddOnsDAO;
import org.akraino.portal.dao.GetEdgeSitesDAO;
import org.akraino.portal.data.EdgeSite;
import org.akraino.portal.entity.Onap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddOnsService {

	private static final Logger logger = Logger.getLogger(AddOnsService.class);
	
	@Autowired
	private AddOnsDAO addOnsDAO;
	
	public boolean saveOnapInput(byte[] fileContent, String siteName) throws ClassNotFoundException, SQLException, Exception {

		// read input file from db
		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();
		List<EdgeSite> edgeSites = getEdgeSitesDAO.getEdgeSites(0, siteName);

		if (edgeSites != null && !edgeSites.isEmpty()) {
			EdgeSite edgeSite = edgeSites.get(0);

			Onap onap = new Onap();
			onap.setSiteName(siteName);
			onap.setInputFile(fileContent);
			
			//update DB with inputfile
			addOnsDAO.saveOnap(onap);
			
			// copy input file
			String filepath = "/opt/akraino/onap/parameters.env";
			//String filepath = "C:\\Users\\ld261v\\Desktop\\AEC\\test\\" + siteName + ".onap";
			
			Path path = Paths.get(filepath);
            Files.createDirectories(path.getParent());

			FileOutputStream out = new FileOutputStream(filepath);
			out.write(fileContent);
			out.close();


			return true;
		}

		return false;

	}
	
}
