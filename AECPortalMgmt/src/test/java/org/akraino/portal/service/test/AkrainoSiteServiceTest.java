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
package org.akraino.portal.service.test;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.akraino.portal.config.AppConfig;
import org.akraino.portal.data.EdgeSite;
import org.akraino.portal.data.Onap;
import org.akraino.portal.data.SiteStatusRequest;
import org.akraino.portal.service.AkrainoSiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class AkrainoSiteServiceTest extends TestCase {

	
	@Autowired
    @Qualifier("akrainoService")
	AkrainoSiteService akrainoService;
	
	@Test
    public void testrun() {
		
		try {
			
			akrainoService.getUserSession("akraino");
			/*
			//akrainoService.getYamlContent("Boston");
			//akrainoService.getRegions();
			//List<EdgeSite> sites = akrainoService.getSites(1);
			//System.out.println("length-" + sites.size());
			
			//List<Onap> oanps = akrainoService.getOnapForSite("Boston");
			
			//System.out.println("size" + oanps.size());
			
			//SiteStatusRequest req = new SiteStatusRequest();
			
			//req.setSiteName("Houston");
			//req.setBuildStatus("hurray");
			//req.setCreateTarStatus("hurray too");
			//int res = akrainoService.updateSiteStatus(req);
			
			//System.out.println("response-" + res);
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
