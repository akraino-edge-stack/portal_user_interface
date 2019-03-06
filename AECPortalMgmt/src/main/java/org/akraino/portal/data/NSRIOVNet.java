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

@JsonPropertyOrder({"physical", "pInterface", "vlanStart", "vlanEnd", "whitelists"})
public class NSRIOVNet {

    private String physical;
    
    @JsonProperty("interface")
    private String pInterface;

    @JsonProperty("vlan_start")
    private String vlanStart;
    
    @JsonProperty("vlan_end")
    private String vlanEnd;

    private List<NSRIOVWhitelist> whitelists;

    public String getPhysical() {
        return physical;
    }

    public void setPhysical(String physical) {
        this.physical = physical;
    }

    public String getpInterface() {
        return pInterface;
    }

    public void setpInterface(String pInterface) {
        this.pInterface = pInterface;
    }

    public String getVlanStart() {
        return vlanStart;
    }

    public void setVlanStart(String vlanStart) {
        this.vlanStart = vlanStart;
    }

    public String getVlanEnd() {
        return vlanEnd;
    }

    public void setVlanEnd(String vlanEnd) {
        this.vlanEnd = vlanEnd;
    }

    public List<NSRIOVWhitelist> getWhitelists() {
        return whitelists;
    }

    public void setWhitelists(List<NSRIOVWhitelist> whitelists) {
        this.whitelists = whitelists;
    }

    @Override
    public String toString() {
        return "NSRIOVNet [physical=" + physical + ", pInterface=" + pInterface + ", vlanStart=" + vlanStart
                + ", vlanEnd=" + vlanEnd + ", whitelists=" + whitelists + "]";
    }

}
