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
package org.akraino.portal.service;

import java.sql.SQLException;
import java.util.List;

import org.akraino.portal.dao.PodDAO;
import org.akraino.portal.entity.Pod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PodService {

    private static final Logger logger = Logger.getLogger(PodService.class);

    @Autowired
    private PodDAO podDAO;

    public void savePod(Pod pod) throws ClassNotFoundException, SQLException {

        podDAO.saveOrUpdate(pod);

    }

    public void updatePod(Pod modifiedPod) throws ClassNotFoundException, SQLException {

        Pod persistedPod = getPodDetails(modifiedPod.getPodId());

        persistedPod.setPodname(modifiedPod.getPodname());

        /*if (persistedPod.getRacks() != null) {
            Iterator<GenericRack> itr = persistedPod.getRacks().iterator();
            while (itr.hasNext()) {
                GenericRack persistedRack = itr.next();
    
                boolean found = false;
                for (GenericRack modifiedRack : modifiedPod.getRacks()) {
                    if (modifiedRack.getRackId() != null) {
                        if (persistedRack.getRackId() == modifiedRack.getRackId()) {
                            found = true;
                            // update existing racks
                            persistedRack.setRackname(modifiedRack.getRackname());
                            persistedRack.setRackPersonality(modifiedRack.getRackPersonality());
                        }
                    }
                }
                if (!found) {
                    // delete removed racks in the pod
                    itr.remove();
                }
            }
        }
        // add new racks to the pod
        for (GenericRack modifiedRack : modifiedPod.getRacks()) {
            if (modifiedRack.getRackId() == null) {
                persistedPod.addRack(modifiedRack);
            }
        }*/

        // flush to DB
        podDAO.merge(persistedPod);
    }

    public List<Pod> getPods() throws ClassNotFoundException, SQLException {

        return podDAO.getPods();

    }

    public Pod getPodDetails(Integer podId) {
        return podDAO.getPod(podId);
    }
    
    public void deletePod(Pod pod) throws ClassNotFoundException, SQLException {

        Pod persistentPod = getPodDetails(pod.getPodId());
        
        podDAO.deletePod(persistentPod);

    }

}
