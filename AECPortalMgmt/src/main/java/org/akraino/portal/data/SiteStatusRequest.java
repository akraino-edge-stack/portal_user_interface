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


public class SiteStatusRequest {

	private String siteName;
	private String buildStatus;
	private String createTarStatus;
	private String genesisNodeStatus;
	private String deployToolsStatus;
	private String buildDate;
	private String deployDate;
	private String deployStatus;
	private String onapStatus;
	private String tempestStatus;
	private String vCDNStatus;
	
	public String getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	public String getDeployDate() {
		return deployDate;
	}
	public void setDeployDate(String deployDate) {
		this.deployDate = deployDate;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getCreateTarStatus() {
		return createTarStatus;
	}
	public void setCreateTarStatus(String createTarStatus) {
		this.createTarStatus = createTarStatus;
	}
	public String getGenesisNodeStatus() {
		return genesisNodeStatus;
	}
	public void setGenesisNodeStatus(String genesisNodeStatus) {
		this.genesisNodeStatus = genesisNodeStatus;
	}
	public String getDeployToolsStatus() {
		return deployToolsStatus;
	}
	public void setDeployToolsStatus(String deployToolsStatus) {
		this.deployToolsStatus = deployToolsStatus;
	}
	public String getBuildStatus() {
		return buildStatus;
	}
	public void setBuildStatus(String buildStatus) {
		this.buildStatus = buildStatus;
	}
	public String getDeployStatus() {
		return deployStatus;
	}
	public void setDeployStatus(String deployStatus) {
		this.deployStatus = deployStatus;
	}
	public String getOnapStatus() {
		return onapStatus;
	}
	public void setOnapStatus(String onapStatus) {
		this.onapStatus = onapStatus;
	}
	public String getTempestStatus() {
		return tempestStatus;
	}
	public void setTempestStatus(String tempestStatus) {
		this.tempestStatus = tempestStatus;
	}
	public String getvCDNStatus() {
		return vCDNStatus;
	}
	public void setvCDNStatus(String vCDNStatus) {
		this.vCDNStatus = vCDNStatus;
	}
	
}
