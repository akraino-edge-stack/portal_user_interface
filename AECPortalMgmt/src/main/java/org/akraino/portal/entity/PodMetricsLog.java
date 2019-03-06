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

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "akraino.podmetrics_logs")
public class PodMetricsLog {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "podmetrics_logs_id_generator")
    @SequenceGenerator(name = "podmetrics_logs_id_generator", sequenceName = "akraino.seq_podmetrics_logs", allocationSize = 1)
    @Column(name = "podmetrics_id")
    private Long podMetricsLogId;

    @Column(name = "signature")
    private String signature;
    
    @Column(name = "tstart")
    private Timestamp tStart;
    
    @Column(name = "tstop")
    private Timestamp tStop;
    
    @Column(name = "sysdata_pod")
    private String sysdataPod;
    
    @Column(name = "sysdata_host")
    private String sysdataHost;
    
    @Column(name = "sysdata_namespace")
    private String sysdataNamespace;
    
    @Column(name = "chomp_log")
    private String log;
    
    @Column(name = "metadata_pod")
    private String metadataPod;
    
    @Column(name = "metadata_latency")
    private String metadataLatency;
    
    @Column(name = "metadata_namespace")
    private String metadataNamespace;

    public Long getPodMetricsLogId() {
        return podMetricsLogId;
    }

    public void setPodMetricsLogId(Long podMetricsLogId) {
        this.podMetricsLogId = podMetricsLogId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Timestamp gettStart() {
        return tStart;
    }

    public void settStart(Timestamp tStart) {
        this.tStart = tStart;
    }

    public Timestamp gettStop() {
        return tStop;
    }

    public void settStop(Timestamp tStop) {
        this.tStop = tStop;
    }

    public String getSysdataPod() {
        return sysdataPod;
    }

    public void setSysdataPod(String sysdataPod) {
        this.sysdataPod = sysdataPod;
    }

    public String getSysdataHost() {
        return sysdataHost;
    }

    public void setSysdataHost(String sysdataHost) {
        this.sysdataHost = sysdataHost;
    }

    public String getSysdataNamespace() {
        return sysdataNamespace;
    }

    public void setSysdataNamespace(String sysdataNamespace) {
        this.sysdataNamespace = sysdataNamespace;
    }

    public String getMetadataPod() {
        return metadataPod;
    }

    public void setMetadataPod(String metadataPod) {
        this.metadataPod = metadataPod;
    }

    public String getMetadataLatency() {
        return metadataLatency;
    }

    public void setMetadataLatency(String metadataLatency) {
        this.metadataLatency = metadataLatency;
    }

    public String getMetadataNamespace() {
        return metadataNamespace;
    }

    public void setMetadataNamespace(String metadataNamespace) {
        this.metadataNamespace = metadataNamespace;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
