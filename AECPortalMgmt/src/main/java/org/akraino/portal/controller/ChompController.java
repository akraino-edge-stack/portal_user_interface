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

import org.akraino.portal.data.ChompObject;
import org.akraino.portal.data.SiteStatusResponse;
import org.akraino.portal.service.PodMetricsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/chomp")
public class ChompController {
    
    @Autowired
    PodMetricsService podMetricsService;
    
    private static final Logger logger = Logger.getLogger(ChompController.class);
    
    @SuppressWarnings("deprecation")
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SiteStatusResponse> uploadChompInputFile(@RequestParam MultipartFile file,
            @ModelAttribute("originSrc") String originSrc, @ModelAttribute("interval") String interval,
            @ModelAttribute("timeStamp") String timeStamp) {

        SiteStatusResponse response = new SiteStatusResponse();

        logger.info("upload chomp data request at:" + timeStamp + "for interval" + interval);

        try {
            
            byte [] chompbytes = file.getBytes();
            String chompString = StringUtils.toString(chompbytes, "UTF-8");

            boolean copyStatus = podMetricsService.processChompInputFile(chompString);

            if (copyStatus) {
                response.setStatusCode("200");
                response.setMessage("CHOMP data loaded into akraino successfully");

            } else {
                response.setStatusCode("406");
                response.setMessage("CHOMP data load into akraino failed");
            }

        } catch (Exception e) {
            response.setStatusCode("406");
            response.setMessage(e.getMessage());

            logger.error("Error uploading input file", e);
        }

        logger.info("upload input file for request:" + response);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChompObject>> getChompData() {

        logger.info("getChompData - start");
        
        List<ChompObject> chompdata = null;
        try {
            
            chompdata = podMetricsService.getChompData();
            

        } catch (Exception e) {
            logger.error("Exception retrieving site details", e);
        } 
        
        logger.info("getChompData - end");

        return new ResponseEntity<>(chompdata, HttpStatus.OK);
    }

}
