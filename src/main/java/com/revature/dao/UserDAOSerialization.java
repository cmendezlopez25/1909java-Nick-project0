package com.revature.dao;

import com.revature.pojo.User;
import static com.revature.util.LoggerUtil.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserDAOSerialization implements UserDAO {
	// singleton
	public static final UserDAOSerialization userSerializer = new UserDAOSerialization();

	private UserDAOSerialization() {
		log.trace("Creating UserDAOSerialization object");
	}

	@Override
	public void CreateUser(User u) {
		if (u == null) {
			log.error("User does not exist!");
			throw new NullPointerException();
		}

		String fileName;
		
		fileName = u.getUsername() + ".user";
		
		try (FileOutputStream fos = new FileOutputStream(fileName);
			 ObjectOutputStream oos = new ObjectOutputStream(fos);)
		{
			oos.writeObject(u);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public User ReadUserFile(String username) {
		if (username == null) {
			log.error("User does not exist!");
			throw new NullPointerException();
		}

		User u = null;
		try (FileInputStream fis = new FileInputStream(username + ".user");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			u = (User) ois.readObject();
		} catch (FileNotFoundException e) {
			log.warn("File not found! Setting user to null.");
			u = null;
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return u;
	}

}
