/**
 * 
 */
package com.pmsi;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Database Manager
 */
public class DatabaseManager {

	private static Properties props = null;
	
	private static Properties getProperties () throws IOException {
		Properties properties = new Properties();
		
		if (Objects.isNull(props)) {
			try (FileInputStream fis = new FileInputStream("conf.properties")) {
				properties.load(fis);
			}
			props = properties;
		}
		
		return props;
	}
	
	public static ResultSet executeUserQuery (String query, Map<Integer, Object> queryParameters) throws ClassNotFoundException, IOException, SQLException{
	
		String url = getProperties().getProperty("jdbc.url");
		String user = getProperties().getProperty("jdbc.username");
		String password = getProperties().getProperty("jdbc.password");
		try (Connection connection = DriverManager.getConnection(url, user, password)) {	
			
			if (queryParameters.isEmpty()) {
				Statement stmt = connection.createStatement();
				return stmt.executeQuery(query);
			}
	        else {
	        	try(PreparedStatement ps = connection.prepareStatement(query)) {
	        		queryParameters.forEach((index, value) -> {
	        			switch (value.getClass().getSimpleName()) {
	        				case "Integer": try {
									ps.setInt(index, (int)value);
								} catch (SQLException e) {
								}
	        					break;
	        				case "String": try {
									ps.setString(index, (String)value);
								} catch (SQLException e) {
								}
	        					break;
	        				default: break;
	        			}
	        		});
	        		return ps.executeQuery();
	        	}
	        }
    		
		}
	}
}
