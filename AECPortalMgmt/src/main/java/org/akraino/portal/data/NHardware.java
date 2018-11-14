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

import com.fasterxml.jackson.annotation.JsonProperty;

public class NHardware {

	private String vendor;
	
	private String generation;
	
	@JsonProperty("hw_version")
	private String hwVersion;
	
	@JsonProperty("bios_version")
	private String biosVersion;

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public String getHwVersion() {
		return hwVersion;
	}

	public void setHwVersion(String hwVersion) {
		this.hwVersion = hwVersion;
	}

	public String getBiosVersion() {
		return biosVersion;
	}

	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}

	@Override
	public String toString() {
		return "NHardware [vendor=" + vendor + ", generation=" + generation + ", hwVersion=" + hwVersion
				+ ", biosVersion=" + biosVersion + "]";
	}
	
}
