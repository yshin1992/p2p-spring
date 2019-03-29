package org.business.system.impl;

import org.business.AbstractServiceImpl;
import org.business.system.UserService;
import org.dao.hibernate.system.RoleDao;
import org.dao.hibernate.system.UserDao;
import org.dao.hibernate.system.UserRoleDao;
import org.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pagination.PageRequest;
import pagination.PageResponse;

@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public PageResponse<User> queryByPage(PageRequest request) {
		return userDao.queryByPage(request);
	}

	@Override
	public User findByCd(String userCd) {
		return userDao.findByCd(userCd);
	}


}
