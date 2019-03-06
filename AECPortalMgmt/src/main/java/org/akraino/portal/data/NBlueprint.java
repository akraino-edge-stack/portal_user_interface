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

public class NBlueprint {

    private String blueprintFamily;
    private String blueprintName;
    private String ETETesting;
    private String Monitoring;

    public String getBlueprintFamily() {
        return blueprintFamily;
    }

    public String getBlueprintName() {
        return blueprintName;
    }

    public String getETETesting() {
        return ETETesting;
    }

    public String getMonitoring() {
        return Monitoring;
    }

    public void setBlueprintFamily(String blueprintFamily) {
        this.blueprintFamily = blueprintFamily;
    }

    public void setBlueprintName(String blueprintName) {
        this.blueprintName = blueprintName;
    }

    public void setETETesting(String eTETesting) {
        ETETesting = eTETesting;
    }

    public void setMonitoring(String monitoring) {
        Monitoring = monitoring;
    }

    @Override
    public String toString() {
        return "NBlueprint [blueprintFamily=" + blueprintFamily + ", blueprintName=" + blueprintName + ", ETETesting="
                + ETETesting + ", Monitoring=" + Monitoring + "]";
    }

}
