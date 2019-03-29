package org.background.dao;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.business.system.UserService;
import org.dao.hibernate.system.UserDao;
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
public class UserDaoTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testUserAdd(){
		User user = new User();
		user.setUserCd("admin");
		user.setPassword(new SimpleHash("MD5","admin","admin",2).toString());
		user.setIsAdmin(1);
		user.setEffTime(new Date());
		user.setExpTime(DateUtils.addYears(new Date(), 100));
		user.setUserNm("administrator");
		user.setEnable(true);
		userService.save(user);
	}
	
	@Test
	public void testAdd(){
		User user = new User();
		user.setUserCd("xiaoyun3");
		user.setUserNm("xiaoyun3");
		user.init();
		userService.save(user);
	}
}
