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

public class NKSNNode extends NNode {

    @JsonProperty("api_service_ip")
    private String apiServiceIP;

    @JsonProperty("etcd_service_ip")
    private String etcdServiceIP;

    @JsonProperty("pod_cidr")
    private String podCIDR;

    @JsonProperty("service_cidr")
    private String serviceCIDR;

    public String getApiServiceIP() {
        return apiServiceIP;
    }

    public String getEtcdServiceIP() {
        return etcdServiceIP;
    }

    public String getPodCIDR() {
        return podCIDR;
    }

    public String getServiceCIDR() {
        return serviceCIDR;
    }

    public void setApiServiceIP(String apiServiceIP) {
        this.apiServiceIP = apiServiceIP;
    }

    public void setEtcdServiceIP(String etcdServiceIP) {
        this.etcdServiceIP = etcdServiceIP;
    }

    public void setPodCIDR(String podCIDR) {
        this.podCIDR = podCIDR;
    }

    public void setServiceCIDR(String serviceCIDR) {
        this.serviceCIDR = serviceCIDR;
    }

    @Override
    public String toString() {
        return "NKSNNode [apiServiceIP=" + apiServiceIP + ", etcdServiceIP=" + etcdServiceIP + ", podCIDR=" + podCIDR
                + ", serviceCIDR=" + serviceCIDR + "]";
    }

}
