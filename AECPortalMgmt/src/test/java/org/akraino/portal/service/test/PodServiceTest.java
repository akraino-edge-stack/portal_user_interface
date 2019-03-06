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

import java.util.List;

import org.akraino.portal.config.AppConfig;
import org.akraino.portal.entity.Pod;
import org.akraino.portal.entity.GenericRack;
import org.akraino.portal.service.PodService;
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
public class PodServiceTest extends TestCase {
    
    @Autowired
    @Qualifier("podService")
    PodService podService;

    @Test
    public void testrun() {
        
        try {
            
            //Pod pod = new Pod();
            
            //pod.setPodname("Rover12");
            
            //podService.savePod(pod);
            
            List<Pod> pods = podService.getPods();
            
            /*Pod pod = new Pod();
            pod.setPodname("RoverUUU16");*/
            
            Pod dbpod=null;
            
            for (Pod pod : pods) {
                if (pod.getPodname().equals("RoverUUU16")) {
                    dbpod = pod;
                }
            }
            
            GenericRack rack = new GenericRack();
            rack.setRackname("rack111");
            rack.setRackPersonality("Control");

            //dbpod.addRack(rack);
            
            //pod.setRacks(racks);
            //podService.updatePod(dbpod);

            //List<Pod> pods = podService.getPods();
            
            //System.out.println("id"+dbpod.getPodId());
            
            //System.out.println("id"+pods.size());
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
