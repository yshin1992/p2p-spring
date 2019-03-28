package org.background.service;

import org.business.system.RoleService;
import org.business.system.UserService;
import org.domain.system.Role;
import org.domain.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages={"org.dao.hibernate","org.business"})
@EntityScan(basePackages={"org.domain"})
@EnableTransactionManagement
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void testAdd(){
		User user = new User();
		user.setUserCd("chenpeng");
		user.setUserNm("陈鹏");
		user.init();
		userService.save(user);
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
