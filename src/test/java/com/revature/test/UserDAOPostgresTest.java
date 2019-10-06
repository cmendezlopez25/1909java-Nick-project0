package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.UserDAOPostgres;
import com.revature.pojo.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOPostgresTest {
	private UserDAOPostgres userDAO;
	private User user;
	private String sql;

	@Mock
	private Connection conn;

	@Mock
	private PreparedStatement stmt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userDAO = new UserDAOPostgres();
		user = new User(User.AccessLevel.EMPLOYEE, "person", "Person Individual", "1234");
		userDAO.setSchemaName("test");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createUserPostgres() {
		sql = "insert into user_table(username, name, password, role) values(?, ?, ?, ?)";

		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt);
			userDAO.setConnection(conn);

			userDAO.CreateUser(user);
			Mockito.verify(stmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readUserPostgres() {
		sql = "select * from user_table where user = ?";
		
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt);
			userDAO.setConnection(conn);
			
			userDAO.ReadUserFile(user.getUsername());
			Mockito.verify(stmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
