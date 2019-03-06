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

@JsonPropertyOrder({"vlan", "vlanInterface", "cidr", "localASNumber", "ranges", "additionalCidrs", "ingressCidr", "peers", "vrrpIP"})
public class NKSNNetwork extends NNetwork {

    @JsonProperty("additional_cidrs")
    private List<String> additionalCidrs;
    
    @JsonProperty("ingress_cidr")
    private String ingressCidr;
    
    @JsonProperty("local_asnumber")
    private String localASNumber;

    private String peerAsNumber;

    private List<NPeerNw> peers;

    private String peerScope;
    
    @JsonProperty("vrrp_ip")
    private String vrrpIP;

    public List<String> getAdditionalCidrs() {
        return additionalCidrs;
    }

    public String getIngressCidr() {
        return ingressCidr;
    }

    public String getLocalASNumber() {
        return localASNumber;
    }

    public String getPeerAsNumber() {
        return peerAsNumber;
    }

    public List<NPeerNw> getPeers() {
        return peers;
    }

    public String getPeerScope() {
        return peerScope;
    }

    public void setAdditionalCidrs(List<String> additionalCidrs) {
        this.additionalCidrs = additionalCidrs;
    }

    public void setIngressCidr(String ingressCidr) {
        this.ingressCidr = ingressCidr;
    }

    public void setLocalASNumber(String localASNumber) {
        this.localASNumber = localASNumber;
    }

    public void setPeerAsNumber(String peerAsNumber) {
        this.peerAsNumber = peerAsNumber;
    }

    public void setPeers(List<NPeerNw> peers) {
        this.peers = peers;
    }

    public void setPeerScope(String peerScope) {
        this.peerScope = peerScope;
    }

    @Override
    public String toString() {
        return "NKSNNetwork [additionalCidrs=" + additionalCidrs + ", ingressCidr=" + ingressCidr + ", localASNumber="
                + localASNumber + ", peerAsNumber=" + peerAsNumber + ", peers=" + peers + ", peerScope=" + peerScope
                + "]";
    }

}
