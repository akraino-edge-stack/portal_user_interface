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

import java.util.List;

import org.akraino.portal.entity.Pod;

public interface PodDAO {
	
	public void saveOrUpdate(Pod pod);
	
	public void merge(Pod pod);
	
	public List<Pod> getPods();
	
	public Pod getPod(Integer podId);
	
	public void deletePod(Pod pod);
	
	public Pod getPodBySiteId(int siteId);
	
}
