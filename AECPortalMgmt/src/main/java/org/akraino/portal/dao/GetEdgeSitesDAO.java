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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.akraino.portal.common.DBConnection;
import org.akraino.portal.common.DateUtil;
import org.akraino.portal.common.StringUtil;
import org.akraino.portal.data.EdgeSite;
import org.akraino.portal.data.Onap;
import org.akraino.portal.data.UserSession;

public class GetEdgeSitesDAO {
	
    private String getEdgeSiteSQL = "SELECT "
    		+ "edge_site.edge_site_id, "
    		+ "edge_site.edge_site_name, "
    		+ "edge_site.input_file, "
    		+ "edge_site.build_status, "
    		+ "edge_site.deploy_createtar_status, "
    		+ "edge_site.deploy_genesisnodestatus_status, "
    		+ "edge_site.deploy_deploytoolstatus_status, "
    		+ "region.region_name, "
    		+ "edge_site.build_dt, "
    		+ "edge_site.deploy_dt, "
    		+ "edge_site.deploy_status, "
    		+ "edge_site.onap_status, "
    		+ "edge_site.tempest_status, "
    		+ "edge_site.vcdn_status "
    				+ "FROM  akraino.edge_site edge_site, akraino.region region where edge_site.region_id = region.region_id \r\n";
    				
   private String getOnapSQL = "SELECT "
   		+ "onap.onap_id, "
   		+ "onap.edge_site_name, "
   		+ "onap.public_net_name, "
   		+ "onap.public_subnet_cidr, "
   		+ "onap.public_subnet_allocation_start, "
   		+ "onap.public_subnet_allocation_end, "
   		+ "onap.public_subnet_dns_nameserver, "
   		+ "onap.public_subnet_gateway_ip, "
   		+ "onap.onap_vm_public_key, "
   		+ "onap.onap_repo, "
   		+ "onap.http_proxy, "
   		+ "onap.https_proxy, "
   		+ "onap.no_proxy "
   			+ "FROM  akraino.onap onap \r\n";
    
    private String updateEdgeSiteSQL = "Update akraino.edge_site "
    		+ "set output_yaml_file_1 = ?"
    		+ ",output_yaml_file_2 = ? "
    		+ ",output_yaml_file_3 = ? "
    		+ ",output_yaml_file_4 = ? "
    		+ ",output_yaml_file_5 = ? "
    		+ ",output_yaml_file_6 = ? "
    		+ ",output_yaml_file_7 = ? "
    		+ ",output_yaml_file_8 = ? "
    		+ ",output_yaml_file_9 = ? "
    		+ ",output_yaml_file_10 = ? "
    		+ ",output_yaml_file_11 = ? "
    		+ ",output_yaml_file_12 = ? "
    		+ ",output_yaml_file_13 = ? "
    		+ ",output_yaml_file_14 = ? "
    		+ ",output_yaml_file_15 = ? "
    		+ ",output_yaml_file_16 = ? "
    		+ ",output_yaml_file_17 = ? "
    		+ ",output_yaml_file_18 = ? "
    		+ ",output_yaml_file_19 = ? "
    		+ ",output_yaml_file_20 = ? "
    		+ ",output_yaml_file_21 = ? "
    		+ "where edge_site_name = ?";
    
    private String updateOnapSQL = "Update akraino.onap "
    		+ "set public_net_name = ? "
    		+ ",public_subnet_cidr = ? "
    		+ ",public_subnet_allocation_start = ? "
    		+ ",public_subnet_allocation_end = ? "
    		+ ",public_subnet_dns_nameserver = ? "
    		+ ",public_subnet_gateway_ip = ? "
    		+ ",onap_vm_public_key = ? "
    		+ ",onap_repo = ? "
    		+ ",http_proxy = ? "
    		+ ",https_proxy = ? "
    		+ ",no_proxy = ? "
    		+ "where edge_site_name = ?";
    
    private String insertUserSQL = "Insert into akraino.usersession values (?, ?, ?)";
    
    private String updateUserSQL = "update akraino.usersession set token_id=? , crt_dt=? where login_id=?";
    		
    private String updateEdgeSiteStatusSQL = "Update akraino.edge_site ";
	
    
	public GetEdgeSitesDAO()
	{
		
	}
    public List <EdgeSite> getEdgeSites(int regionId) throws ClassNotFoundException, SQLException 
    {
    	//DBConnection dbConnection = DBConnection.getInstance();
		Connection connection = DBConnection.getInstance().getConnection();
		
		if (regionId != 0) {
			getEdgeSiteSQL += " AND edge_site.region_id = ?";
		}
		
		PreparedStatement pstmt = connection.prepareStatement(getEdgeSiteSQL);
		if (regionId != 0) {
			pstmt.setInt(1, regionId);
		}
		
        ResultSet rs= pstmt.executeQuery();
        List<EdgeSite> edgeSiteList = new ArrayList<EdgeSite>();
        try {
        
	        
	        while(rs.next())
	        {
	        	EdgeSite edgeSite = new EdgeSite();
	        	int edgeSiteId = rs.getInt(1);
	        	edgeSite.setEdgeSiteId(edgeSiteId);
	        	String edgeSiteName = rs.getString(2);
	        	edgeSite.setEdgeSiteName(edgeSiteName);
	        	byte[] b_input_file = rs.getBytes(3);
	        	
	        	String buildStatus = rs.getString(4);
	        	edgeSite.setEdgeSiteBuildStatus(buildStatus);
	        	
	        	String edgeSiteDeployCreateTarStatus = rs.getString(5);
	        	edgeSite.setEdgeSiteDeployCreateTarStatus(edgeSiteDeployCreateTarStatus);
	        	
	
	        	String deployTarStatus = rs.getString(6);
	        	edgeSite.setEdgeSiteDeployCreateTarStatus(deployTarStatus);
	        	
	        	String deploygenesisStatus = rs.getString(7);
	        	edgeSite.setEdgeSiteDeployGenesisNodeStatus(deploygenesisStatus);
	        
	        	String region = rs.getString(8);
	        	edgeSite.setRegionName(region);
	        	
	        	Date buildDate = rs.getDate(9);
	        	edgeSite.setBuildDate(DateUtil.sqlToUtil(buildDate));
	        	
	        	Date deployDate = rs.getDate(10);
	        	edgeSite.setDeployDate(DateUtil.sqlToUtil(deployDate));
	        	
	        	String deployStatus = rs.getString(11);
	        	edgeSite.setDeployStatus(deployStatus);
	        	
	        	String onapStatus = rs.getString(12);
	        	edgeSite.setOnapStatus(onapStatus);
	        	
	        	String tempestStatus = rs.getString(13);
	        	edgeSite.setTempestStatus(tempestStatus);
	        	
	        	String vcdnStatus = rs.getString(14);
	        	edgeSite.setvCDNStatus(vcdnStatus);
	        	
	 	        try {
					String input_file = new String(b_input_file, "UTF-8");
					edgeSite.setInputFile(input_file);
					
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}
	 	       
	 	       edgeSiteList.add(edgeSite);
	        	
	        }
        } finally {
        	pstmt.close();
            rs.close();
        }

        return edgeSiteList;
		
    }
    
    public List <Onap> getOnap(String siteName) throws ClassNotFoundException, SQLException { 
    	//DBConnection dbConnection = DBConnection.getInstance();
		Connection connection = DBConnection.getInstance().getConnection();
		
		PreparedStatement pstmt = connection.prepareStatement(getOnapSQL);

        ResultSet rs= pstmt.executeQuery();
        
        List<Onap> onapList = new ArrayList<Onap>();
        
        try {
	        while(rs.next())
	        {
	        	Onap onap = new Onap();
	        	int onapId = rs.getInt(1);
	        	onap.setId(onapId);
	        	String edgeSiteName = rs.getString(2);
	        	onap.setSiteName(edgeSiteName);
	               	
	        	String publicNetName = rs.getString(3);
	        	onap.setPublicNetName(publicNetName);
	        	
	        	String publicSubnetCIDR = rs.getString(4);
	        	onap.setPublicSubnetCIDR(publicSubnetCIDR);
	        	
	
	        	String publicSubnetAllocStart = rs.getString(5);
	        	onap.setPublicSubnetAllocStart(publicSubnetAllocStart);
	        	
	        	String publicSubnetAllocEnd = rs.getString(6);
	        	onap.setPublicSubnetAllocEnd(publicSubnetAllocEnd);
	        
	        	String publicSubnetDNSNameServer = rs.getString(7);
	        	onap.setPublicSubnetDNSNameServer(publicSubnetDNSNameServer);
	
	        	String publicSubnetGtwyIP = rs.getString(8);
	        	onap.setPublicSubnetGtwyIP(publicSubnetGtwyIP);
	        	
	        	String onapVMPubKey = rs.getString(9);
	        	onap.setOnapVMPubKey(onapVMPubKey);
	        	
	        	String onapRepo = rs.getString(10);
	        	onap.setOnapRepo(onapRepo);
	        	
	        	String httpProxy = rs.getString(11);
	        	onap.setHttpProxy(httpProxy);
	        	
	        	String httpsProxy = rs.getString(12);
	        	onap.setHttpsProxy(httpsProxy);
	        	
	        	String noProxy = rs.getString(13);
	        	onap.setNoProxy(noProxy);
	 	       
	 	       onapList.add(onap);
	        	
	        }
        } finally {
        	pstmt.close();
        	rs.close();
         }

        return onapList;
    }
    
    public int createUserSession (String userid, String tokenId) throws ClassNotFoundException, SQLException {
    	
    	Connection connection = DBConnection.getInstance().getConnection();
		
    	PreparedStatement pstmt = connection.prepareStatement(insertUserSQL);
    	
    	pstmt.setString(1, userid);
    	pstmt.setString(2, tokenId);
    	pstmt.setDate(3, DateUtil.utilToSql(Calendar.getInstance().getTime()));
    	
    	int returnValue;
    	
    	returnValue = pstmt.executeUpdate();
    	
    	connection.commit();
    	pstmt.close();

    	
    	return returnValue;
    	
    }
    
    public int updateUserSession (String userid, String tokenId) throws ClassNotFoundException, SQLException {
    	
    	Connection connection = DBConnection.getInstance().getConnection();
		
    	PreparedStatement pstmt = connection.prepareStatement(updateUserSQL);
    	
    	pstmt.setString(1, tokenId);
    	pstmt.setDate(2, DateUtil.utilToSql(Calendar.getInstance().getTime()));
    	pstmt.setString(3, userid);
    	
    	int returnValue;
    	
    	returnValue = pstmt.executeUpdate();
    	
    	connection.commit();
    	pstmt.close();
    	
    	return returnValue;
    	
    }
    
    public UserSession getUserSession(String userId) throws ClassNotFoundException, SQLException {
    	
    	Connection connection = DBConnection.getInstance().getConnection();
		
    	PreparedStatement pstmt = connection.prepareStatement("select login_id, token_id, crt_dt from akraino.usersession where login_id = ?");
    	
    	pstmt.setString(1, userId);
    	
    	ResultSet rs= pstmt.executeQuery();
    	
    	UserSession user = new UserSession();
        
        try {
	        while(rs.next())
	        {
	        	user.setLoginId(rs.getString(1));
	        	user.setTokenId(rs.getString(2));
	        	user.setCreatedDate(DateUtil.sqlToUtil(rs.getDate(3)));
	        }
        } finally {
        	pstmt.close();
        	rs.close();
         }
        
        return user;
    	
    }
    
    public int deleteUserSession(String userId) throws ClassNotFoundException, SQLException {
    	
    	Connection connection = DBConnection.getInstance().getConnection();
    	
    	PreparedStatement pstmt = connection.prepareStatement("delete from akraino.usersession where login_id = ?");
    	int returnValue;
    	
    	try { 
	    	
	    	pstmt.setString(1, userId);
	    	
	    	returnValue = pstmt.executeUpdate();
	    	connection.commit();
	    	
    	} finally {
    	
    		pstmt.close();
    		
    	}
	    	
    	return returnValue;
	    	
    }
    	
    public int updateOnap (Onap onap) throws ClassNotFoundException, SQLException {
    	
    	//DBConnection dbConnection = DBConnection.getInstance();
		Connection connection = DBConnection.getInstance().getConnection();
		
    	PreparedStatement pstmt = connection.prepareStatement(updateOnapSQL);
    	
    	int returnValue;
    	
    	try {
    		
	    	pstmt.setString(1, onap.getPublicNetName());
	    	pstmt.setString(2, onap.getPublicSubnetCIDR());
	    	pstmt.setString(3, onap.getPublicSubnetAllocStart());
	    	pstmt.setString(4, onap.getPublicSubnetAllocEnd());
	    	pstmt.setString(5, onap.getPublicSubnetDNSNameServer());
	    	pstmt.setString(6, onap.getPublicSubnetGtwyIP());
	    	pstmt.setString(7, onap.getOnapVMPubKey());
	    	pstmt.setString(8, onap.getOnapRepo());
	    	pstmt.setString(9, onap.getHttpProxy());
	    	pstmt.setString(10, onap.getHttpsProxy());
	    	pstmt.setString(11, onap.getNoProxy());
	    	pstmt.setString(12, onap.getSiteName());
			
			returnValue = pstmt.executeUpdate();
			connection.commit();
			
		} finally {
	         	pstmt.close();
			}
    	
    	
    	
    	return returnValue;
    	
    }
    
    public int updateEdgeSiteStatus(EdgeSite edgeSite) throws ClassNotFoundException, SQLException {
		//DBConnection dbConnection = DBConnection.getInstance();
		Connection connection = DBConnection.getInstance().getConnection();
		
		int returnValue;
		
		int counter=0;
		int buildCount=0;
		int tarCount=0;
		int genCount=0;
		int toolCount=0;
		int buildDateCount=0;
		int deployDateCount = 0;
		int deployStatusCount = 0;
		int onapStatusCount = 0;
		int tempestStatusCount = 0;
		int vcdnStatusCount = 0;
		if (StringUtil.notEmpty(edgeSite.getEdgeSiteBuildStatus())) {
			updateEdgeSiteStatusSQL += "set build_status = ? ";
			counter++;
			buildCount = counter;
		}
		if (StringUtil.notEmpty(edgeSite.getEdgeSiteDeployCreateTarStatus())) {
			updateEdgeSiteStatusSQL += ",deploy_createtar_status = ? ";
			counter++;
			tarCount = counter;
		}
		if (StringUtil.notEmpty(edgeSite.getEdgeSiteDeployGenesisNodeStatus())) {
			updateEdgeSiteStatusSQL += ",deploy_genesisnodestatus_status = ? ";
			counter++;
			genCount = counter;
		}
		if (StringUtil.notEmpty(edgeSite.getEdgeSiteDeployGenesisNodeStatus())) {
			updateEdgeSiteStatusSQL += ",deploy_deploytoolstatus_status = ? ";
			counter++;
			toolCount = counter;
		}
		if (edgeSite.getBuildDate() != null) {
			updateEdgeSiteStatusSQL += ",build_dt = ? ";
			counter++;
			buildDateCount = counter;
		}
		if (edgeSite.getDeployDate() != null) {
			updateEdgeSiteStatusSQL += ",deploy_dt = ? ";
			counter++;
			deployDateCount = counter;
		}
		if (StringUtil.notEmpty(edgeSite.getDeployStatus())) {
			updateEdgeSiteStatusSQL += ",deploy_status = ? ";
			counter++;
			deployStatusCount = counter;
		}
		if (StringUtil.notEmpty(edgeSite.getOnapStatus())) {
			updateEdgeSiteStatusSQL += ",onap_status = ? ";
			counter++;
			onapStatusCount = counter;
		}
		if (StringUtil.notEmpty(edgeSite.getTempestStatus())) {
			updateEdgeSiteStatusSQL += ",tempest_status = ? ";
			counter++;
			tempestStatusCount = counter;
		}
		if (StringUtil.notEmpty(edgeSite.getvCDNStatus())) {
			updateEdgeSiteStatusSQL += ",vcdn_status = ? ";
			counter++;
			vcdnStatusCount = counter;
		}
		
		updateEdgeSiteStatusSQL += "where edge_site_name = ?";
		
		PreparedStatement pstmt = connection.prepareStatement(updateEdgeSiteStatusSQL);
		
		try {
			if (buildCount != 0) {
				pstmt.setString(buildCount, edgeSite.getEdgeSiteBuildStatus());
			}
			if (tarCount != 0) {
				pstmt.setString(tarCount, edgeSite.getEdgeSiteDeployCreateTarStatus());
			}
			if (genCount != 0) {
				pstmt.setString(genCount, edgeSite.getEdgeSiteDeployGenesisNodeStatus());
			}
			if (toolCount != 0) {
				pstmt.setString(toolCount, edgeSite.getEdgeSiteDeployDeployToolStatus());
			}
			if (buildDateCount != 0) {
				pstmt.setDate(buildDateCount, DateUtil.utilToSql(edgeSite.getBuildDate()));
			}
			if (deployDateCount != 0) {
				pstmt.setDate(deployDateCount,  DateUtil.utilToSql(edgeSite.getDeployDate()));
			}
			if (deployStatusCount != 0) {
				pstmt.setString(deployStatusCount,  edgeSite.getDeployStatus());
			}
			if (onapStatusCount != 0) {
				pstmt.setString(onapStatusCount,  edgeSite.getOnapStatus());
			}
			if (tempestStatusCount != 0) {
				pstmt.setString(tempestStatusCount,  edgeSite.getTempestStatus());
			}
			if (vcdnStatusCount != 0) {
				pstmt.setString(vcdnStatusCount,  edgeSite.getvCDNStatus());
			}
			
			pstmt.setString(counter+1, edgeSite.getEdgeSiteName());
			
			returnValue = pstmt.executeUpdate();
			connection.commit();
		} finally {
             	pstmt.close();
 		}
		
		return returnValue;
		
    }
    
	public void updategeneratedYaml(EdgeSite edgeSite) throws ClassNotFoundException, SQLException {
		//DBConnection dbConnection = DBConnection.getInstance();
		Connection connection = DBConnection.getInstance().getConnection();
		
		PreparedStatement pstmt = connection.prepareStatement(updateEdgeSiteSQL);
		
		try {
			InputStream streamYaml1 = new ByteArrayInputStream(edgeSite.getOutputYaml1().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(1, streamYaml1);
			
			InputStream streamYaml2 = new ByteArrayInputStream(edgeSite.getOutputYaml2().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(2, streamYaml2);
			
			InputStream streamYaml3 = new ByteArrayInputStream(edgeSite.getOutputYaml3().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(3, streamYaml3);
			
			InputStream streamYaml4 = new ByteArrayInputStream(edgeSite.getOutputYaml4().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(4, streamYaml4);
			
			InputStream streamYaml5 = new ByteArrayInputStream(edgeSite.getOutputYaml5().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(5, streamYaml5);
			
			InputStream streamYaml6 = new ByteArrayInputStream(edgeSite.getOutputYaml6().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(6, streamYaml6);
			
			InputStream streamYaml7 = new ByteArrayInputStream(edgeSite.getOutputYaml7().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(7, streamYaml7);
			
			InputStream streamYaml8 = new ByteArrayInputStream(edgeSite.getOutputYaml8().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(8, streamYaml8);
			
			InputStream streamYaml9 = new ByteArrayInputStream(edgeSite.getOutputYaml9().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(9, streamYaml9);
			
			InputStream streamYaml10 = new ByteArrayInputStream(edgeSite.getOutputYaml10().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(10, streamYaml10);
			
			InputStream streamYaml11 = new ByteArrayInputStream(edgeSite.getOutputYaml11().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(11, streamYaml11);
			
			InputStream streamYaml12 = new ByteArrayInputStream(edgeSite.getOutputYaml12().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(12, streamYaml12);
			
			InputStream streamYaml13 = new ByteArrayInputStream(edgeSite.getOutputYaml13().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(13, streamYaml13);
			
			InputStream streamYaml14 = new ByteArrayInputStream(edgeSite.getOutputYaml14().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(14, streamYaml14);
			
			InputStream streamYaml15 = new ByteArrayInputStream(edgeSite.getOutputYaml15().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(15, streamYaml15);
			
			InputStream streamYaml16 = new ByteArrayInputStream(edgeSite.getOutputYaml16().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(16, streamYaml16);
			
			InputStream streamYaml17 = new ByteArrayInputStream(edgeSite.getOutputYaml17().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(17, streamYaml17);
			
			InputStream streamYaml18 = new ByteArrayInputStream(edgeSite.getOutputYaml18().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(18, streamYaml18);
			
			InputStream streamYaml19 = new ByteArrayInputStream(edgeSite.getOutputYaml19().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(19, streamYaml19);
			InputStream streamYaml20 = new ByteArrayInputStream(edgeSite.getOutputYaml20().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(20, streamYaml20);
			InputStream streamYaml21 = new ByteArrayInputStream(edgeSite.getOutputYaml21().getBytes(StandardCharsets.UTF_8));
			pstmt.setBinaryStream(21, streamYaml21);
			pstmt.setString(22, edgeSite.getEdgeSiteName());
			
			pstmt.executeUpdate();
			connection.commit();
			
		} finally {
			pstmt.close();
		}
		
	}
	
}
