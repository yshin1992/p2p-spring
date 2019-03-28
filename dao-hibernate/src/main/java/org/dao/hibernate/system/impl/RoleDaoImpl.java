package org.dao.hibernate.system.impl;

import java.util.HashMap;

import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.system.RoleDao;
import org.domain.system.Role;
import org.springframework.stereotype.Repository;

import pagination.PageRequest;
import pagination.PageResponse;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {

	@Override
	public PageResponse<Role> queryRolesByPage(PageRequest pageRequest) {
		StringBuilder HQL = new StringBuilder(" from Role order by listSort asc");
		return this.queryPageByHQL(HQL.toString(), new HashMap<String,Object>(), pageRequest);
	}

}
