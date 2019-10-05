package com.revature.util;

import static com.revature.util.LoggerUtil.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static final String PROPERTIES_FILE = "src/main/resources/database.properties";
	private static ConnectionFactory cf;

	//singleton
	public static Connection getConnection() {
		if (cf == null) {
			cf = new ConnectionFactory();
		}

		return cf.createConnection();
	}

	private ConnectionFactory() {
		log.trace("Creating ConnectionFactory object");
		Properties prop = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
			prop.load(fis);
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (FileNotFoundException e) {
			log.error("Cannot find" + PROPERTIES_FILE + "!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		log.trace("Entering createConnection");
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			log.error("Could not connect. ");
			e.printStackTrace();
		}

		log.trace("Exiting createConnection");
		return conn;
	}
}
