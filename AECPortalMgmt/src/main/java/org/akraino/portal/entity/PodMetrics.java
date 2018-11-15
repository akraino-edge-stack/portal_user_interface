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
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "akraino.podmetrics")
public class PodMetrics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "podmetrics_id_generator")
	@SequenceGenerator(name = "podmetrics_id_generator", sequenceName = "akraino.seq_podmetrics", allocationSize = 1)
	@Column(name = "podmetrics_id")
	private Long podMetricsId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "tstart")
	private Timestamp tStart;
	
	@Column(name = "gen_type")
	private String type;
	
	@Column(name = "tstop")
	private Timestamp tStop;
	
	@Column(name = "logcount")
	private Integer logCount;
	
	@Column(name = "latency")
	private String latency;
	
	@Column(name = "latency_max")
	private Float latencyMax;
	
	@Column(name = "latency_min")
	private Float latencyMin;
	
	@Column(name = "latency_avg")
	private Float latencyAvg;

	public Long getPodMetricsId() {
		return podMetricsId;
	}

	public void setPodMetricsId(Long podMetricsId) {
		this.podMetricsId = podMetricsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getLogCount() {
		return logCount;
	}

	public void setLogCount(Integer logCount) {
		this.logCount = logCount;
	}

	public String getLatency() {
		return latency;
	}

	public void setLatency(String latency) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
