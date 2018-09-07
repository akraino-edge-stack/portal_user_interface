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
	private String remoteserver;
	private String username;
	private String password;
	private int port;
	private String srcdir;
	private String destdir;
	private String filename;
	private String fileparams;
	private int noofiterations;
	private int waittime;
	private String filetrasferscript;
	private String filetransferparams;
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getRemoteserver() {
		return remoteserver;
	}
	public void setRemoteserver(String remoteserver) {
		this.remoteserver = remoteserver;
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
	public int getPortnumber() {
		return port;
	}
	public void setPortnumber(int portnumber) {
		this.port = portnumber;
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
	public int getNoofiterations() {
		return noofiterations;
	}
	public void setNoofiterations(int noofiterations) {
		this.noofiterations = noofiterations;
	}
	public int getWaittime() {
		return waittime;
	}
	public void setWaittime(int waittime) {
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