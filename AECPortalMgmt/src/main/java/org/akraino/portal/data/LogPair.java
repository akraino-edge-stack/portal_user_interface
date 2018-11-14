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

import java.sql.Timestamp;

public class LogPair {
	
	private String signature;
	
	private Timestamp tStart;
	
	private Timestamp tStop;

	private ChompSysData sysdata;
	
	private String log;
	
	private ChompMetadata chMetadata;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Timestamp gettStart() {
		return tStart;
	}

	public void settStart(Timestamp tStart) {
		this.tStart = tStart;
	}

	public Timestamp gettStop() {
		return tStop;
	}

	public void settStop(Timestamp tStop) {
		this.tStop = tStop;
	}

	public ChompSysData getSysdata() {
		return sysdata;
	}

	public void setSysdata(ChompSysData sysdata) {
		this.sysdata = sysdata;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public ChompMetadata getChMetadata() {
		return chMetadata;
	}

	public void setChMetadata(ChompMetadata chMetadata) {
		this.chMetadata = chMetadata;
	}

	@Override
	public String toString() {
		return "LogPair [signature=" + signature + ", tStart=" + tStart + ", tStop=" + tStop + ", sysdata=" + sysdata
				+ ", log=" + log + ", chMetadata=" + chMetadata + "]";
	}
	
}
