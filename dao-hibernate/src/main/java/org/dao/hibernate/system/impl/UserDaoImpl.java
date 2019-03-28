package org.dao.hibernate.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.system.UserDao;
import org.domain.system.User;
import org.springframework.stereotype.Repository;

import pagination.PageRequest;
import pagination.PageResponse;

@Repository("userDao")
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Override
	public User findByCd(String userCd) {
		String HQL="from User where userCd=:userCd";
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userCd", userCd);
		return findSingleResultByHQL(HQL, condition);
	}


	@Override
	public PageResponse<User> queryByPage(PageRequest request) {
		return queryPageByHQL("from User where isAdmin=0 order by listSort desc,createTime desc", new HashMap<String,Object>(), request);
	}


}
