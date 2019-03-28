package org.business.system;

import org.business.AbstractService;
import org.domain.system.User;

import pagination.PageRequest;
import pagination.PageResponse;

public interface UserService extends AbstractService<User> {
	
	public User findByCd(String userCd);
	
	public PageResponse<User> queryByPage(PageRequest request);
	
	public void save(User user, String ids);
}

