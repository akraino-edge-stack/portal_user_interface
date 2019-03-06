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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "akraino.edgenode")
public class EdgeNode implements Serializable {

    /**
         * 
         */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "edgenode_id_generator")
    @SequenceGenerator(name = "edgenode_id_generator", sequenceName = "akraino.seq_edgeNode", allocationSize = 1)
    @Column(name = "edgenode_id")
    private Long edgeNodeId;

    @OneToOne
    @JoinColumn(name = "hardware_id")
    private EdgeSite hardware;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "akraino.edgenode_software", joinColumns = {
            @JoinColumn(name = "edgenode_id") }, inverseJoinColumns = { @JoinColumn(name = "software_id") })
    private Set<Software> softwares;

    public Long getEdgeNodeId() {
        return edgeNodeId;
    }

    public void setEdgeNodeId(Long edgeNodeId) {
        this.edgeNodeId = edgeNodeId;
    }

    public EdgeSite getHardware() {
        return hardware;
    }

    public void setHardware(EdgeSite hardware) {
        this.hardware = hardware;
    }

    public Set<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(Set<Software> softwares) {
        this.softwares = softwares;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}