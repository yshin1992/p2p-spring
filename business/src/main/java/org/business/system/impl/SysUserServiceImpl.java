package org.business.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.business.AbstractServiceImpl;
import org.business.system.SysUserService;
import org.business.util.SysFuncCache;
import org.dao.hibernate.system.RoleDao;
import org.dao.hibernate.system.UserDao;
import org.dao.hibernate.system.UserRoleDao;
import org.domain.system.Role;
import org.domain.system.User;
import org.domain.system.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service("sysUserService")
public class SysUserServiceImpl extends AbstractServiceImpl<User> implements SysUserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private SysFuncCache sysFuncCache;
	
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
		user.setPassword(new SimpleHash("MD5", "123", user.getUserCd(), 2).toString());//初始化密码为123
		userDao.saveOrUpdate(user);

		// 2.查询原有的角色信息
		List<UserRole> permissions = userRoleDao.findByUserId(user.getId());
		// 3.本次分配的角色
		List<Role> roles = StringUtils.isNotEmpty(ids) ? roleDao
				.queryByIds(ids) : new ArrayList<Role>();
		boolean update = false;
		for (UserRole ur : permissions) {
			Role role = ur.getRole();
			if (roles.contains(role)) {// 原来有的角色，本次也选择了的。那么不用再加入
				roles.remove(role);
				update = true;
			} else {
				ur.disable(); // 又来有的角色，但本次没有选择的。那么需要失效
				userRoleDao.update(ur);
				update = true;
			}
		}
		// 4.保存本次增加的角色
		for (Role role : roles) {
			UserRole userRole = new UserRole(user, role);
			userRole.init();
			userRoleDao.saveOrUpdate(userRole);
		}
		if(!roles.isEmpty()){
			update = true;
		}
		//这里添加update标记，是为了标记是否需要更新已经登录之后的用户的权限缓存
		if(update){
			sysFuncCache.refresh(user.getUserCd());
		}
		
	}

	@Transactional
	@Override
	public void disable(String[] ids) {
		if(!ObjectUtils.isEmpty(ids)){
			for(String id : ids){
				User user = userDao.queryById(id);
				user.disable();
				userDao.update(user);
			}
		}
	}

	@Transactional
	@Override
	public void enable(String[] ids) {
		if(!ObjectUtils.isEmpty(ids)){
			for(String id : ids){
				User user = userDao.queryById(id);
				user.enable();
				userDao.update(user);
			}
		}
	}
}
