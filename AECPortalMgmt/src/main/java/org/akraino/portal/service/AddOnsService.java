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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.NoResultException;

import org.akraino.portal.common.PropertyUtil;
import org.akraino.portal.common.RestInterface;
import org.akraino.portal.common.RestRequestBody;
import org.akraino.portal.common.RestResponseBody;
import org.akraino.portal.common.StringUtil;
import org.akraino.portal.dao.AddOnsDAO;
import org.akraino.portal.dao.EdgeSiteDAO;
import org.akraino.portal.data.EdgeSiteState;
import org.akraino.portal.data.WorkflowRequest;
import org.akraino.portal.entity.EdgeSite;
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
    
    @Autowired
    private EdgeSiteDAO edgeSiteDAO;
    
    @Autowired
    EdgeSiteService edgeSiteService;
    
    private String akrainoBaseDir = null;
    
    public AddOnsService() {
        
        akrainoBaseDir = PropertyUtil.getInstance().getProperty("akraino.base.dir");
        
    }
    
    public boolean saveOnapInput(byte[] fileContent, String siteName) throws IOException {

        EdgeSite edgeSite = null;
        FileOutputStream out = null;
        
        try {
            // read input file from db
            edgeSite = edgeSiteDAO.getEdgeSiteDetails(siteName);
            
            if (edgeSite != null) {
                Onap onap = getOnap(siteName);
                if (onap == null) {
                    //onap is not installed on the edge site
                    onap = new Onap();
                    onap.setEdgeSite(edgeSite);
                }
                onap.setInputFile(fileContent);
                //update DB with inputfile
                addOnsDAO.saveOnap(onap);
                
                // copy input file
                String filepath = akrainoBaseDir + "/onap/parameters.env";
                //String filepath = "C:\\Users\\ld261v\\Desktop\\AEC\\test\\" + siteName + ".onap";
                
                Path path = Paths.get(filepath);
                Files.createDirectories(path.getParent());

                out = new FileOutputStream(filepath);
                out.write(fileContent);
                


                return true;
            }

            
        } catch (NoResultException e) {
            logger.info("no edge site found");
        } finally {
            if (out != null) {
                out.close();
            }
        }
        
        return false;
    }
    
    public Onap getOnap(String siteName) {
        
        
        Onap onap = null;
        try {
            
            onap = addOnsDAO.getOnap(siteName);
            
        } catch (NoResultException e) {
            
            return null;
        }
        
        return onap;
    }
    
    public List <Onap> getOnapList() {
        
        List <Onap> onapList = addOnsDAO.getOnapList();
        
        return onapList;
    }
    
    public EdgeSiteState installOnap(WorkflowRequest onapRequest) {
        
        EdgeSiteState siteState = new EdgeSiteState();
        siteState.setSiteName(onapRequest.getSitename());

        try {

            String onapURI = PropertyUtil.getInstance().getProperty("camunda.onap.uri");

            RestRequestBody<WorkflowRequest> requestObj = new RestRequestBody<WorkflowRequest>();
            requestObj.setT(onapRequest);

            RestResponseBody<EdgeSiteState> responseObj = new RestResponseBody<EdgeSiteState>();
            responseObj.setT(new EdgeSiteState());

            siteState = RestInterface.sendPOST(onapURI, requestObj, responseObj);

        } catch (Exception e) {

            throw e;

        } finally {
            
            if (!StringUtil.notEmpty(siteState.getOnapStatus())) {
                siteState.setOnapStatus("Error invoking Camunda API");
            }

            edgeSiteService.updateSiteStatus(siteState);
        }
        
        return siteState;
        
    }
}