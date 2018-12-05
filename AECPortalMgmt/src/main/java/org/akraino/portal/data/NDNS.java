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

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"domain", "servers", "upstreamServers", "serversJoined", "ingressDomain, dnsSearch, ntp"})
public class NDNS {

	private String domain;
	
	private String servers;
	
	private String dnsSearch;
	
	private String ntp;
	
	public NDNS() {
		
	}

	public NDNS(String domain, String servers, String dnsSearch, String ntp) {
		super();
		this.domain = domain;
		this.servers = servers;
		this.dnsSearch = dnsSearch;
		this.ntp = ntp;
	}



	@JsonProperty("upstream_servers")
	private String[] upstreamServers;

	@JsonProperty("upstream_servers_joined")
	private String serversJoined;
	
	@JsonProperty("ingress_domain")
	private String ingressDomain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public String[] getUpstreamServers() {
		return upstreamServers;
	}

	public void setUpstreamServers(String[] upstreamServers) {
		this.upstreamServers = upstreamServers;
	}

	public String getServersJoined() {
		return serversJoined;
	}

	public void setServersJoined(String serversJoined) {
		this.serversJoined = serversJoined;
	}

	public String getIngressDomain() {
		return ingressDomain;
	}

	public void setIngressDomain(String ingressDomain) {
		this.ingressDomain = ingressDomain;
	}

	@Override
	public String toString() {
		return "NDNS [domain=" + domain + ", servers=" + servers + ", upstreamServers="
				+ Arrays.toString(upstreamServers) + ", serversJoined=" + serversJoined + ", ingressDomain="
				+ ingressDomain + "]";
	}

}
