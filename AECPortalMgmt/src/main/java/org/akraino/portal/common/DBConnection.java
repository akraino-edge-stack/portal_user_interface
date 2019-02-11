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

public class DBConnection {

	private static DBConnection  dbConnection;
	private static Object lock = new Object();

	private Connection connection;

	/**
	 * Get the singleton instance of this class.
	 * @return a DBConnection object to access the DB.
	 * @throws SQLException if an error occurs at the SQL layer
	 * @throws ClassNotFoundException if the DB driver is not loaded
	 */
	public static DBConnection getInstance() throws  SQLException, ClassNotFoundException
	{
		synchronized(lock)
		{
			if (null == dbConnection)
			{
				dbConnection = new DBConnection();
			}
			return dbConnection;
		}
	}

	private DBConnection() throws ClassNotFoundException, SQLException
	{
		PropertyUtil p    = PropertyUtil.getInstance();
		String jdbcUrl    = p.getProperty("postgres.db.url");
		String userId     = p.getProperty("postgres.db.user.name");
		String dbPassword = p.getProperty("postgres.db.user.pwd");

		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection(jdbcUrl, userId, dbPassword);
		connection.setAutoCommit(false);
	}

	/**
	 * Get the JDBC Connection object from this class.
	 * @return the Connection (may be null)
	 */
	public Connection getConnection()
	{
		return connection;
	}
}
