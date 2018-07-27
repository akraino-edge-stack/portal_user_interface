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

package org.akraino.portal.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.akraino.portal.service.AkrainoSiteService;
import org.akraino.portal.service.RegionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/regions")
public class RegionController {

	@Autowired
	RegionService regionService;
	
	private static final Logger logger = Logger.getLogger(RegionController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)   
	public ResponseEntity<List<org.akraino.portal.entity.Region>> getAllRegions() {
		
		//List <org.akraino.portal.data.Region> list = new ArrayList<org.akraino.portal.data.Region> ();
		List <org.akraino.portal.entity.Region> list = new ArrayList<org.akraino.portal.entity.Region> ();
		logger.error("test error msg");
		AkrainoSiteService akraionSiteService = new AkrainoSiteService();
		
		try {
			//list = akraionSiteService.getRegions(); //--> working with sql
			
			list = regionService.listAllRegions();
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		return new ResponseEntity<List<org.akraino.portal.entity.Region>>(list, HttpStatus.OK);
	}
	
}
