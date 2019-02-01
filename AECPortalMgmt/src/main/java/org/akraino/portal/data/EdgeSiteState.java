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


public class EdgeSiteState {

	
	private String siteName;
	private String buildStatus;
	private String createTarStatus;
	private String transferFileStatus;
	private String genesisNodeStatus;
	private String deployToolsStatus;
	private String nodeYamlStatus;
	private String nodetransferFileStatus;
	private String nodeDeployStatus;
	private String buildDate;
	private String deployDate;
	private String deployStatus;
	private String onapStatus;
	private String tempestStatus;
	private String vCDNStatus;
	private String blueprint;
	private String edgeSiteIP;
	private String edgeSiteUser;
	private String edgeSitePwd;
	private String deployMode;
	
	@Override
	public String toString() {
		return "EdgeSiteState [siteName=" + siteName + ", buildStatus=" + buildStatus + ", createTarStatus="
				+ createTarStatus + ", transferFileStatus=" + transferFileStatus + ", genesisNodeStatus="
				+ genesisNodeStatus + ", deployToolsStatus=" + deployToolsStatus + ", nodeYamlStatus=" + nodeYamlStatus
				+ ", nodetransferFileStatus=" + nodetransferFileStatus + ", nodeDeployStatus=" + nodeDeployStatus
				+ ", buildDate=" + buildDate + ", deployDate=" + deployDate + ", deployStatus=" + deployStatus
				+ ", onapStatus=" + onapStatus + ", tempestStatus=" + tempestStatus + ", vCDNStatus=" + vCDNStatus
				+ ", blueprint=" + blueprint + ", edgeSiteIP=" + edgeSiteIP + ", edgeSiteUser=" + edgeSiteUser
				+ ", edgeSitePwd=" + edgeSitePwd + ", deployMode=" + deployMode + "]";
	}
	
	public String getBlueprint() {
		return blueprint;
	}
	public void setBlueprint(String blueprint) {
		this.blueprint = blueprint;
	}
	
	public String getDeployMode() {
		return deployMode;
	}
	public void setDeployMode(String deployMode) {
		this.deployMode = deployMode;
	}
	public String getEdgeSiteIP() {
		return edgeSiteIP;
	}
	public void setEdgeSiteIP(String edgeSiteIP) {
		this.edgeSiteIP = edgeSiteIP;
	}
	public String getEdgeSiteUser() {
		return edgeSiteUser;
	}
	public void setEdgeSiteUser(String edgeSiteUser) {
		this.edgeSiteUser = edgeSiteUser;
	}
	public String getEdgeSitePwd() {
		return edgeSitePwd;
	}
	public void setEdgeSitePwd(String edgeSitePwd) {
		this.edgeSitePwd = edgeSitePwd;
	}
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
	public String getNodeYamlStatus() {
		return nodeYamlStatus;
	}
	public void setNodeYamlStatus(String nodeYamlStatus) {
		this.nodeYamlStatus = nodeYamlStatus;
	}
	public String getNodetransferFileStatus() {
		return nodetransferFileStatus;
	}
	public void setNodetransferFileStatus(String nodetransferFileStatus) {
		this.nodetransferFileStatus = nodetransferFileStatus;
	}
	public String getNodeDeployStatus() {
		return nodeDeployStatus;
	}
	public void setNodeDeployStatus(String nodeDeployStatus) {
		this.nodeDeployStatus = nodeDeployStatus;
	}
	public String getTransferFileStatus() {
		return transferFileStatus;
	}
	public void setTransferFileStatus(String transferFileStatus) {
		this.transferFileStatus = transferFileStatus;
	}
	
}
