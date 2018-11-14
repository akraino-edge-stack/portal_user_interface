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

import java.io.IOException;
import java.io.InputStream;

import org.akraino.portal.config.AppConfig;
import org.akraino.portal.service.PodMetricsService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import junit.framework.TestCase;

public class ChompServiceTest extends TestCase {
	
	@Autowired
    @Qualifier("podMetricsService")
	PodMetricsService podMetricsService;
	
	private static final String CHOMP_JSON_FILENAME = "chomp.json";
	
	@Test
	public void testRun() {
		
		try {
			//InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(CHOMP_JSON_FILENAME);
			
			//podMetricsService.processChompInputFile(IOUtils.toByteArray(input));
			
			String file1 = AppConfig.class.getClassLoader().getResource("multi-node-input-file-hpgen10.yaml").getFile();
			
			String file2 = AppConfig.class.getClassLoader().getResource("multi-node-input-file-hpgen10.yaml").getPath();
			
			System.out.println("file1:" + file1);
			System.out.println("file2:" + file2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
