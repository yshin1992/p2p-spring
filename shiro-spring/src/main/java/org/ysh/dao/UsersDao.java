package org.ysh.dao;

import org.ysh.domain.Users;

public interface UsersDao {

	public Users findUserByName(String name);
	
	public void addUser(Users user);
	
}
