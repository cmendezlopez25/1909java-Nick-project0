package com.revature.dao;

import com.revature.pojo.User;

public interface UserDAO {
	public void CreateUser(User u);
	public User ReadUserFile(String username);
}
