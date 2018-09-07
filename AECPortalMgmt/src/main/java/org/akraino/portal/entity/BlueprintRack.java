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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "akraino.blueprint_rack")
public class BlueprintRack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rack_id_generator")
	@SequenceGenerator(name = "rack_id_generator", sequenceName = "akraino.seq_rack", allocationSize = 1)
	@Column(name = "brack_id")
	private Long bRackId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "brack_id")
	private Set<EdgeNode> edgeNodes;
	
	@OneToOne
    @JoinColumn(name = "grack_id")
	private GenericRack gRack;

	public Long getbRackId() {
		return bRackId;
	}

	public void setbRackId(Long bRackId) {
		this.bRackId = bRackId;
	}

	public Set<EdgeNode> getEdgeNodes() {
		return edgeNodes;
	}

	public void setEdgeNodes(Set<EdgeNode> edgeNodes) {
		this.edgeNodes = edgeNodes;
	}

	public GenericRack getgRack() {
		return gRack;
	}

	public void setgRack(GenericRack gRack) {
		this.gRack = gRack;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}