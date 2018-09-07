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

import org.akraino.portal.data.AECPortalResponse;
import org.akraino.portal.data.TempestRequest;
import org.akraino.portal.service.TestETEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestETEController {

	@Autowired
	TestETEService testETEService;

	@RequestMapping(value = "/tempest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AECPortalResponse> runTempest(@RequestBody TempestRequest requestPayLoad) {

		AECPortalResponse response = new AECPortalResponse();

		try {

			testETEService.runTempest(requestPayLoad);

			response.setEntity("Tempest");
			response.setStatusCode("200");
			response.setMessage("Tempest call initiated successfuly");

		} catch (Exception e) {

			response.setEntity("Tempest");
			response.setStatusCode("406");
			response.setMessage("Tempest call initiation failed");

		}

		return new ResponseEntity<AECPortalResponse>(response, HttpStatus.OK);

	}
}