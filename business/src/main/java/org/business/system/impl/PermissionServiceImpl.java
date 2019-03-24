package org.business.system.impl;

import java.util.List;

import org.business.system.PermissionService;
import org.dao.hibernate.system.ApplicationDao;
import org.dao.hibernate.system.MenuDao;
import org.domain.system.Application;
import org.domain.system.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Override
	public List<Menu> findMenuByUser(String appCd, String userCd,
			String resourcePcd, boolean isAdmin) {
		
		//TODO根据用户角色去查，这里先查出所有的
		return menuDao.queryAll(appCd, resourcePcd);
	}

	@Override
	public List<Application> findAppByUser(String userCd, boolean isAdmin) {
		//TODO根据用户角色去查，这里先查出所有的
		return applicationDao.findAll();
	}

}
