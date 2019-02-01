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
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.akraino.portal.common.PropertyUtil;
import org.akraino.portal.common.StringUtil;
import org.akraino.portal.dao.EdgeSiteYamlTemplateDAO;
import org.akraino.portal.entity.EdgeSiteYamlTemplate;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("edgeSiteYamlTemplateService")
@Transactional
public class EdgeSiteYamlTemplateService {

	private static final Logger logger = Logger.getLogger(EdgeSiteYamlTemplateService.class);

	private static final String J2_TEMPLATE_DIR = "akraino-j2templates";

	private String akrainoBaseDir = null;

	@Autowired
	private EdgeSiteYamlTemplateDAO edgeSiteYamlTemplateDAO;

	public EdgeSiteYamlTemplateService() {

		akrainoBaseDir = PropertyUtil.getInstance().getProperty("akraino.base.dir");

	}

	public List<EdgeSiteYamlTemplate> getYamlTemplates() {

		logger.info("getYamlTemplates");

		return edgeSiteYamlTemplateDAO.getYamlTemplates();

	}

	public void loadJ2TemplateFiles() throws IOException {

		cleanJ2FilesFromDB();

		logger.info("loadJ2TemplateFiles");

		String j2_file_path = akrainoBaseDir + "/akraino-j2templates";

		logger.info("loading j2 template files from:" + j2_file_path);

		copyFilesToDB(j2_file_path);

	}

	private void copyFilesToDB(String directoryPath) throws IOException {

		File directory = new File(directoryPath);

		// Get all j2 files
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile() && FilenameUtils.getExtension(file.toString()).equals("j2")) {

					byte[] fileContent = Files.readAllBytes(file.toPath());

					String absolutePath = file.getParent();
					String relativePath = absolutePath
							.substring(absolutePath.indexOf(J2_TEMPLATE_DIR) + J2_TEMPLATE_DIR.length());

					String fullFileName = file.getName();
					String fileName = fullFileName.substring(0, fullFileName.lastIndexOf('.'));

					EdgeSiteYamlTemplate template = new EdgeSiteYamlTemplate();

					template.setFileName(fileName);
					template.setFileLocation(StringUtil.notEmpty(relativePath) ? relativePath : "/");
					template.setFileContent(fileContent);

					edgeSiteYamlTemplateDAO.save(template);

				} else if (file.isDirectory()) {

					copyFilesToDB(file.getAbsolutePath());

				}
			}
		}
	}

	private void cleanJ2FilesFromDB() {

		logger.info("cleanJ2FilesFromDB");

		edgeSiteYamlTemplateDAO.deleteAll();

	}

}