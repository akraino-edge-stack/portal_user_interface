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
package org.akraino.portal.service.test;

import org.akraino.portal.config.AppConfig;
import org.akraino.portal.data.EdgeSiteState;
import org.akraino.portal.data.TempestRequest;
import org.akraino.portal.service.TestETEService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.TestCase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class TestETEServiceTest extends TestCase {
    
    @Autowired
    @Qualifier("testETEService")
    TestETEService testETEService;

    @Test
    public void testrun() {
        
        try {
            
            TempestRequest req = new TempestRequest();
            req.setSitename("MTN1");
            
            EdgeSiteState state = testETEService.runTempest(req);
            
            System.out.println("no. of onaps:" + state.toString());
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
}
