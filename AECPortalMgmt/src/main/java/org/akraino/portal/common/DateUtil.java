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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class DateUtil {
	
	private static final Logger logger = Logger.getLogger(DateUtil.class);

	public static java.util.Date sqlToUtil(java.sql.Date sqldate) {

		java.util.Date utilDate = null;
		
		if (sqldate != null)
			utilDate = new java.util.Date(sqldate.getTime());
		
		return utilDate;
	}
	
	public static java.sql.Date utilToSql(java.util.Date utildate) {

		 java.sql.Date sqlDate = null;
		 
		 if (utildate != null)
			 sqlDate = new java.sql.Date(utildate.getTime());
		
		return sqlDate;
	}
	
	public static java.util.Date strToDate(String strdate) {

		java.util.Date utilDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");
		
		try {
			if (StringUtil.notEmpty(strdate))
				utilDate = formatter.parse(strdate);
		} catch (ParseException pe) {
			logger.error("Error converting string to date");
		}
		
		return utilDate;
	}
	
}
