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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.akraino.portal.config.AppConfig;
import org.apache.log4j.Logger;

public class PropertyUtil {
	private static final Logger logger = Logger.getLogger(PropertyUtil.class);
	private static final String APP_PROPERTIES_FILENAME = "app.properties";
	private static PropertyUtil propertyUtil;

	private Properties appProps;

	/**
	 * Return the single instance of this object in the app.
	 * @return the singleton
	 */
	public static synchronized PropertyUtil getInstance() {
		if (propertyUtil == null) {
			propertyUtil = new PropertyUtil();
		}
		return propertyUtil;
	}

	private PropertyUtil() {
		InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(APP_PROPERTIES_FILENAME);
		appProps = new Properties();
		try {
			appProps.load(input);
		} catch (IOException e) {
			logger.error("Error loading properties file: "+APP_PROPERTIES_FILENAME);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	/**
	 * Get a property from the PropertyUtil object.  If the environment variable $IP is set,
	 * then any URL's referring to localhost will be rewritten to use this IP address instead.
	 * @param key the key to use to find the property
	 * @return the value
	 */
	public String getProperty(String key) {
		String s = appProps.getProperty(key);
		if (s != null && s.indexOf("://localhost:") > 0) {
			String ip = System.getenv().get("IP");
			if (ip != null && !"".contentEquals(ip)) {
				s = s.replaceAll("://localhost:", "://"+ip+":");
			}
		}
		return s;
	}
}
