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

import org.akraino.portal.data.EdgeSite;
import org.akraino.portal.data.Onap;
import org.akraino.portal.data.SiteStatusRequest;
import org.akraino.portal.data.SiteStatusResponse;
import org.akraino.portal.data.WFEBuildSiteReponse;
import org.akraino.portal.data.WFEBuildSiteRequest;
import org.akraino.portal.service.AkrainoSiteService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/edgeSites")
public class SitesController {

	private static final Logger logger = Logger.getLogger(SitesController.class);
	
	@RequestMapping(value = "/{regionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)   
	public ResponseEntity<List<EdgeSite>> getAllEdgeSites(  @PathVariable("regionId") Integer regionId) {
		
		List <EdgeSite> list = new ArrayList<EdgeSite> ();
		
		AkrainoSiteService akrainoSiteService = new AkrainoSiteService();
		try {
			if (regionId == null) {
				regionId = 0;
			}
			list = akrainoSiteService.getSites(regionId.intValue());
		} catch (ClassNotFoundException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}
		
		return new ResponseEntity<List<EdgeSite>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/build", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WFEBuildSiteReponse> buildEdgeSite(@RequestBody WFEBuildSiteRequest buildRequest) {
		
		
		WFEBuildSiteReponse response = new WFEBuildSiteReponse();
	    try {
	    
	         HttpHeaders headers = new HttpHeaders();
	         headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	         headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

	    
	         RestTemplate restTemplate = new RestTemplate();
	         
	         // Data attached to the request.
	         HttpEntity<WFEBuildSiteRequest> requestBody = new HttpEntity<>(buildRequest, headers);
	         	        response = restTemplate.postForObject(
	        		 "http://135.16.101.85:8070/build/", 
	        		 requestBody, WFEBuildSiteReponse.class);
	    	
	        if (response.getStatus() == String.valueOf(200)) {
	        	
	        	AkrainoSiteService akrainoSiteService = new AkrainoSiteService(); 
	        	
	        	SiteStatusRequest statusReqeust = new SiteStatusRequest();
	        	
	        	akrainoSiteService.updateSiteStatus(statusReqeust);
	        	
	        	return new ResponseEntity<WFEBuildSiteReponse>(response, HttpStatus.OK);
	        } else {
	        	return new ResponseEntity<WFEBuildSiteReponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	         
		} catch (Exception e) {
			
			
			
			logger.error("site build failed-" + e);
		}
	   
	    
	    return new ResponseEntity<WFEBuildSiteReponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SiteStatusResponse> updateSiteStatus(@RequestBody SiteStatusRequest statusRequest) {
		AkrainoSiteService akrainoSiteService = new AkrainoSiteService(); 
		int updateRecords;
		SiteStatusResponse response = new SiteStatusResponse();
		response.setSiteName(statusRequest.getSiteName());
	    try {
	    	updateRecords = akrainoSiteService.updateSiteStatus(statusRequest);
	    	if(updateRecords > 0) {
	    		response.setStatusCode("200");
	    		response.setMessage("install status updated successfully");
	    	} else {
	    		response.setStatusCode("406");
	    		response.setMessage("install status update failed");
	    	}
	    	
	    	
		} catch (ClassNotFoundException | SQLException e) {
			response.setStatusCode("406");
			response.setMessage(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    
	    return new ResponseEntity<SiteStatusResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/onap/{siteName}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SiteStatusResponse> updateSiteOnap(@RequestBody Onap onap) {
		AkrainoSiteService akrainoSiteService = new AkrainoSiteService(); 
		int updateRecords;
		SiteStatusResponse response = new SiteStatusResponse();
		response.setSiteName(onap.getSiteName());
	    try {
	    	updateRecords = akrainoSiteService.updateSiteOnap(onap);
	    	if(updateRecords > 0) {
	    		response.setStatusCode("200");
	    		response.setMessage("Update Onap successfully");
	    	} else {
	    		response.setStatusCode("406");
	    		response.setMessage("Update Onap failed");
	    	}
	    	
		} catch (ClassNotFoundException | SQLException e) {
			response.setStatusCode("406");
			response.setMessage(e.getMessage());

		}
	   
	    
	    return new ResponseEntity<SiteStatusResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/onap/{siteName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)   
	public ResponseEntity<Onap> getOnapDetails(  @PathVariable("siteName") String siteName) {
		
		List <Onap> list = new ArrayList<Onap> ();
		
		AkrainoSiteService akrainoSiteService = new AkrainoSiteService();
		try {
			list = akrainoSiteService.getOnapForSite(siteName);
		} catch (ClassNotFoundException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}
	
		
		return new ResponseEntity<Onap>(list.get(0), HttpStatus.OK);
	}
	
}
