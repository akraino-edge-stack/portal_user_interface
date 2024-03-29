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

public class BuildRequest extends WorkflowRequest {
	
	private String blueprint;
	private String targetfolder;
	
	@Override
	public String toString() {
		return "BuildRequest [blueprint=" + blueprint + ", targetfolder=" + targetfolder + "]";
	}
	
	public String getBlueprint() {
		return blueprint;
	}
	public void setBlueprint(String blueprint) {
		this.blueprint = blueprint;
	}
	public String getTargetfolder() {
		return targetfolder;
	}
	public void setTargetfolder(String targetfolder) {
		this.targetfolder = targetfolder;
	}

}