package org.business.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.business.AbstractServiceImpl;
import org.business.system.UserService;
import org.dao.hibernate.system.RoleDao;
import org.dao.hibernate.system.UserDao;
import org.dao.hibernate.system.UserRoleDao;
import org.domain.system.Role;
import org.domain.system.User;
import org.domain.system.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pagination.PageRequest;
import pagination.PageResponse;

@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

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

	@Transactional
	@Override
	public void save(User user, String ids) {
		if (StringUtils.isNotEmpty(user.getUserId())) {
			User u=userDao.queryById(user.getId());
			if(u==null) u=new User();
			u.setUserCd(user.getUserCd());
			u.setUserNm(user.getUserNm());
			u.setListSort(user.getListSort());
			user=u;
		}
		user.init();
		userDao.saveOrUpdate(user);

		// 2.查询原有的角色信息
		List<UserRole> permissions = userRoleDao.findByUserId(user.getId());
		// 3.本次分配的角色
		List<Role> roles = StringUtils.isNotEmpty(ids) ? roleDao
				.queryByIds(ids) : new ArrayList<Role>();
		for (UserRole ur : permissions) {
			Role role = ur.getRole();
			if (roles.contains(role)) {// 原来有的角色，本次也选择了的。那么不用再加入
				roles.remove(role);
			} else {
				ur.disable(); // 又来有的角色，但本次没有选择的。那么需要失效
				userRoleDao.update(ur);
			}
		}
		// 4.保存本次增加的角色
		for (Role role : roles) {
			UserRole userRole = new UserRole(user, role);
			userRole.init();
			userRoleDao.saveOrUpdate(userRole);
		}
	}

	@Override
	public User findByCd(String userCd) {
		return userDao.findByCd(userCd);
	}

}
