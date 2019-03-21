package org.business.system;

import org.domain.system.User;

public interface UserService {
	public User findByCd(String userCd);
	
	public void save(User user);
}

