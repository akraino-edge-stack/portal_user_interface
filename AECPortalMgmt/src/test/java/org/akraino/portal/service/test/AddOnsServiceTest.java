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

import java.io.File;
import java.util.List;

import org.akraino.portal.config.AppConfig;
import org.akraino.portal.entity.Onap;
import org.akraino.portal.service.AddOnsService;
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
public class AddOnsServiceTest extends TestCase {
    
    @Autowired
    @Qualifier("addOnsService")
    AddOnsService addOnsService;

    @Test
    public void testrun() {
        
        try {
            
            File fr = new File("C:\\Users\\ld261v\\Desktop\\AEC\\test\\input.onap");
            
            //addOnsService.saveOnapInput(Files.readAllBytes(fr.toPath()), "MTN1");
            
            //Onap onap = addOnsService.getOnap("MTN1");
            //System.out.println(new String(onap.getInputFile()));
            
            List<Onap> onaps = addOnsService.getOnapList();
            
            System.out.println("no. of onaps:" + onaps);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

}
