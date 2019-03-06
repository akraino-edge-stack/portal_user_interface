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

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChompObject {
    
    private String name;
    
    private Timestamp tstart;
    
    private Timestamp tstop;
    
    private String type;
    
    @JsonProperty("count")
    private Integer logCount;
    
    private String[] latency;
    
    @JsonProperty("latency_max")
    private Float latencyMax;
    
    @JsonProperty("latency_min")
    private Float latencyMin;
    
    @JsonProperty("latency_avg")
    private Float latencyAvg;
    
    private List<LogPair[]> logs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTstart() {
        return tstart;
    }

    public void setTstart(Timestamp tstart) {
        this.tstart = tstart;
    }

    public Timestamp getTstop() {
        return tstop;
    }

    public void setTstop(Timestamp tstop) {
        this.tstop = tstop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLogCount() {
        return logCount;
    }

    public void setLogCount(Integer logCount) {
        this.logCount = logCount;
    }

    public String[] getLatency() {
        return latency;
    }

    public void setLatency(String[] latency) {
        this.latency = latency;
    }

    public Float getLatencyMax() {
        return latencyMax;
    }

    public void setLatencyMax(Float latencyMax) {
        this.latencyMax = latencyMax;
    }

    public Float getLatencyMin() {
        return latencyMin;
    }

    public void setLatencyMin(Float latencyMin) {
        this.latencyMin = latencyMin;
    }

    public Float getLatencyAvg() {
        return latencyAvg;
    }

    public void setLatencyAvg(Float latencyAvg) {
        this.latencyAvg = latencyAvg;
    }

    public List<LogPair[]> getLogs() {
        return logs;
    }

    public void setLogs(List<LogPair[]> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "ChompObject [name=" + name + ", tstart=" + tstart + ", tstop=" + tstop + ", type=" + type
                + ", logCount=" + logCount + ", latency=" + Arrays.toString(latency) + ", latencyMax=" + latencyMax
                + ", latencyMin=" + latencyMin + ", latencyAvg=" + latencyAvg + ", logs=" + logs + "]";
    }

}
