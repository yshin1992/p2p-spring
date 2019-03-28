package org.dao.hibernate.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.system.UserRoleDao;
import org.domain.system.UserRole;
import org.springframework.stereotype.Repository;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDaoImpl<UserRole> implements UserRoleDao {

	@Override
	public List<UserRole> findByUserId(String userId) {
		StringBuffer sb = new StringBuffer(" from UserRole ur ");
        sb.append(" where 1=1 and state=:state  and userId=:userId");
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("state", "F0A");
        condition.put("userId", userId);
		return queryList(sb.toString(), condition);
	}

}
