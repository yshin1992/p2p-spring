package org.dao.hibernate.system;

import org.dao.hibernate.AbstractDao;
import org.domain.system.User;

import pagination.PageRequest;
import pagination.PageResponse;

public interface UserDao extends AbstractDao<User> {

	public User findByCd(String userCd);
	
	public PageResponse<User> queryByPage(PageRequest request);
	
}
