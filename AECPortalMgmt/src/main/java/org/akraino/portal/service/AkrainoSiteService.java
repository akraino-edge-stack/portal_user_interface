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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.akraino.portal.common.DateUtil;
import org.akraino.portal.common.LoginUtil;
import org.akraino.portal.dao.GetEdgeSitesDAO;
import org.akraino.portal.dao.GetRegionsDAO;
import org.akraino.portal.dao.GetSiteInputYamlFilesDAO;
import org.akraino.portal.data.EdgeSite;
import org.akraino.portal.data.Onap;
import org.akraino.portal.data.Region;
import org.akraino.portal.data.SiteFileInfo;
import org.akraino.portal.data.SiteStatusRequest;
import org.akraino.portal.data.UserSession;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("akrainoService")
public class AkrainoSiteService {

	private static final Logger logger = Logger.getLogger(AkrainoSiteService.class);

	private static final String akrainoBaseDir = "/opt/akraino/yaml_builds";
	//private static final String akrainoBaseDir = "C:\\Users\\ld261v\\Desktop\\AEC\\test";

	private static final String BLUEPRINT_ROVER = "Rover";

	private static final String BLUEPRINT_UNICYCLE = "Unicycle";

	public AkrainoSiteService() {

	}

	public List<Region> getRegions() throws ClassNotFoundException, SQLException {
		GetRegionsDAO getSitesDAO = new GetRegionsDAO();

		return getSitesDAO.getRegions();
	}

	public List<EdgeSite> getSites(int regionId) throws ClassNotFoundException, SQLException {
		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();
		return getEdgeSitesDAO.getEdgeSites(regionId, null);
	}

	public List<Onap> getOnapForSite(String siteName) throws ClassNotFoundException, SQLException {
		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();
		return getEdgeSitesDAO.getOnap(siteName);
	}

	public String getYamlContent(String siteName) throws ClassNotFoundException, SQLException, Exception {

		logger.info(AkrainoSiteService.class.getName() + "-" + "getYamlContent-begin");

		GetSiteInputYamlFilesDAO getSiteInputYamlFilesDAO = new GetSiteInputYamlFilesDAO();
		List<SiteFileInfo> siteInfoList = getSiteInputYamlFilesDAO.getSiteFileInfo();

		// String baseDir = "C:/akraino-j2templates";
		// String baseDir = "/opt/aec_poc/aic-clcp-manifests/site/site80"; // container
		// file path
		// mounted host file path (use this one)
		// FileWriter fw = null;
		// BufferedWriter bw = null;
		StringBuffer output = new StringBuffer();
		EdgeSite edgeSite = new EdgeSite();

		List<String> yamlContentList = new ArrayList<String>();

		for (int i = 0; i < siteInfoList.size(); i++) {

			SiteFileInfo siteInfo = siteInfoList.get(i);

			byte[] array = null;

			String filePath = akrainoBaseDir + "/site/" + siteName + siteInfo.getFileLocation() + siteInfo.getFileName()
					+ ".yaml";

			try {
				array = Files.readAllBytes(new File(filePath).toPath());
				String yamlFileContent = null;

				yamlFileContent = new String(array, "UTF-8");

				yamlContentList.add(yamlFileContent);
				output.append(yamlFileContent);

			} catch (Exception e) {
				logger.error("yaml file not found=" + e);

			}

		}

		edgeSite.setEdgeSiteName(siteName);
		edgeSite.setOutputYaml1(yamlContentList.get(0));
		edgeSite.setOutputYaml2(yamlContentList.get(1));
		edgeSite.setOutputYaml3(yamlContentList.get(2));
		edgeSite.setOutputYaml4(yamlContentList.get(3));
		edgeSite.setOutputYaml5(yamlContentList.get(4));
		edgeSite.setOutputYaml6(yamlContentList.get(5));
		edgeSite.setOutputYaml7(yamlContentList.get(6));
		edgeSite.setOutputYaml8(yamlContentList.get(7));
		edgeSite.setOutputYaml9(yamlContentList.get(8));
		edgeSite.setOutputYaml10(yamlContentList.get(9));
		edgeSite.setOutputYaml11(yamlContentList.get(10));
		edgeSite.setOutputYaml12(yamlContentList.get(11));
		edgeSite.setOutputYaml13(yamlContentList.get(12));
		edgeSite.setOutputYaml14(yamlContentList.get(13));
		edgeSite.setOutputYaml15(yamlContentList.get(14));
		edgeSite.setOutputYaml16(yamlContentList.get(15));
		edgeSite.setOutputYaml17(yamlContentList.get(16));
		edgeSite.setOutputYaml18(yamlContentList.get(17));
		edgeSite.setOutputYaml19(yamlContentList.get(18));
		edgeSite.setOutputYaml20(yamlContentList.get(19));
		edgeSite.setOutputYaml21(yamlContentList.get(20));

		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();
		getEdgeSitesDAO.updategeneratedYaml(edgeSite);

		logger.info(AkrainoSiteService.class.getName() + "-" + "getYamlContent-end");

		return output.toString();

	}

	public boolean saveAndCopyInput(byte[] fileContent, SiteStatusRequest siteRequest) throws ClassNotFoundException, SQLException, Exception {

		// read input file from db
		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();
		List<EdgeSite> edgeSites = getEdgeSitesDAO.getEdgeSites(0, siteRequest.getSiteName());

		if (edgeSites != null && !edgeSites.isEmpty()) {
			EdgeSite edgeSite = edgeSites.get(0);

			if (siteRequest.getBlueprint().equals(BLUEPRINT_ROVER)) {

				//update DB with inputfile, edge site details
				getEdgeSitesDAO.updateEdgeSiteDetails(fileContent.toString(), siteRequest.getEdgeSiteIP(), 
						siteRequest.getEdgeSiteUser(), siteRequest.getEdgeSitePwd(), siteRequest.getBlueprint(), siteRequest.getSiteName());
				
				// copy input file
				String filepath = "/opt/akraino/server-build/"+siteRequest.getSiteName();
				
				Path path = Paths.get(filepath);
	            Files.createDirectories(path.getParent());

				FileOutputStream out = new FileOutputStream(filepath);
				out.write(fileContent);
				out.close();

			} else if (siteRequest.getBlueprint().equals(BLUEPRINT_UNICYCLE)) {

				// copy input file
				String inputfilepath = akrainoBaseDir + "/"+ edgeSite.getEdgeSiteName() + ".yaml";

				Path path = Paths.get(inputfilepath);
	            Files.createDirectories(path.getParent());
				
				FileOutputStream out = new FileOutputStream(inputfilepath);
				out.write(edgeSite.getInputFile().getBytes());
				out.close();

				// copy j2 template files
				pushJ2TemplateFiles();

			}

			return true;
		}

		return false;

	}

	private boolean pushJ2TemplateFiles() throws Exception {

		GetSiteInputYamlFilesDAO getSiteInputYamlFilesDAO = new GetSiteInputYamlFilesDAO();
		List<SiteFileInfo> siteInfoList = getSiteInputYamlFilesDAO.getSiteFileInfo();

		for (int i = 0; i < siteInfoList.size(); i++) {

			SiteFileInfo siteInfo = siteInfoList.get(i);

			String filePath = akrainoBaseDir + "/templates" + siteInfo.getFileLocation() + siteInfo.getFileName() + ".j2";
			
			Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());

			FileOutputStream out = new FileOutputStream(filePath);
			out.write(siteInfo.getFileContent().getBytes());
			out.close();

		}

		return true;

	}

	public String getHeatContent(String vnfName) throws ClassNotFoundException, SQLException, Exception {

		logger.info(AkrainoSiteService.class.getName() + "-" + "getHeatContent-begin");

		// String baseDir = "C:/akraino-j2templates";
		// String baseDir = "/opt/aec_poc/aic-clcp-manifests/site/site80"; // container
		// file path
		// String baseDir = "/usr/local/site80"; // mounted host file path (use this
		// one)
		String fileName = "";
		String fileExtension = ".yaml";
		// FileWriter fw = null;
		// BufferedWriter bw = null;
		StringBuffer output = new StringBuffer();

		byte[] array = null;

		String filePath = akrainoBaseDir + fileName + fileExtension;

		try {
			array = Files.readAllBytes(new File(filePath).toPath());
			String heatFileContent = null;

			heatFileContent = new String(array, "UTF-8");

			output.append(heatFileContent);

		} catch (Exception e) {
			logger.error("heat template file not found=" + e);
		}

		logger.info(AkrainoSiteService.class.getName() + "-" + "getHeatContent-end");

		return output.toString();

	}

	public boolean createUserSession(String userid, String tokenId) throws ClassNotFoundException, SQLException {

		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();

		return getEdgeSitesDAO.createUserSession(userid, tokenId) == 0 ? false : true;

	}

	public boolean updateUserSession(String userid, String tokenId) throws ClassNotFoundException, SQLException {

		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();

		return getEdgeSitesDAO.updateUserSession(userid, tokenId) == 0 ? false : true;

	}

	public UserSession getUserSession(String userId) throws ClassNotFoundException, SQLException {

		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();

		return getEdgeSitesDAO.getUserSession(userId);

	}

	public int deleteUserSession(String authToken) throws ClassNotFoundException, SQLException {

		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();

		String userId = LoginUtil.decode(LoginUtil.getUserName(authToken));

		return getEdgeSitesDAO.deleteUserSession(userId);

	}

	public int updateSiteOnap(Onap onap) throws ClassNotFoundException, SQLException {

		GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();

		return getEdgeSitesDAO.updateOnap(onap);
	}

	public int updateSiteStatus(SiteStatusRequest statusReqeust) throws ClassNotFoundException, SQLException {

		EdgeSite edgeSite = new EdgeSite();
		try {
			edgeSite.setEdgeSiteName(statusReqeust.getSiteName());
			edgeSite.setEdgeSiteBuildStatus(statusReqeust.getBuildStatus());
			edgeSite.setEdgeSiteDeployCreateTarStatus(statusReqeust.getCreateTarStatus());
			edgeSite.setEdgeSiteDeployGenesisNodeStatus(statusReqeust.getGenesisNodeStatus());
			edgeSite.setEdgeSiteDeployDeployToolStatus(statusReqeust.getDeployToolsStatus());
			edgeSite.setBuildDate(DateUtil.strToDate(statusReqeust.getBuildDate()));
			edgeSite.setBuildDate(DateUtil.strToDate(statusReqeust.getDeployDate()));
			edgeSite.setDeployStatus(statusReqeust.getDeployStatus());
			edgeSite.setOnapStatus(statusReqeust.getOnapStatus());
			edgeSite.setTempestStatus(statusReqeust.getTempestStatus());
			edgeSite.setvCDNStatus(statusReqeust.getvCDNStatus());

			GetEdgeSitesDAO getEdgeSitesDAO = new GetEdgeSitesDAO();
			return getEdgeSitesDAO.updateEdgeSiteStatus(edgeSite);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return 0;

	}
}
