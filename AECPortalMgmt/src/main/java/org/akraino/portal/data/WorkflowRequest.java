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

public class WorkflowRequest {

	private String sitename;
	private String remotserver;
	private String username;
	private String password;
	private Integer port;
	private String srcdir;
	private String destdir;
	private String filename;
	private String filepath;
	private String fileparams;
	private Integer noofiterations;
	private Integer waittime;
	private String filetrasferscript;
	private String filetransferparams;
	
	@Override
	public String toString() {
		return "WorkflowRequest [sitename=" + sitename + ", remotserver=" + remotserver + ", username=" + username
				+ ", password=" + password + ", port=" + port + ", srcdir=" + srcdir + ", destdir=" + destdir
				+ ", filename=" + filename + ", filepath=" + filepath + ", fileparams=" + fileparams
				+ ", noofiterations=" + noofiterations + ", waittime=" + waittime + ", filetrasferscript="
				+ filetrasferscript + ", filetransferparams=" + filetransferparams + "]";
	}
	
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getRemotserver() {
		return remotserver;
	}
	public void setRemotserver(String remotserver) {
		this.remotserver = remotserver;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getSrcdir() {
		return srcdir;
	}
	public void setSrcdir(String srcdir) {
		this.srcdir = srcdir;
	}
	public String getDestdir() {
		return destdir;
	}
	public void setDestdir(String destdir) {
		this.destdir = destdir;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileparams() {
		return fileparams;
	}
	public void setFileparams(String fileparams) {
		this.fileparams = fileparams;
	}

	public Integer getNoofiterations() {
		return noofiterations;
	}
	public void setNoofiterations(Integer noofiterations) {
		this.noofiterations = noofiterations;
	}
	public Integer getWaittime() {
		return waittime;
	}
	public void setWaittime(Integer waittime) {
		this.waittime = waittime;
	}
	public String getFiletrasferscript() {
		return filetrasferscript;
	}
	public void setFiletrasferscript(String filetrasferscript) {
		this.filetrasferscript = filetrasferscript;
	}
	public String getFiletransferparams() {
		return filetransferparams;
	}
	public void setFiletransferparams(String filetransferparams) {
		this.filetransferparams = filetransferparams;
	}
}