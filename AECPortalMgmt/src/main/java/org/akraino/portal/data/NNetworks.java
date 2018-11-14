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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"bonded", "primary", "slaves", "oob", "host", "nwStorage", "pxe", "ksn", "neutron"})
public class NNetworks {

	private String bonded;
	private String primary;
	private List<NSlaveNw> slaves;
	private NNetwork oob;

	private NNetwork host;
	
	@JsonProperty("storage")
	private NNetwork nwStorage;
	
	private NNetwork pxe;
	
	private NKSNNetwork ksn;

	private NNetwork neutron;

	public String getBonded() {
		return bonded;
	}

	public void setBonded(String bonded) {
		this.bonded = bonded;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public List<NSlaveNw> getSlaves() {
		return slaves;
	}

	public void setSlaves(List<NSlaveNw> slaves) {
		this.slaves = slaves;
	}

	public NNetwork getOob() {
		return oob;
	}

	public void setOob(NNetwork oob) {
		this.oob = oob;
	}

	public NNetwork getHost() {
		return host;
	}

	public void setHost(NNetwork host) {
		this.host = host;
	}

	public NNetwork getNwStorage() {
		return nwStorage;
	}

	public void setNwStorage(NNetwork nwStorage) {
		this.nwStorage = nwStorage;
	}

	public NNetwork getPxe() {
		return pxe;
	}

	public void setPxe(NNetwork pxe) {
		this.pxe = pxe;
	}

	public NKSNNetwork getKsn() {
		return ksn;
	}

	public void setKsn(NKSNNetwork ksn) {
		this.ksn = ksn;
	}

	public NNetwork getNeutron() {
		return neutron;
	}

	public void setNeutron(NNetwork neutron) {
		this.neutron = neutron;
	}

	@Override
	public String toString() {
		return "NNetworks [bonded=" + bonded + ", primary=" + primary + ", slaves=" + slaves + ", oob=" + oob
				+ ", host=" + host + ", nwStorage=" + nwStorage + ", pxe=" + pxe + ", ksn=" + ksn + ", neutron="
				+ neutron + "]";
	}

}
