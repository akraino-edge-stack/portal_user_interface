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
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.akraino.portal.common.FileUtility;
import org.akraino.portal.common.PropertyUtil;
import org.akraino.portal.common.RestInterface;
import org.akraino.portal.common.RestRequestBody;
import org.akraino.portal.common.RestResponseBody;
import org.akraino.portal.common.StringUtil;
import org.akraino.portal.config.AppConfig;
import org.akraino.portal.dao.EdgeSiteDAO;
import org.akraino.portal.dao.RegionDAO;
import org.akraino.portal.data.BuildRequest;
import org.akraino.portal.data.EdgeSiteState;
import org.akraino.portal.data.NPod;
import org.akraino.portal.data.SiteDeployRequest;
import org.akraino.portal.data.SiteRequest;
import org.akraino.portal.data.WorkflowRequest;
import org.akraino.portal.entity.EdgeSite;
import org.akraino.portal.entity.EdgeSiteYamlTemplate;
import org.akraino.portal.entity.Region;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@Service("edgeSiteService")
@Transactional
public class EdgeSiteService {

	@Autowired
	private EdgeSiteDAO edgeSiteDAO;
	
	@Autowired
	private RegionDAO regionDAO;
	
	@Autowired
	private EdgeSiteYamlTemplateService edgeSiteYamlTemplateService;

	private static final Logger logger = Logger.getLogger(EdgeSiteService.class);

	private String akrainoBaseDir = null;

	private static final String BLUEPRINT_ROVER = "Rover";
	
	private static final String ROVER_BUILD_STATUS_NA = "Not Applicable";
	
	private static final String STATUS_NOT_STARTED = "Not Started";

	private static final String BLUEPRINT_UNICYCLE = "Unicycle";
	
	private static final String YAML_FILE_EXT = ".yaml";
	
	private static final String MULTI_NODE_INPUT_FILE_NAME_HPGEN10 = "multi-node-input-file-hpgen10.yaml";

	public EdgeSiteService() {
		
		akrainoBaseDir = PropertyUtil.getInstance().getProperty("akraino.base.dir");
		
	}

	public List<EdgeSite> getSites(int regionId) {
		
		logger.info("getSites");

		return edgeSiteDAO.listAllEdgeSites(regionId);

	}
	
	public EdgeSite getEdgeSiteDetails(String siteName) {
		
		logger.info("getEdgeSiteDetails");

		return edgeSiteDAO.getEdgeSiteDetails(siteName);

	}
	
	public NPod getEdgeSitePodInfo(String sitename, String blueprint) throws IOException {
		
		EdgeSite site = null;
		FileOutputStream fileOuputStream = null;
		NPod npod = null;
		
		try {
			
			getEdgeSitePodInfo(sitename, blueprint);
			
			site = getEdgeSiteDetails(sitename);
			
			//String multinodefile = AppConfig.class.getClassLoader().getResource(MULTI_NODE_INPUT_FILE_NAME_HPGEN10).getPath();

			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			File yamlFile = new File(akrainoBaseDir + "/yaml_builds/" + sitename + "_gen.yaml");
			
			fileOuputStream = new FileOutputStream(yamlFile, false); 
		    fileOuputStream.write(site.getInputFile());
			
		    // read yaml file into java object
		    npod = mapper.readValue(yamlFile, NPod.class);
		
		} finally {
			try {
				fileOuputStream.close();
			} catch (IOException io) {
				logger.error("Failed to read Yaml File Read", io);
			}
		}
		
		return npod;
	}

	public String getBuildYamlContent(String siteName) throws IOException {

		logger.info("getBuildYamlContent");

		StringBuilder output = new StringBuilder();

		List<EdgeSiteYamlTemplate> yamlTemplates = edgeSiteYamlTemplateService.getYamlTemplates();

		List<byte[]> yamlContentList = new ArrayList<byte[]>();

		for (int i = 0; i < yamlTemplates.size(); i++) {
			// read all generated yaml files
			EdgeSiteYamlTemplate yamlTemplate = yamlTemplates.get(i);

			byte[] array = null;

			String filePath = akrainoBaseDir + "/yaml_builds/site/" + siteName + "/" + yamlTemplate.getFileLocation()
					+ "/" + yamlTemplate.getFileName() + YAML_FILE_EXT;
			
			logger.info("fetching generated yaml files from:" + filePath);

			array = Files.readAllBytes(new File(filePath).toPath());

			yamlContentList.add(array);
			output.append(new String(array, "UTF-8"));

		}

		// update database
		EdgeSite edgeSite = edgeSiteDAO.getEdgeSiteDetails(siteName);

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
		edgeSite.setOutputYaml22(yamlContentList.get(21));
		edgeSite.setOutputYaml23(yamlContentList.get(22));
		edgeSite.setOutputYaml24(yamlContentList.get(23));

		edgeSiteDAO.updateEdgeSite(edgeSite);


		return output.toString();

	}

	public boolean saveAndCopyInput(byte[] bfileContent, EdgeSiteState siteRequest) throws Exception {

		logger.info("saveAndCopyInput");
		
		EdgeSite edgeSite = edgeSiteDAO.getEdgeSiteDetails(siteRequest.getSiteName());

		edgeSite.setBlueprint(siteRequest.getBlueprint());
		edgeSite.setEdgeSiteIP(siteRequest.getEdgeSiteIP());
		edgeSite.setEdgeSiteUser(siteRequest.getEdgeSiteUser());
		edgeSite.setEdgeSitePwd(siteRequest.getEdgeSitePwd());
		edgeSite.setInputFile(bfileContent);
		edgeSite.setDeployStatus(STATUS_NOT_STARTED);
		edgeSite.setvCDNStatus(STATUS_NOT_STARTED);
		//edgeSite.setDeployMode(siteRequest.getDeployMode());
		
		// copy input file
		if (siteRequest.getBlueprint().equals(BLUEPRINT_ROVER)) {
			
			edgeSite.setEdgeSiteBuildStatus(ROVER_BUILD_STATUS_NA);
			
			String filepath = akrainoBaseDir + "/server-build/"+siteRequest.getSiteName();
			//String filepath = "C:\\Users\\ld261v\\Desktop\\AEC\\test\\" + siteRequest.getSiteName() + YAML_FILE_EXT;

			logger.info("writing input file to:" + filepath);
			
			FileUtility.writeToFile(filepath, bfileContent);
			

		} else if (siteRequest.getBlueprint().equals(BLUEPRINT_UNICYCLE)) {

			edgeSite.setEdgeSiteBuildStatus(STATUS_NOT_STARTED);
			edgeSite.setEdgeSiteDeployCreateTarStatus(STATUS_NOT_STARTED);
			edgeSite.setEdgeSiteDeployGenesisNodeStatus(STATUS_NOT_STARTED);
			edgeSite.setEdgeSiteDeployDeployToolStatus(STATUS_NOT_STARTED);
			
			String inputfilepath = akrainoBaseDir + "/yaml_builds/" + edgeSite.getEdgeSiteName() + YAML_FILE_EXT;
			//String inputfilepath = "C:\\Users\\ld261v\\Desktop\\AEC\\test\\" + siteRequest.getSiteName() + YAML_FILE_EXT;
			logger.info("writing input file to:" + inputfilepath);

			FileUtility.writeToFile(inputfilepath, bfileContent);
			
			// copy j2 template files
			pushJ2TemplateFiles();

		}
		
		// update edge site
		edgeSiteDAO.updateEdgeSite(edgeSite);

		return true;

	}

	private boolean pushJ2TemplateFiles() throws Exception {

		List<EdgeSiteYamlTemplate> yamlTemplates = edgeSiteYamlTemplateService.getYamlTemplates();

		for (int i = 0; i < yamlTemplates.size(); i++) {

			EdgeSiteYamlTemplate yamlTemplate = yamlTemplates.get(i);

			String filePath = akrainoBaseDir + "/yaml_builds/templates/" + yamlTemplate.getFileLocation()
					+ yamlTemplate.getFileName() + ".j2";
			
			logger.info("pushing all j2 templates files to:"+filePath);

			FileUtility.writeToFile(filePath, yamlTemplate.getFileContent());

		}

		return true;

	}

	public String getHeatContent(String vnfName) throws ClassNotFoundException, SQLException, Exception {

		logger.info("getHeatContent");

		String fileName = "";

		StringBuilder output = new StringBuilder();

		byte[] array = null;

		String filePath = akrainoBaseDir + fileName + YAML_FILE_EXT;
		
		logger.info("looking for heat file:"+filePath);

		try {
			array = Files.readAllBytes(new File(filePath).toPath());
			String heatFileContent = new String(array, "UTF-8");

			output.append(heatFileContent);

		} catch (Exception e) {
			logger.error("heat template file retrieval failed", e);
		}

		logger.info("getHeatContent");

		return output.toString();

	}

	/*// example get rest client
	public String GETRequest() {

		String uri = "http://gturnquist-quoters.cfapps.io/api/random";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Quote> quote = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Quote.class);

		return quote.toString();

	}*/

	public void updateSiteStatus(EdgeSiteState statusReqeust) {
		
		logger.info("updateSiteStatus");

		EdgeSite edgeSite = edgeSiteDAO.getEdgeSiteDetails(statusReqeust.getSiteName());

		if (StringUtil.notEmpty(statusReqeust.getBuildStatus()))
			edgeSite.setEdgeSiteBuildStatus(statusReqeust.getBuildStatus());
		
		if (StringUtil.notEmpty(statusReqeust.getCreateTarStatus()))
			edgeSite.setEdgeSiteDeployCreateTarStatus(statusReqeust.getCreateTarStatus());
		
		if (StringUtil.notEmpty(statusReqeust.getGenesisNodeStatus()))
			edgeSite.setEdgeSiteDeployGenesisNodeStatus(statusReqeust.getGenesisNodeStatus());
		
		if (StringUtil.notEmpty(statusReqeust.getDeployToolsStatus()))
			edgeSite.setEdgeSiteDeployDeployToolStatus(statusReqeust.getDeployToolsStatus());

		if (StringUtil.notEmpty(statusReqeust.getDeployStatus()))
			edgeSite.setDeployStatus(statusReqeust.getDeployStatus());

		if (StringUtil.notEmpty(statusReqeust.getOnapStatus()))
			edgeSite.setOnapStatus(statusReqeust.getOnapStatus());

		if (StringUtil.notEmpty(statusReqeust.getTempestStatus()))
			edgeSite.setTempestStatus(statusReqeust.getTempestStatus());

		if (StringUtil.notEmpty(statusReqeust.getvCDNStatus()))
			edgeSite.setvCDNStatus(statusReqeust.getvCDNStatus());
		
		if (StringUtil.notEmpty(statusReqeust.getTransferFileStatus()))
			edgeSite.setTransferFileStatus(statusReqeust.getTransferFileStatus());
		
		if (StringUtil.notEmpty(statusReqeust.getNodeDeployStatus()))
			edgeSite.setNodeDeployStatus(statusReqeust.getNodeDeployStatus());
		
		if (StringUtil.notEmpty(statusReqeust.getNodetransferFileStatus()))
			edgeSite.setNodetransferFileStatus(statusReqeust.getNodetransferFileStatus());
		
		if (StringUtil.notEmpty(statusReqeust.getNodeYamlStatus()))
			edgeSite.setNodeYamlStatus(statusReqeust.getNodeYamlStatus());

		edgeSiteDAO.updateEdgeSite(edgeSite);
	}
	
	public void createSite(SiteRequest siteRequest) {
		
		logger.info("createEdgeSite");
		
		Region region = regionDAO.getRegion(siteRequest.getRegionId().longValue());
		
		if (region == null) {
			throw new EntityNotFoundException();
		}
		
		EdgeSite site = new EdgeSite();
		site.setEdgeSiteName(siteRequest.getName());
		site.setRegion(region);
		
		edgeSiteDAO.saveOrUpdate(site);
	}

	public EdgeSiteState buildEdgeSite(BuildRequest buildRequest) {

		logger.info("buildEdgeSite");
		
		EdgeSiteState siteState = new EdgeSiteState();
		siteState.setSiteName(buildRequest.getSitename());

		try {

			String buildURI = PropertyUtil.getInstance().getProperty("camunda.site.build.uri");

			RestRequestBody<WorkflowRequest> requestObj = new RestRequestBody<WorkflowRequest>();
			requestObj.setT(buildRequest);

			RestResponseBody<EdgeSiteState> responseObj = new RestResponseBody<EdgeSiteState>();
			responseObj.setT(siteState);

			siteState = RestInterface.sendPOST(buildURI, requestObj, responseObj);

		} catch (Exception e) {

			throw e;

		} finally {
			
			if (!StringUtil.notEmpty(siteState.getBuildStatus())) {
				siteState.setBuildStatus("Error invoking Camunda API");
			}

			updateSiteStatus(siteState);
		}

		return siteState;

	}
	
	public EdgeSiteState onBoardVNF(WorkflowRequest vnfRequest) {
		
		logger.info("onBoardVNF");

		EdgeSiteState siteState = new EdgeSiteState();
		siteState.setSiteName(vnfRequest.getSitename());

		String vnfOnboardURI = PropertyUtil.getInstance().getProperty("camunda.vnf.onboard.uri");

		RestRequestBody<WorkflowRequest> requestObj = new RestRequestBody<>();
		requestObj.setT(vnfRequest);

		RestResponseBody<EdgeSiteState> responseObj = new RestResponseBody<>();
		responseObj.setT(siteState);

		siteState = RestInterface.sendPOST(vnfOnboardURI, requestObj, responseObj);

		
		if (!StringUtil.notEmpty(siteState.getvCDNStatus())) {
			siteState.setvCDNStatus("Error invoking Camunda API");
		}

		updateSiteStatus(siteState);

		return siteState;

	}

	public EdgeSiteState deploySite(SiteDeployRequest siteDeployRequest) {

		logger.info("deploySite");
		
		EdgeSiteState siteState = new EdgeSiteState();
		siteState.setSiteName(siteDeployRequest.getSitename());

		String deployURI = null;
		
		if (siteDeployRequest.getBlueprint().equalsIgnoreCase(BLUEPRINT_ROVER)) {
			
			deployURI = PropertyUtil.getInstance().getProperty("camunda.site.rover.deploy.uri");
			
		} else if(siteDeployRequest.getBlueprint().equalsIgnoreCase(BLUEPRINT_UNICYCLE)) {
			
			EdgeSite edgeSite = edgeSiteDAO.getEdgeSiteDetails(siteDeployRequest.getSitename());
			
			if (edgeSite.getDeployMode().equalsIgnoreCase("new")) {
				
				deployURI = PropertyUtil.getInstance().getProperty("camunda.site.multinode.deploy.uri");
				
			} else if (edgeSite.getDeployMode().equalsIgnoreCase("addNode") || 
					edgeSite.getDeployMode().equalsIgnoreCase("deleteNode")) {
				
				deployURI = PropertyUtil.getInstance().getProperty("camunda.site.multinode.node.adddelete.uri");
				
			}
		}

		RestRequestBody<WorkflowRequest> requestObj = new RestRequestBody<>();
		requestObj.setT(siteDeployRequest);

		RestResponseBody<EdgeSiteState> responseObj = new RestResponseBody<>();
		responseObj.setT(siteState);

		siteState = RestInterface.sendPOST(deployURI, requestObj, responseObj);

		if (!StringUtil.notEmpty(siteState.getDeployStatus())) {				
			siteState.setDeployStatus("Error invoking Camunda API");
		}

		updateSiteStatus(siteState);

		return siteState;

	}

}