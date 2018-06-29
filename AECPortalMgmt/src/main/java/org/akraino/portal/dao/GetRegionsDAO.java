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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.akraino.portal.common.DBConnection;
import org.akraino.portal.data.EdgeSite;
import org.akraino.portal.data.Region;

public class GetRegionsDAO {
	    		
    public static String getRegionSQL = "SELECT region.region_id, region.region_name FROM akraino.region";
    		
	public GetRegionsDAO()
	{
		
	}
    public List <Region> getRegions() throws ClassNotFoundException, SQLException 
    {
    	//DBConnection dbConnection = DBConnection.getInstance();
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement pstmt = connection.prepareStatement(getRegionSQL);
        ResultSet rs= pstmt.executeQuery();
        
        List<Region> regionList = new ArrayList<Region>();
        
        try {
	        while(rs.next())
	        {
	        	Region region = new Region();
	        	int regionId = rs.getInt(1);
	        	region.setRegionId(regionId);
	        	String regionName = rs.getString(2);
	        	region.setRegionName(regionName);
	        	regionList.add(region);
	        }
        } finally {
        	pstmt.close();
            rs.close();
        }
        
        return regionList;
		
    }

	
    
	

}
