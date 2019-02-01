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

package org.akraino.portal.data;

public class SiteDeployRequest extends WorkflowRequest {
	
	private String winscpdir;
	
	private String winscpfilepath;
	
	private String winscpfileparams;
	
	private String remotefilename;
	
	private String filepath;
	
	private String blueprint;
	
	private String file1;
	
	private String file1params;
	
	private String remotefile1;
	
	private String remotefile2;
	
	private String destdir1;
	
	private String destdir2;

	@Override
	public String toString() {
		return "SiteDeployRequest [winscpdir=" + winscpdir + ", winscpfilepath=" + winscpfilepath
				+ ", winscpfileparams=" + winscpfileparams + ", remotefilename=" + remotefilename + ", filepath="
				+ filepath + ", blueprint=" + blueprint + ", file1=" + file1 + ", file1params=" + file1params
				+ ", remotefile1=" + remotefile1 + ", remotefile2=" + remotefile2 + ", destdir1=" + destdir1
				+ ", destdir2=" + destdir2 + "]";
	}

	public String getWinscpdir() {
		return winscpdir;
	}

	public void setWinscpdir(String winscpdir) {
		this.winscpdir = winscpdir;
	}

	public String getWinscpfilepath() {
		return winscpfilepath;
	}

	public void setWinscpfilepath(String winscpfilepath) {
		this.winscpfilepath = winscpfilepath;
	}

	public String getWinscpfileparams() {
		return winscpfileparams;
	}

	public void setWinscpfileparams(String winscpfileparams) {
		this.winscpfileparams = winscpfileparams;
	}

	public String getRemotefilename() {
		return remotefilename;
	}

	public void setRemotefilename(String remotefilename) {
		this.remotefilename = remotefilename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(String blueprint) {
		this.blueprint = blueprint;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile1params() {
		return file1params;
	}

	public void setFile1params(String file1params) {
		this.file1params = file1params;
	}

	public String getRemotefile1() {
		return remotefile1;
	}

	public void setRemotefile1(String remotefile1) {
		this.remotefile1 = remotefile1;
	}

	public String getRemotefile2() {
		return remotefile2;
	}

	public void setRemotefile2(String remotefile2) {
		this.remotefile2 = remotefile2;
	}

	public String getDestdir1() {
		return destdir1;
	}

	public void setDestdir1(String destdir1) {
		this.destdir1 = destdir1;
	}

	public String getDestdir2() {
		return destdir2;
	}

	public void setDestdir2(String destdir2) {
		this.destdir2 = destdir2;
	}
	
}