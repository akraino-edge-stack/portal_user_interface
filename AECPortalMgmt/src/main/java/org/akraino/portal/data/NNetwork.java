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

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"vlan", "vlanInterface", "cidr", "gateway", "subnet", "netmask", "routes", "ranges", "dns", })
public class NNetwork {

	private String cidr;

	private NDNS dns;

	private String gateway;

	private String inf;

	private boolean mesh;

	private String netmask;

	private NetworkRanges ranges;

	private Map<String, String> routes;

	private String subnet;
	private Integer vlan;

	@JsonProperty("interface")
	private String vlanInterface;
	
	private Integer mtu;

	public String getCidr() {
		return cidr;
	}

	public NDNS getDns() {
		return dns;
	}

	public String getGateway() {
		return gateway;
	}

	public String getInf() {
		return inf;
	}

	public String getNetmask() {
		return netmask;
	}

	public NetworkRanges getRanges() {
		return ranges;
	}

	public Map<String, String> getRoutes() {
		return routes;
	}

	public String getSubnet() {
		return subnet;
	}

	public String getVlanInterface() {
		return vlanInterface;
	}

	public boolean isMesh() {
		return mesh;
	}

	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public void setDns(NDNS dns) {
		this.dns = dns;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public void setInf(String inf) {
		this.inf = inf;
	}

	public void setMesh(boolean mesh) {
		this.mesh = mesh;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public void setRanges(NetworkRanges ranges) {
		this.ranges = ranges;
	}

	public void setRoutes(Map<String, String> routes) {
		this.routes = routes;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public void setVlanInterface(String vlanInterface) {
		this.vlanInterface = vlanInterface;
	}

	public Integer getVlan() {
		return vlan;
	}

	public void setVlan(Integer vlan) {
		this.vlan = vlan;
	}

	public Integer getMtu() {
		return mtu;
	}

	public void setMtu(Integer mtu) {
		this.mtu = mtu;
	}

	@Override
	public String toString() {
		return "NNetwork [cidr=" + cidr + ", dns=" + dns + ", gateway=" + gateway + ", inf=" + inf + ", mesh=" + mesh
				+ ", netmask=" + netmask + ", ranges=" + ranges + ", routes=" + routes + ", subnet=" + subnet
				+ ", vlan=" + vlan + ", vlanInterface=" + vlanInterface + "]";
	}

}
