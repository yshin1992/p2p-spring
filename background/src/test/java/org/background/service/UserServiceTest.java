package org.background.service;

import org.business.system.RoleService;
import org.business.system.SysUserService;
import org.business.system.UserService;
import org.domain.system.Role;
import org.domain.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages={"org.dao.hibernate","org.business"})
@EntityScan(basePackages={"org.domain"})
@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Test
	public void testAdd(){
		User user = new User();
		user.setUserCd("xiaoyun7");
		user.setUserNm("xiaoyun7");
		user.init();
		userService.saveOrUpdate(user);
	}
	
	@Test
	public void testAdd2(){
		User user = new User();
		user.setUserCd("xiaoyun2");
		user.setUserNm("xiaoyun2");
		user.init();
		sysUserService.save(user);
	}
	
	@Test
	public void testRoleAdd(){
		Role role = new Role();
		role.setRoleCd("5");
		role.setRoleNm("测试");
		role.init();
		roleService.save(role);
	}
	
}
