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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="akraino.onap")
public class Onap implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "onap_id_generator")
    @SequenceGenerator(name="onap_id_generator", sequenceName = "akraino.seq_onap", allocationSize=1)
    @Column(name="onap_id")
    private int onapId; 
    
    @OneToOne
    @JoinColumn(name = "edge_site_id")
    private EdgeSite edgeSite;
    
    @Column(name = "input_file")
    private byte [] inputFile;

    public int getOnapId() {
        return onapId;
    }

    public void setOnapId(int onapId) {
        this.onapId = onapId;
    }

    public EdgeSite getEdgeSite() {
        return edgeSite;
    }

    public void setEdgeSite(EdgeSite edgeSite) {
        this.edgeSite = edgeSite;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public byte[] getInputFile() {
        return inputFile;
    }

    public void setInputFile(byte[] inputFile) {
        this.inputFile = inputFile;
    }
    
}
