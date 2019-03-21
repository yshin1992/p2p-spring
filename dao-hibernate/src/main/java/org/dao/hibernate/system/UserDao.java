package org.dao.hibernate.system;

import org.domain.system.User;

public interface UserDao {

	public User findByCd(String userCd);
	
	public void save(User user);
}
