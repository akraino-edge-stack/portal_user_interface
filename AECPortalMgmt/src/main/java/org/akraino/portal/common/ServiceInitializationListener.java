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

package org.akraino.portal.common;

import org.akraino.portal.service.EdgeSiteYamlTemplateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ServiceInitializationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(ServiceInitializationListener.class);
    
    @Autowired
    private EdgeSiteYamlTemplateService edgeSiteYamlTemplateService;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        
        // load all j2 template files into db
        loadJ2TemplateFiles();
        
    }
    
    private void loadJ2TemplateFiles() {

        logger.info("loading all j2 template files into portal DB on start up");
        
        try {
            edgeSiteYamlTemplateService.loadJ2TemplateFiles();
        } catch (Exception e) {
            
            logger.error("failed to load j2 template files into DB", e);
        }
        
    }

}