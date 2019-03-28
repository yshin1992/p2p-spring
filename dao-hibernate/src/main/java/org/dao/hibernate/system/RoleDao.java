package org.dao.hibernate.system;

import org.dao.hibernate.AbstractDao;
import org.domain.system.Role;

import pagination.PageRequest;
import pagination.PageResponse;

public interface RoleDao extends AbstractDao<Role>{

	public PageResponse<Role> queryRolesByPage(PageRequest pageRequest);
	
}
