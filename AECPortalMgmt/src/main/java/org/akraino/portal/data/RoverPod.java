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

package org.akraino.portal.data;

/**
 * 
 * rover pod extends from generic construct NPod
 * defines specific (additional) attributes for Rover Pod
 * 
 * @author ld261v
 */
public class RoverPod extends NPod {
	
	private String webIP;
	
	private Integer webPort;

	public String getWebIP() {
		return webIP;
	}

	public void setWebIP(String webIP) {
		this.webIP = webIP;
	}

	public Integer getWebPort() {
		return webPort;
	}

	public void setWebPort(Integer webPort) {
		this.webPort = webPort;
	}

	@Override
	public String toString() {
		return "RoverPod [webIP=" + webIP + ", webPort=" + webPort + "]";
	}
	
}
