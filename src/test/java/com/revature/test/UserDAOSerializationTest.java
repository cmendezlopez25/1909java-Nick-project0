package com.revature.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.UserDAOSerialization;
import com.revature.pojo.User;
import com.revature.pojo.User.AccessLevel;

public class UserDAOSerializationTest {
	private UserDAOSerialization userSerializer;
	private User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userSerializer = UserDAOSerialization.userSerializer;
		user = new User(AccessLevel.EMPLOYEE, "burrito", "Burrito Man", "1234");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void userAddNull() {
		userSerializer.CreateUser(null);
	}

	@Test
	public void createFile() {
		userSerializer.CreateUser(user);

		User user2 = null;

		// try with resources
		try (FileInputStream fis = new FileInputStream("burrito.user");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			user2 = (User) ois.readObject();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		assertEquals("File should have been created.", user.getUsername(), user2.getUsername());
	}

	@Test
	public void readExistingFile() {
		assertEquals("Should return user with same username.", user.getUsername(),
				userSerializer.ReadUserFile("burrito").getUsername());
	}
	
	@Test(expected = NullPointerException.class)
	public void readNull() {
		userSerializer.ReadUserFile(null);
	}

	@Test
	public void readNonExistingFile() {
		assertEquals("Should return null.", null, userSerializer.ReadUserFile("notFound"));
	}
}
