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

import org.akraino.portal.data.ChompData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("podMetricsService")
@Transactional
public class PodMetricsService {
	
	public boolean processChompInputFile(byte[] bfileContent) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper(new JsonFactory());
		
		ChompData chompData = mapper.readValue(bfileContent, ChompData.class);
		
		System.out.println("chomp data:" + chompData);
		
		return true;
		
	}

}
