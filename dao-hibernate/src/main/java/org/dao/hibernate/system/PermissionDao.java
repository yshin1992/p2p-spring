package org.dao.hibernate.system;

import java.util.List;

import org.dao.hibernate.AbstractDao;
import org.domain.system.Permission;

public interface PermissionDao extends AbstractDao<Permission> {
	
	public List<Permission> findByRoleId(String roleId);
	
}
