package org.business.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.business.system.PermissionService;
import org.dao.hibernate.system.ApplicationDao;
import org.dao.hibernate.system.MenuDao;
import org.dao.hibernate.system.ResourceDao;
import org.dao.hibernate.system.UserDao;
import org.domain.system.Application;
import org.domain.system.Menu;
import org.domain.system.Resource;
import org.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public List<Menu> findMenuByUser(String appCd, String userCd,
			String resourcePcd, boolean isAdmin) {
		return isAdmin?menuDao.queryAll(appCd, resourcePcd) : menuDao.findByUser(userCd, appCd, resourcePcd);
	}

	@Override
	public List<Application> findAppByUser(String userCd, boolean isAdmin) {
		return isAdmin?applicationDao.findAll():applicationDao.findByUser(userCd);
	}

	@Override
	public List<String> queryPermissionsByUser(String userCd) {
		List<String> resourceCds = new ArrayList<String>();
		User user = userDao.findByCd(userCd);
		List<Resource> all = user.getIsAdmin()==1 ? resourceDao.findAll() : resourceDao.findByUser(userCd);
		if(null != all && all.size()>0){
			for(Resource m:all){
				resourceCds.add(m.getResourceCd());
			}
		}
		return resourceCds;
	}

	@Override
	public List<Resource> queryResourcesByUser(String userCd) {
		User user = userDao.findByCd(userCd);
		List<Resource> all = user.getIsAdmin()==1 ? resourceDao.findAll() : resourceDao.findByUser(userCd);
		return all;
	}

}
