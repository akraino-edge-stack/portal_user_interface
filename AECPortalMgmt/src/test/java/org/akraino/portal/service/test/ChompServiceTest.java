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

import java.io.InputStream;
import java.util.List;

import org.akraino.portal.common.StringUtil;
import org.akraino.portal.config.AppConfig;
import org.akraino.portal.data.ChompObject;
import org.akraino.portal.service.PodMetricsService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class ChompServiceTest extends TestCase {
	
	@Autowired
    @Qualifier("podMetricsService")
	PodMetricsService podMetricsService;
	
	
	
	@Test
	public void testRun() {
		
		try {
			
			List<ChompObject> chomps = podMetricsService.getChompData();
			
			System.out.println("chomp:" + chomps.toString());
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
}
