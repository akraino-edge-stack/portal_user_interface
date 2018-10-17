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

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.akraino.portal.data.AccessResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LDAPAuthentication {
	
	private static final Logger logger = Logger.getLogger(LDAPAuthentication.class);
	
	public static final String AUTHORIZED = "Authorized";
	public static final String UNAUTHORIZED = "Unauthorized";
	
	public synchronized AccessResponse authenticateUser(String user, String pwd) {
		
		AccessResponse authResponse = new AccessResponse();
		
		try {
			
			String serviceUserDN = "uid=" + user + "," + "ou=users,dc=akraino,dc=org";
			String serviceUserPassword = pwd;
			
			Hashtable env = new Hashtable(11);
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, PropertyUtil.getInstance().getProperty("apacheds.ldap.url"));
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
	        env.put(Context.SECURITY_PRINCIPAL, serviceUserDN);
	        env.put(Context.SECURITY_CREDENTIALS, serviceUserPassword);
			
	        LdapContext ctx = new InitialLdapContext(env, null);
	        ctx.setRequestControls(null);
	        NamingEnumeration<?> namingEnum = ctx.search("ou=users", "(objectclass=*)", getSimpleSearchControls());
	        
	        while (namingEnum.hasMore()) {
	            SearchResult result = (SearchResult) namingEnum.next ();    
	            Attributes attrs = result.getAttributes();
	            
				authResponse.setMessage(AUTHORIZED);

	        } 
			
		} 
		catch (Exception e) {
			logger.error(e);

			authResponse.setMessage(UNAUTHORIZED);
		}
		
		return authResponse;
		
	}
	
	
	private synchronized SearchControls getSimpleSearchControls() {
	    SearchControls searchControls = new SearchControls();
	    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	    searchControls.setTimeLimit(0);
	    searchControls.setCountLimit(1000);

	    return searchControls;
	}

}
