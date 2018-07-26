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
package org.akraino.portal.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtil {
	
	private static final Logger logger = Logger.getLogger(PropertyUtil.class);
	
	private static PropertyUtil propertyUtil;
	Properties appProps;
	
	public static PropertyUtil getInstance() {
		if (propertyUtil == null) {
			return new PropertyUtil();
		}
		
		return propertyUtil;
	}
	
	private PropertyUtil() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "app.properties";
		
		appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
			
		} catch (IOException e) {
			logger.error("Error loading app properties file");
		}
	}

	public String getProperty(String key) {
		
		return appProps.getProperty(key);
	}
	
}