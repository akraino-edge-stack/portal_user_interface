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

import java.util.ArrayList;
import java.util.List;

import org.akraino.portal.entity.Pod;
import org.akraino.portal.service.PodService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pod")
public class PodController {

	@Autowired
	PodService podService;
	
	private static final Logger logger = Logger.getLogger(PodController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)   
	public ResponseEntity<List<Pod>> getPods() {
		
		List <Pod> list = new ArrayList<Pod> ();
		
		try {
			
			list = podService.getPods();
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		return new ResponseEntity<List<Pod>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pod", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPod(@RequestBody Pod pod) {
		
		try {
			
			podService.savePod(pod);
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		return new ResponseEntity<String>("pod saved successfuly", HttpStatus.OK);
	}
	
}
