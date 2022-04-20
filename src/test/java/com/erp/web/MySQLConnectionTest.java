package com.erp.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MySQLConnectionTest {

	private static final Logger logger = LoggerFactory.getLogger(MySQLConnectionTest.class);
	
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL ="jdbc:mysql://localhost:3306/erp";
	private static final String USER="kt";
	private static final String PW="kt";
	
	@Test
	public void testConnection() throws Exception{
		Class.forName(DRIVER);
		try(Connection con=DriverManager.getConnection(URL, USER, PW)) {
			logger.info("con  : {} ", con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
