package org.business.system.impl;

import javax.transaction.Transactional;

import org.business.system.UserService;
import org.dao.hibernate.system.UserDao;
import org.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByCd(String userCd) {
		return userDao.findByCd(userCd);
	}

	@Transactional
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

}
