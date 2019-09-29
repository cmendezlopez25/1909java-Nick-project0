package com.revature.dao;

import com.revature.pojo.User;
import static com.revature.util.LoggerUtil.log;

public class UserDAOSerialization implements UserDAO {
	//singleton
	public static final UserDAOSerialization userSerializer = new UserDAOSerialization();
	
	private UserDAOSerialization(){
		log.trace("Creating UserDAOSerialization object");
	}

	@Override
	public void CreateUser(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public User ReadUserFile(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
