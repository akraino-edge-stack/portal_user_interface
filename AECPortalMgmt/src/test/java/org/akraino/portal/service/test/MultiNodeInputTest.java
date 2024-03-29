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
import java.io.IOException;

import org.akraino.portal.config.AppConfig;
import org.akraino.portal.data.NPod;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

import junit.framework.TestCase;

public class MultiNodeInputTest extends TestCase {
	
	private static final String MULTI_NODE_INPUT_FILENAME = "multi-node-input-file-hpgen10.yaml";
	
	private static final String MULTI_NODE_OUTPUT_FILENAME = "/multi-node-output-file-hpgen10.yaml";
	
	@Test
	public void testRun() {
		
		File yamlFile = new File(AppConfig.class.getClassLoader().getResource(MULTI_NODE_INPUT_FILENAME).getPath());
		
		ObjectMapper mapper = new ObjectMapper(
				new YAMLFactory()
				.enable(Feature.MINIMIZE_QUOTES)
				.enable(Feature.WRITE_DOC_START_MARKER)
				)
				.disable(SerializationFeature.WRITE_NULL_MAP_VALUES)
				.setSerializationInclusion(Include.NON_NULL);
		
		try {
			NPod npod = mapper.readValue(yamlFile, NPod.class);
			
			mapper.writeValue(new File(yamlFile.getParent()+MULTI_NODE_OUTPUT_FILENAME), npod);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
