package org.dao.hibernate.system;

import java.util.List;

import org.dao.hibernate.AbstractDao;
import org.domain.system.UserRole;

public interface UserRoleDao extends AbstractDao<UserRole> {

	List<UserRole> findByUserId(String userId);
	
}
