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

package org.akraino.portal.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection {
	
	private static DBConnection  dbConnection;
	private static Object lock = new Object();
	private Connection connection;
	private String jdbcUrl = "";
	private String userId = "";
	private String dbPassword = "";
	
	private static final Logger logger = Logger.getLogger(DBConnection.class);
	
	public static DBConnection getInstance() throws  SQLException, ClassNotFoundException 
	{
		synchronized(lock)
		{
			if (null != dbConnection)
			{
				return dbConnection;
			}
			else
			{
				dbConnection = new DBConnection();
				return dbConnection;
			}
		}
	}
	
	private DBConnection() throws ClassNotFoundException, SQLException
	{
		
		jdbcUrl = PropertyUtil.getInstance().getProperty("postgres.db.url");
		userId = PropertyUtil.getInstance().getProperty("postgres.db.user.name");
		dbPassword = PropertyUtil.getInstance().getProperty("postgres.db.user.pwd");
		
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection(jdbcUrl, userId, dbPassword);
		connection.setAutoCommit(false);
		
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
}
