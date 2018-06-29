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

public class Onap {
	
	private int id;
	private String siteName;
	private String publicNetName;
	private String publicSubnetCIDR;
	private String publicSubnetAllocStart;
	private String publicSubnetAllocEnd;
	private String publicSubnetDNSNameServer;
	private String publicSubnetGtwyIP;
	private String onapVMPubKey;
	private String onapRepo;
	private String httpProxy;
	private String httpsProxy;
	private String noProxy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getPublicNetName() {
		return publicNetName;
	}
	public void setPublicNetName(String publicNetName) {
		this.publicNetName = publicNetName;
	}
	public String getPublicSubnetCIDR() {
		return publicSubnetCIDR;
	}
	public void setPublicSubnetCIDR(String publicSubnetCIDR) {
		this.publicSubnetCIDR = publicSubnetCIDR;
	}
	public String getPublicSubnetAllocStart() {
		return publicSubnetAllocStart;
	}
	public void setPublicSubnetAllocStart(String publicSubnetAllocStart) {
		this.publicSubnetAllocStart = publicSubnetAllocStart;
	}
	public String getPublicSubnetAllocEnd() {
		return publicSubnetAllocEnd;
	}
	public void setPublicSubnetAllocEnd(String publicSubnetAllocEnd) {
		this.publicSubnetAllocEnd = publicSubnetAllocEnd;
	}
	public String getPublicSubnetDNSNameServer() {
		return publicSubnetDNSNameServer;
	}
	public void setPublicSubnetDNSNameServer(String publicSubnetDNSNameServer) {
		this.publicSubnetDNSNameServer = publicSubnetDNSNameServer;
	}
	public String getPublicSubnetGtwyIP() {
		return publicSubnetGtwyIP;
	}
	public void setPublicSubnetGtwyIP(String publicSubnetGtwyIP) {
		this.publicSubnetGtwyIP = publicSubnetGtwyIP;
	}
	public String getOnapVMPubKey() {
		return onapVMPubKey;
	}
	public void setOnapVMPubKey(String onapVMPubKey) {
		this.onapVMPubKey = onapVMPubKey;
	}
	public String getOnapRepo() {
		return onapRepo;
	}
	public void setOnapRepo(String onapRepo) {
		this.onapRepo = onapRepo;
	}
	public String getHttpProxy() {
		return httpProxy;
	}
	public void setHttpProxy(String httpProxy) {
		this.httpProxy = httpProxy;
	}
	public String getHttpsProxy() {
		return httpsProxy;
	}
	public void setHttpsProxy(String httpsProxy) {
		this.httpsProxy = httpsProxy;
	}
	public String getNoProxy() {
		return noProxy;
	}
	public void setNoProxy(String noProxy) {
		this.noProxy = noProxy;
	}

	
	
}
