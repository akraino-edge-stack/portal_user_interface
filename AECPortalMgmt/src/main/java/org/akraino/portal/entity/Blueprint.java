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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "akraino.blueprint")
public class Blueprint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blueprint_id_generator")
	@SequenceGenerator(name = "blueprint_id_generator", sequenceName = "akraino.seq_blueprint", allocationSize = 1)
	@Column(name = "blueprint_id")
	private int blueprintId;

	@Column(name = "blueprint_name")
	private String blueprintName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "blueprint_id")
	private Set<BlueprintRack> racks;

	public int getBlueprintId() {
		return blueprintId;
	}

	public void setBlueprintId(int blueprintId) {
		this.blueprintId = blueprintId;
	}

	public String getBlueprintName() {
		return blueprintName;
	}

	public void setBlueprintName(String blueprintName) {
		this.blueprintName = blueprintName;
	}

	public Set<BlueprintRack> getRacks() {
		return racks;
	}

	public void setRacks(Set<BlueprintRack> racks) {
		this.racks = racks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}