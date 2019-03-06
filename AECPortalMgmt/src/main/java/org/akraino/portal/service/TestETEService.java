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

import org.akraino.portal.common.PropertyUtil;
import org.akraino.portal.common.RestInterface;
import org.akraino.portal.common.RestRequestBody;
import org.akraino.portal.common.RestResponseBody;
import org.akraino.portal.common.StringUtil;
import org.akraino.portal.data.EdgeSiteState;
import org.akraino.portal.data.TempestRequest;
import org.akraino.portal.data.WorkflowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestETEService {

    @Autowired
    EdgeSiteService edgeSiteService;

    public EdgeSiteState runTempest(TempestRequest tempestRequest) {

        EdgeSiteState siteState = new EdgeSiteState();
        siteState.setSiteName(tempestRequest.getSitename());

        try {

            String tempestURI = PropertyUtil.getInstance().getProperty("camunda.tempest.uri");

            RestRequestBody<WorkflowRequest> requestObj = new RestRequestBody<WorkflowRequest>();
            requestObj.setT(tempestRequest);

            RestResponseBody<EdgeSiteState> responseObj = new RestResponseBody<EdgeSiteState>();
            responseObj.setT(new EdgeSiteState());

            siteState = RestInterface.sendPOST(tempestURI, requestObj, responseObj);

        } catch (Exception e) {

            throw e;

        } finally {
            
            if (!StringUtil.notEmpty(siteState.getTempestStatus())) {
                siteState.setTempestStatus("Error invoking Camunda API");
            }

            edgeSiteService.updateSiteStatus(siteState);
        }

        return siteState;

    }
}