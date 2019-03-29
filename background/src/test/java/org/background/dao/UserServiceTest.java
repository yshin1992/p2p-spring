package org.background.dao;

import org.business.system.UserService;
import org.business.util.CacheUtil;
import org.domain.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages={"org.dao.hibernate","org.business"},excludeFilters={@Filter(type=FilterType.ASSIGNABLE_TYPE,classes=CacheUtil.class)})
@EntityScan(basePackages={"org.domain"})
@EnableTransactionManagement
@SpringBootTest(classes=UserServiceTest.class)
@SpringBootApplication
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testAdd(){
		User user = new User();
		user.setUserCd("xiaoyun5");
		user.setUserNm("xiaoyun5");
		user.init();
		userService.save(user);
	}
}
