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

/**
 * class construct to hold the generic node information for all nodes in a pod
 * 
 * @author ld261v
 *
 */
@JsonPropertyOrder({"name", "rack", "pos", "oob", "host", "storage", "pxe", "ksn", "neutron", "rootPwd", "oem", 
	"macAddress", "biosTemplate", "bootTemplate", "httpBootDevice", "oobUser", "oobPwd, bootDevice"})
public class NNode {

	private String name;
	
	private Integer rack;
	
	private Integer pos;
	
	private String oob;
	
	private String host;
	
	private String storage;
	
	private String pxe;
	
	private String ksn;
	
	private String neutron;
	
	@JsonProperty("root_password")
	private String rootPwd;
	
	private String oem;
	
	@JsonProperty("mac_address")
	private String macAddress;
	
	@JsonProperty("bios_template")
	private String biosTemplate;
	
	@JsonProperty("boot_template")
	private String bootTemplate;
	
	@JsonProperty("http_boot_device")
	private String httpBootDevice;
	
	private String bootDevice;
	
	@JsonProperty("oob_user")
	private String oobUser;

	@JsonProperty("oob_password")
	private String oobPwd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOob() {
		return oob;
	}

	public void setOob(String oob) {
		this.oob = oob;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getPxe() {
		return pxe;
	}

	public void setPxe(String pxe) {
		this.pxe = pxe;
	}

	public String getKsn() {
		return ksn;
	}

	public void setKsn(String ksn) {
		this.ksn = ksn;
	}

	public String getNeutron() {
		return neutron;
	}

	public void setNeutron(String neutron) {
		this.neutron = neutron;
	}

	public String getRootPwd() {
		return rootPwd;
	}

	public void setRootPwd(String rootPwd) {
		this.rootPwd = rootPwd;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getBiosTemplate() {
		return biosTemplate;
	}

	public void setBiosTemplate(String biosTemplate) {
		this.biosTemplate = biosTemplate;
	}

	public String getBootTemplate() {
		return bootTemplate;
	}

	public void setBootTemplate(String bootTemplate) {
		this.bootTemplate = bootTemplate;
	}

	public String getHttpBootDevice() {
		return httpBootDevice;
	}

	public void setHttpBootDevice(String httpBootDevice) {
		this.httpBootDevice = httpBootDevice;
	}

	public String getOobUser() {
		return oobUser;
	}

	public void setOobUser(String oobUser) {
		this.oobUser = oobUser;
	}

	public String getOobPwd() {
		return oobPwd;
	}

	public void setOobPwd(String oobPwd) {
		this.oobPwd = oobPwd;
	}

	public Integer getRack() {
		return rack;
	}

	public void setRack(Integer rack) {
		this.rack = rack;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getBootDevice() {
		return bootDevice;
	}

	public void setBootDevice(String bootDevice) {
		this.bootDevice = bootDevice;
	}

	@Override
	public String toString() {
		return "NNode [name=" + name + ", rack=" + rack + ", pos=" + pos + ", oob=" + oob + ", host=" + host
				+ ", storage=" + storage + ", pxe=" + pxe + ", ksn=" + ksn + ", neutron=" + neutron + ", rootPwd="
				+ rootPwd + ", oem=" + oem + ", macAddress=" + macAddress + ", biosTemplate=" + biosTemplate
				+ ", bootTemplate=" + bootTemplate + ", httpBootDevice=" + httpBootDevice + ", oobUser=" + oobUser
				+ ", oobPwd=" + oobPwd + "]";
	}

}
