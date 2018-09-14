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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "akraino.pod")
public class Pod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pod_id_generator")
	@SequenceGenerator(name = "pod_id_generator", sequenceName = "akraino.seq_pod", allocationSize = 1)
	@Column(name = "pod_id")
	private Long podId;

	@Column(name = "pod_name")
	private String podname;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	@JoinColumn(name = "pod_id")
	private Set<GenericRack> racks;

	public Long getPodId() {
		return podId;
	}

	public void setPodId(Long podId) {
		this.podId = podId;
	}

	public String getPodname() {
		return podname;
	}

	public void setPodname(String podname) {
		this.podname = podname;
	}

	public Set<GenericRack> getRacks() {
		return racks;
	}

	public void setRacks(Set<GenericRack> racks) {
		this.racks = racks;
	}
	
	public void addRack(GenericRack rack) {
		this.getRacks().add(rack);
	}
}