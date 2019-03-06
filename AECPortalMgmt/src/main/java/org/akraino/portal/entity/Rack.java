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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="akraino.rack")
public class Rack implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rack_id_generator")
    @SequenceGenerator(name="rack_id_generator", sequenceName = "akraino.seq_rack", allocationSize=1)
    @Column(name="rack_id")
    private Long rackId; 
    
    @Column(name = "rack_name")
    private String rackname;
    
    @Column(name = "rack_personality")
    private String rackPersonality;
    
    @ManyToOne
    @JoinColumn(name = "pod_id")
    private Pod pod;
    
    public Long getRackId() {
        return rackId;
    }
    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }
    public String getRackname() {
        return rackname;
    }
    public void setRackname(String rackname) {
        this.rackname = rackname;
    }
    public String getRackPersonality() {
        return rackPersonality;
    }
    public void setRackPersonality(String rackPersonality) {
        this.rackPersonality = rackPersonality;
    }
    public Pod getPod() {
        return pod;
    }
    public void setPod(Pod pod) {
        this.pod = pod;
    }
    
}
