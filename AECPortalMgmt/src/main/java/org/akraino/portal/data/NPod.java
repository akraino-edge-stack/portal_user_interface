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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * class construct to hold the generic pod details for any blueprint 
 * 
 * @author ld261v
 *
 */
@JsonPropertyOrder({"siteName", "ipmiAdmin", "networks", "dns", "sriovnets", "storage", 
    "genesis", "masters", "workers", "hardware", "disks", "disks_compute", "sshKey", "kubernetes", "regionalServer"})
public class NPod {

    @JsonProperty("site_name")
    private String siteName;
    
    // id rack credentials
    @JsonProperty("ipmi_admin")
    private Map<String, String> ipmiAdmin;
    
    // network information
    private NNetworks networks;
    
    // DNS information
    private NDNS dns;
    
    // sriov net information
    private List<NSRIOVNet> sriovnets;
    
    // storage information
    private NStorage storage;
    
    // genesis node
    private NNode genesis;
    
    // master nodes
    private List<NNode> masters;
    
    // worker nodes
    private List<NNode> workers;
    
    // hardware
    private NHardware hardware;
    
    private List<NDisk> disks;

    private List<NDisk> disks_compute;

    @JsonProperty("genesis_ssh_public_key")
    private String sshKey;
    
    private NKSNNode kubernetes;
    
    @JsonProperty("regional_server")
    private NRegionalServer regionalServer;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Map<String, String> getIpmiAdmin() {
        return ipmiAdmin;
    }

    public void setIpmiAdmin(Map<String, String> ipmiAdmin) {
        this.ipmiAdmin = ipmiAdmin;
    }

    public NNetworks getNetworks() {
        return networks;
    }

    public void setNetworks(NNetworks networks) {
        this.networks = networks;
    }

    public NDNS getDns() {
        return dns;
    }

    public void setDns(NDNS dns) {
        this.dns = dns;
    }

    public List<NSRIOVNet> getSriovnets() {
        return sriovnets;
    }

    public void setSriovnets(List<NSRIOVNet> sriovnets) {
        this.sriovnets = sriovnets;
    }

    public NStorage getStorage() {
        return storage;
    }

    public void setStorage(NStorage storage) {
        this.storage = storage;
    }

    public NNode getGenesis() {
        return genesis;
    }

    public void setGenesis(NNode genesis) {
        this.genesis = genesis;
    }

    public List<NNode> getMasters() {
        return masters;
    }

    public void setMasters(List<NNode> masters) {
        this.masters = masters;
    }
    
    public void addMasterNode(NNode node) {
        
        if (this.getMasters() == null) {
            this.setMasters(new ArrayList<>());
        }
        
        this.getMasters().add(node);
    }

    public List<NNode> getWorkers() {
        return workers;
    }

    public void setWorkers(List<NNode> workers) {
        this.workers = workers;
    }

    public NHardware getHardware() {
        return hardware;
    }

    public void setHardware(NHardware hardware) {
        this.hardware = hardware;
    }

    public List<NDisk> getDisks() {
        return disks;
    }

    public void setDisks(List<NDisk> disks) {
        this.disks = disks;
    }

    public List<NDisk> getDisks_compute() {
        return disks_compute;
    }

    public void setDisks_compute(List<NDisk> disks_compute) {
        this.disks_compute = disks_compute;
    }

    public String getSshKey() {
        return sshKey;
    }

    public void setSshKey(String sshKey) {
        this.sshKey = sshKey;
    }

    public NKSNNode getKubernetes() {
        return kubernetes;
    }

    public void setKubernetes(NKSNNode kubernetes) {
        this.kubernetes = kubernetes;
    }

    public NRegionalServer getRegionalServer() {
        return regionalServer;
    }

    public void setRegionalServer(NRegionalServer regionalServer) {
        this.regionalServer = regionalServer;
    }

    @Override
    public String toString() {
        return "NPod [siteName=" + siteName + ", ipmiAdmin=" + ipmiAdmin + ", networks=" + networks + ", dns=" + dns
                + ", sriovnets=" + sriovnets + ", storage=" + storage + ", genesis=" + genesis + ", masters=" + masters
                + ", workers=" + workers + ", hardware=" + hardware + ", disks=" + disks + ", disks_compute="
                + disks_compute + ", sshKey=" + sshKey + ", kubernetes=" + kubernetes + ", regionalServer="
                + regionalServer + "]";
    }

}
