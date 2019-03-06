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

import java.io.InputStream;
import java.util.List;

import org.akraino.portal.common.StringUtil;
import org.akraino.portal.config.AppConfig;
import org.akraino.portal.dao.PodMetricsDAO;
import org.akraino.portal.data.ChompObject;
import org.akraino.portal.entity.PodMetrics;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Service("podMetricsService")
@Transactional
public class PodMetricsService {
    
    private static final Logger logger = Logger.getLogger(PodMetricsService.class);
    
    @Autowired
    private PodMetricsDAO podMetricsDAO;
    
    private static final String CHOMP_JSON_FILENAME = "chomp.json";
    
    public boolean processChompInputFile(String chompData) throws Exception {
        
        String jsonString = StringUtil.unescape(chompData);
        
        logger.info("chomp json input as is:" + jsonString);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<ChompObject> chompList = objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, ChompObject.class));
        
        // store chomp output in db
        for (ChompObject chomp : chompList) {
            
            PodMetrics metric = new PodMetrics();
            
            metric.setName(chomp.getName());
            metric.settStart(chomp.getTstart());
            metric.settStop(chomp.getTstop());
            
            String latencySeries = "";
            for (String latency : chomp.getLatency()) {
                if (StringUtil.notEmpty(latencySeries)) {
                    latencySeries += ",";
                }
                latencySeries += latency;
            }
            metric.setLatency(latencySeries);
            
            metric.setLatencyAvg(chomp.getLatencyAvg());
            metric.setLatencyMin(chomp.getLatencyMin());
            metric.setLatencyMax(chomp.getLatencyMax());
            metric.setLogCount(chomp.getLogCount());
            metric.setType(chomp.getType());
            
            podMetricsDAO.createPodMetrics(metric);
        }
        
        logger.info("processed following chomp data" + chompList.toString());
        
        return true;
        
    }
    
    public List<ChompObject> getChompData() throws Exception {
        
        InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(CHOMP_JSON_FILENAME);
        
        String jsonString = StringUtil.unescape(IOUtils.toString(input));
        
        logger.info("chomp json input as is:" + jsonString);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<ChompObject> chompList = objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, ChompObject.class));
        
        logger.info("chomp json to java object: "+ chompList.toString());
        
        return chompList;
        
    }
    
}
