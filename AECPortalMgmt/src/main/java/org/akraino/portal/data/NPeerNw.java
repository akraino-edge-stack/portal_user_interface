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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"ip", "scope", "asnumber"})
public class NPeerNw {

    private Integer asnumber;
    private String ip;
    private String scope;

    public Integer getAsnumber() {
        return asnumber;
    }

    public String getIp() {
        return ip;
    }

    public String getScope() {
        return scope;
    }

    public void setAsnumber(Integer asnumber) {
        this.asnumber = asnumber;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "NPeerNw [asnumber=" + asnumber + ", ip=" + ip + ", scope=" + scope + "]";
    }

}
