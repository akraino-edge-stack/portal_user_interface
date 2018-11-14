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

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class FileUtility {
	
	private static final Logger logger = Logger.getLogger(FileUtility.class);

	public static void writeToFile(String filePath, byte[] fileContent) throws IOException {
		
		FileOutputStream out = null;
		try {
			Path path = Paths.get(filePath);
			Files.createDirectories(path.getParent());
	
			out = new FileOutputStream(filePath);
			out.write(fileContent);
			
		} finally {
			out.close();
		}
			
			convertDOSToUnix(filePath);
	}
	
	private static void convertDOSToUnix(String filePath) {
		
		try {
			
			Runtime.getRuntime().exec("dos2unix " + filePath);
			
		} catch (IOException e) {
			
			logger.error("Error converting windows file to unix", e);
		}
	}
	
}