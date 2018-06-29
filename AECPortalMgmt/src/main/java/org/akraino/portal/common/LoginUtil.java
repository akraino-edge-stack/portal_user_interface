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


import java.util.Base64;

public class LoginUtil {

	public static synchronized String getUserName(String base64Str) {
		
		return base64Str.split(":")[0];
		
	}
	
	public static synchronized String getPassword(String base64Str) {
		
		if (!StringUtil.notEmpty(base64Str))
			return null;
		
		return base64Str.split(":")[1];
		
	}
	
	public static synchronized String decode(String base64Str) {
		
		byte [] decodedBytes;
		String decodedString = null;
		
		if (StringUtil.notEmpty(base64Str)) {
			decodedBytes = Base64.getDecoder().decode(base64Str);
			decodedString = new String (decodedBytes);
		}
		
		return decodedString;
		
	}
	
	public static synchronized String encode(String base64Str) {
		
		String encodedString = null;
		
		if (StringUtil.notEmpty(base64Str)) {
			encodedString = Base64.getEncoder().encodeToString(base64Str.getBytes());
		}
		
		return encodedString;
		
	}
	
}
