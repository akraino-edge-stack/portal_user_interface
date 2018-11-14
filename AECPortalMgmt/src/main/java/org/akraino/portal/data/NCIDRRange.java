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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"start", "endRange"})
public class NCIDRRange {

	@JsonProperty("start")
	private String startRange;

	@JsonProperty("end")
	private String endRange;
	
	public String getEndRange() {
		return endRange;
	}

	public String getStartRange() {
		return startRange;
	}

	public void setEndRange(String endRange) {
		this.endRange = endRange;
	}

	public void setStartRange(String startRange) {
		this.startRange = startRange;
	}

	@Override
	public String toString() {
		return "NCIDRRange [endRange=" + endRange + ", startRange=" + startRange + "]";
	}

}
