package org.ysh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ysh.dao.UsersDao;
import org.ysh.domain.Users;
import org.ysh.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public Users findUserByName(String name) {
		// TODO Auto-generated method stub
		return usersDao.findUserByName(name);
	}

	@Override
	public void addUser(Users user) {
		usersDao.addUser(user);
	}

}
