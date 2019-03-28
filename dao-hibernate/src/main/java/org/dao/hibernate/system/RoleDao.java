package org.dao.hibernate.system;

import java.util.List;

import org.dao.hibernate.AbstractDao;
import org.domain.system.Role;

import pagination.PageRequest;
import pagination.PageResponse;

public interface RoleDao extends AbstractDao<Role>{

	public PageResponse<Role> queryRolesByPage(PageRequest pageRequest);
	
	public List<Role> queryByIds(String ids);
	
	public List<Role> queryAll();
	
	public List<Role> queryByUserId(String userId);
} 
