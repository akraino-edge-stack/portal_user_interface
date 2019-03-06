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

import java.util.List;

import org.akraino.portal.data.AECPortalResponse;
import org.akraino.portal.data.EdgeSiteState;
import org.akraino.portal.data.SiteStatusResponse;
import org.akraino.portal.data.WorkflowRequest;
import org.akraino.portal.entity.Onap;
import org.akraino.portal.service.AddOnsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/addon")
public class AddOnsController {

    @Autowired
    AddOnsService addOnsService;

    private static final Logger logger = Logger.getLogger(PodController.class);

    @RequestMapping(value = "/onap/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteStatusResponse> uploadInputFile(@RequestParam MultipartFile file,
            @ModelAttribute("siteName") String siteName) {

        SiteStatusResponse response = new SiteStatusResponse();

        EdgeSiteState siteRequest = new EdgeSiteState();

        siteRequest.setSiteName(siteName);

        try {
            boolean copyStatus = addOnsService.saveOnapInput(file.getBytes(), siteName);
            if (copyStatus) {
                logger.info("ONAP input file copied successfully");
                response.setStatusCode("200");
                response.setMessage("Input file copied successfully");
            } else {
                logger.warn("Edge site not found");
                response.setStatusCode("406");
                response.setMessage("Input file copy failed");
            }

        } catch (Exception e) {
            logger.error("Error copying ONAP input file", e);
            
            response.setStatusCode("406");
            response.setMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/onap/{siteName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Onap> getOnapDetails(@PathVariable("siteName") String siteName) {

        Onap onap = null;
        try {

            onap = addOnsService.getOnap(siteName);

        } catch (Exception e) {
            logger.error("Error retrieving ONAP details", e);
        }

        return new ResponseEntity<Onap>(onap, HttpStatus.OK);
    }

    @RequestMapping(value = "/onap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Onap>> getOnapList() {

        List<Onap> onapList = null;
        try {

            onapList = addOnsService.getOnapList();

        } catch (Exception e) {
            logger.error("Error retrieving ONAP list", e);
        }

        return new ResponseEntity<>(onapList, HttpStatus.OK);
    }

    @RequestMapping(value = "/onap", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AECPortalResponse> installOnap(@RequestBody WorkflowRequest onapRequest) {

        AECPortalResponse response = new AECPortalResponse();

        try {

            addOnsService.installOnap(onapRequest);

            response.setEntity("ONAP install");
            response.setStatusCode("200");
            response.setMessage("ONAP install call initiated successfuly");

        } catch (Exception e) {

            response.setEntity("ONAP install");
            response.setStatusCode("406");
            response.setMessage("ONAP install call initiation failed");
            
            logger.error("Error triggering ONAP install", e);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}