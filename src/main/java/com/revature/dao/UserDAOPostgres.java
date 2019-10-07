package com.revature.dao;

import static com.revature.util.LoggerUtil.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pojo.User;
import com.revature.pojo.User.AccessLevel;
import com.revature.util.ConnectionFactory;

public class UserDAOPostgres implements UserDAO {
	private Connection conn = ConnectionFactory.getConnection();
	private String schemaName = "project0";

	@Override
	public void CreateUser(User u) {
		log.trace("Entering UserDAOPostgres's createUser");
		if (u == null) {
			log.error("User is null!");
			throw new NullPointerException();
		}

		String sql = "insert into " + schemaName + ".user_table(username, name, password, role) values(?, ?, ?, ?)";

		try {
			conn.setSchema(schemaName);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getName());
			stmt.setString(3, u.getPassword());
			stmt.setString(4, u.getAccessLevel().name());
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.error("Could not create user!");
			e.printStackTrace();
		}

		log.trace("Exiting UserDAOPostgres's createUser");
	}

	@Override
	public User ReadUserFile(String username) {
		log.trace("Entering UserDAOPostgres's readUserFile");
		if (username == null) {
			return null;
		}

		User user = null;
		
		String sql = "select * from " + schemaName + ".user_table where username = ?";

		try {
			conn.setSchema(schemaName);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				user = new User();
				user.setUsername(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setAccessLevel(AccessLevel.valueOf(rs.getString(4)));
			}
		} catch (SQLException e) {
			log.error("Could not read user!");
			e.printStackTrace();
			user = null;
		}

		log.trace("Exiting UserDAOPostgres's readUserFile");
		return user;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
