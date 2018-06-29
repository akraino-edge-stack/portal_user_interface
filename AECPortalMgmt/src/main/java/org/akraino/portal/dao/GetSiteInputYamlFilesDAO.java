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
package org.akraino.portal.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.akraino.portal.common.DBConnection;
import org.akraino.portal.data.EdgeSite;
import org.akraino.portal.data.Region;
import org.akraino.portal.data.SiteFileInfo;

public class GetSiteInputYamlFilesDAO {
	    		
    public static String getSiteInputFiles = "select input_yaml_file_name, input_yaml_file_location, input_yaml_file_content from akraino.edge_site_input_yaml_files order by 1 asc";
    
	public GetSiteInputYamlFilesDAO()
	{
		
	}
    public List <SiteFileInfo> getSiteFileInfo() throws ClassNotFoundException, SQLException 
    {
    	//DBConnection dbConnection = DBConnection.getInstance();
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = connection.prepareStatement(getSiteInputFiles);
        ResultSet rs= pstmt.executeQuery();
        
        List<SiteFileInfo> siteInputFileList = new ArrayList<SiteFileInfo>();
        
        try {
        
	        while(rs.next())
	        {
	        	SiteFileInfo siteFileInfo = new SiteFileInfo();
	        	String fileName = rs.getString(1);
	        	siteFileInfo.setFileName(fileName);
	        	String fileLocation = rs.getString(2);
	        	siteFileInfo.setFileLocation(fileLocation);
	        	
	        	byte[]  b_fileContent = rs.getBytes(3);
	        	String fileContent = null;
	        	if (b_fileContent != null )
	        	{
					try {
						fileContent = new String(b_fileContent, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	
	        	siteFileInfo.setFileContent(fileContent);
				siteInputFileList.add(siteFileInfo);
	        }
        } finally {
        	pstmt.close();
        	rs.close();
        }

        return siteInputFileList;
		
    }
}
