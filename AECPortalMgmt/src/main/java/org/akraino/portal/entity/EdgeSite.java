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

package org.akraino.portal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="akraino.edge_site")
public class EdgeSite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_id_generator")
	@SequenceGenerator(name = "site_id_generator", sequenceName = "akraino.seq_site", allocationSize = 1)
	@Column(name="edge_site_id")
	private int edgeSiteId;
	
	@Column(name="edge_site_name")
	private String edgeSiteName;
	
	@Column(name="input_file")
	private byte [] inputFile;
	
	@ManyToOne
    @JoinColumn(name = "region_id")
	private Region region;
	
	@Column(name="edge_site_ip")
	private String edgeSiteIP;
	
	@Column(name="edge_site_user")
	private String edgeSiteUser;
	
	@Column(name="edge_site_pwd")
	private String edgeSitePwd;
	
	@Column(name="build_dt")
	private Date buildDate;
	
	@Column(name="deploy_dt")
	private Date deployDate;
	
	@Column(name="deploy_status")
	private String deployStatus;
	
	@Column(name="onap_status")
	private String onapStatus;
	
	@Column(name="tempest_status")
	private String tempestStatus;
	
	@Column(name="vcdn_status")
	private String vCDNStatus;
	
	@Column(name="edge_site_blueprint")
	private String blueprint;
	
	@Column(name="output_yaml_file_1")
	private byte[] outputYaml1;
	
	@Column(name="output_yaml_file_2")
	private byte[] outputYaml2;
	
	@Column(name="output_yaml_file_3")
	private byte[] outputYaml3;
	
	@Column(name="output_yaml_file_4")
	private byte[] outputYaml4;
	
	@Column(name="output_yaml_file_5")
	private byte[] outputYaml5;
	
	@Column(name="output_yaml_file_6")
	private byte[] outputYaml6;
	
	@Column(name="output_yaml_file_7")
	private byte[] outputYaml7;
	
	@Column(name="output_yaml_file_8")
	private byte[] outputYaml8;
	
	@Column(name="output_yaml_file_9")
	private byte[] outputYaml9;
	
	@Column(name="output_yaml_file_10")
	private byte[] outputYaml10;
	
	@Column(name="output_yaml_file_11")
	private byte[] outputYaml11;
	
	@Column(name="output_yaml_file_12")
	private byte[] outputYaml12;
	
	@Column(name="output_yaml_file_13")
	private byte[] outputYaml13;
	
	@Column(name="output_yaml_file_14")
	private byte[] outputYaml14;
	
	@Column(name="output_yaml_file_15")
	private byte[] outputYaml15;
	
	@Column(name="output_yaml_file_16")
	private byte[] outputYaml16;
	
	@Column(name="output_yaml_file_17")
	private byte[] outputYaml17;
	
	@Column(name="output_yaml_file_18")
	private byte[] outputYaml18;
	
	@Column(name="output_yaml_file_19")
	private byte[] outputYaml19;
	
	@Column(name="output_yaml_file_20")
	private byte[] outputYaml20;
	
	@Column(name="output_yaml_file_21")
	private byte[] outputYaml21;
	
	@Column(name="output_yaml_file_22")
	private byte[] outputYaml22;
	
	@Column(name="output_yaml_file_23")
	private byte[] outputYaml23;
	
	@Column(name="output_yaml_file_24")
	private byte[] outputYaml24;
	
	@Column(name="build_status")
	private String edgeSiteBuildStatus;
	
	@Column(name="multinode_createtar_status")
	private String edgeSiteDeployCreateTarStatus;
	
	@Column(name="multinode_genesisnode_status")
	private String edgeSiteDeployGenesisNodeStatus;
	
	@Column(name="multinode_deploytool_status")
	private String edgeSiteDeployDeployToolStatus;

	@Column(name="multinode_transferfile_status")
	private String transferFileStatus;
	
	@Column(name="deploy_mode")
	private String deployMode;
	
	@Column(name="singlenode_createyaml_status")
	private String nodeYamlStatus;
	
	@Column(name="singlenode_transferfile_status")
	private String nodetransferFileStatus;
	
	@Column(name="singlenode_deploy_status")
	private String nodeDeployStatus;
	
	public int getEdgeSiteId() {
		return edgeSiteId;
	}

	public void setEdgeSiteId(int edgeSiteId) {
		this.edgeSiteId = edgeSiteId;
	}

	public String getEdgeSiteName() {
		return edgeSiteName;
	}

	public void setEdgeSiteName(String edgeSiteName) {
		this.edgeSiteName = edgeSiteName;
	}

	public byte[] getInputFile() {
		return inputFile;
	}

	public void setInputFile(byte[] inputFile) {
		this.inputFile = inputFile;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
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

	public Date getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	public Date getDeployDate() {
		return deployDate;
	}

	public void setDeployDate(Date deployDate) {
		this.deployDate = deployDate;
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

	public String getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(String blueprint) {
		this.blueprint = blueprint;
	}

	public byte[] getOutputYaml1() {
		return outputYaml1;
	}

	public void setOutputYaml1(byte[] outputYaml1) {
		this.outputYaml1 = outputYaml1;
	}

	public byte[] getOutputYaml2() {
		return outputYaml2;
	}

	public void setOutputYaml2(byte[] outputYaml2) {
		this.outputYaml2 = outputYaml2;
	}

	public byte[] getOutputYaml3() {
		return outputYaml3;
	}

	public void setOutputYaml3(byte[] outputYaml3) {
		this.outputYaml3 = outputYaml3;
	}

	public byte[] getOutputYaml4() {
		return outputYaml4;
	}

	public void setOutputYaml4(byte[] outputYaml4) {
		this.outputYaml4 = outputYaml4;
	}

	public byte[] getOutputYaml5() {
		return outputYaml5;
	}

	public void setOutputYaml5(byte[] outputYaml5) {
		this.outputYaml5 = outputYaml5;
	}

	public byte[] getOutputYaml6() {
		return outputYaml6;
	}

	public void setOutputYaml6(byte[] outputYaml6) {
		this.outputYaml6 = outputYaml6;
	}

	public byte[] getOutputYaml7() {
		return outputYaml7;
	}

	public void setOutputYaml7(byte[] outputYaml7) {
		this.outputYaml7 = outputYaml7;
	}

	public byte[] getOutputYaml8() {
		return outputYaml8;
	}

	public void setOutputYaml8(byte[] outputYaml8) {
		this.outputYaml8 = outputYaml8;
	}

	public byte[] getOutputYaml9() {
		return outputYaml9;
	}

	public void setOutputYaml9(byte[] outputYaml9) {
		this.outputYaml9 = outputYaml9;
	}

	public byte[] getOutputYaml10() {
		return outputYaml10;
	}

	public void setOutputYaml10(byte[] outputYaml10) {
		this.outputYaml10 = outputYaml10;
	}

	public byte[] getOutputYaml11() {
		return outputYaml11;
	}

	public void setOutputYaml11(byte[] outputYaml11) {
		this.outputYaml11 = outputYaml11;
	}

	public byte[] getOutputYaml12() {
		return outputYaml12;
	}

	public void setOutputYaml12(byte[] outputYaml12) {
		this.outputYaml12 = outputYaml12;
	}

	public byte[] getOutputYaml13() {
		return outputYaml13;
	}

	public void setOutputYaml13(byte[] outputYaml13) {
		this.outputYaml13 = outputYaml13;
	}

	public byte[] getOutputYaml14() {
		return outputYaml14;
	}

	public void setOutputYaml14(byte[] outputYaml14) {
		this.outputYaml14 = outputYaml14;
	}

	public byte[] getOutputYaml15() {
		return outputYaml15;
	}

	public void setOutputYaml15(byte[] outputYaml15) {
		this.outputYaml15 = outputYaml15;
	}

	public byte[] getOutputYaml16() {
		return outputYaml16;
	}

	public void setOutputYaml16(byte[] outputYaml16) {
		this.outputYaml16 = outputYaml16;
	}

	public byte[] getOutputYaml17() {
		return outputYaml17;
	}

	public void setOutputYaml17(byte[] outputYaml17) {
		this.outputYaml17 = outputYaml17;
	}

	public byte[] getOutputYaml18() {
		return outputYaml18;
	}

	public void setOutputYaml18(byte[] outputYaml18) {
		this.outputYaml18 = outputYaml18;
	}

	public byte[] getOutputYaml19() {
		return outputYaml19;
	}

	public void setOutputYaml19(byte[] outputYaml19) {
		this.outputYaml19 = outputYaml19;
	}

	public byte[] getOutputYaml20() {
		return outputYaml20;
	}

	public void setOutputYaml20(byte[] outputYaml20) {
		this.outputYaml20 = outputYaml20;
	}

	public byte[] getOutputYaml21() {
		return outputYaml21;
	}

	public void setOutputYaml21(byte[] outputYaml21) {
		this.outputYaml21 = outputYaml21;
	}

	public byte[] getOutputYaml22() {
		return outputYaml22;
	}

	public void setOutputYaml22(byte[] outputYaml22) {
		this.outputYaml22 = outputYaml22;
	}

	public byte[] getOutputYaml23() {
		return outputYaml23;
	}

	public void setOutputYaml23(byte[] outputYaml23) {
		this.outputYaml23 = outputYaml23;
	}

	public byte[] getOutputYaml24() {
		return outputYaml24;
	}

	public void setOutputYaml24(byte[] outputYaml24) {
		this.outputYaml24 = outputYaml24;
	}

	public String getEdgeSiteBuildStatus() {
		return edgeSiteBuildStatus;
	}

	public void setEdgeSiteBuildStatus(String edgeSiteBuildStatus) {
		this.edgeSiteBuildStatus = edgeSiteBuildStatus;
	}

	public String getEdgeSiteDeployCreateTarStatus() {
		return edgeSiteDeployCreateTarStatus;
	}

	public void setEdgeSiteDeployCreateTarStatus(String edgeSiteDeployCreateTarStatus) {
		this.edgeSiteDeployCreateTarStatus = edgeSiteDeployCreateTarStatus;
	}

	public String getEdgeSiteDeployGenesisNodeStatus() {
		return edgeSiteDeployGenesisNodeStatus;
	}

	public void setEdgeSiteDeployGenesisNodeStatus(String edgeSiteDeployGenesisNodeStatus) {
		this.edgeSiteDeployGenesisNodeStatus = edgeSiteDeployGenesisNodeStatus;
	}

	public String getEdgeSiteDeployDeployToolStatus() {
		return edgeSiteDeployDeployToolStatus;
	}

	public void setEdgeSiteDeployDeployToolStatus(String edgeSiteDeployDeployToolStatus) {
		this.edgeSiteDeployDeployToolStatus = edgeSiteDeployDeployToolStatus;
	}

	public String getDeployMode() {
		return deployMode;
	}

	public void setDeployMode(String deployMode) {
		this.deployMode = deployMode;
	}

	public String getTransferFileStatus() {
		return transferFileStatus;
	}

	public void setTransferFileStatus(String transferFileStatus) {
		this.transferFileStatus = transferFileStatus;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
