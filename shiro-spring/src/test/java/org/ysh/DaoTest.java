package org.ysh;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ysh.dao.UsersDao;
import org.ysh.domain.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class DaoTest {

	@Autowired
	private UsersDao usersDao;
	
	@Test
	public void testUsersDao(){
		Users findUserByName = usersDao.findUserByName("admin");
		Assert.assertNotNull(findUserByName);
	}
	
}
