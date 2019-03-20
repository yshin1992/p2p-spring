package org.ysh.service;

import org.ysh.domain.Users;

public interface UsersService {

	public Users findUserByName(String name);
	
	public void addUser(Users user);
	
}
