package org.business.system;

import java.util.List;

import org.business.AbstractService;
import org.domain.system.Role;

import pagination.PageRequest;
import pagination.PageResponse;

public interface RoleService extends AbstractService<Role> {

	public PageResponse<Role> queryRolesByPage(PageRequest request);
	
	/**
	 * 保存角色及其权限
	 * @param role
	 * @param ids
	 */
	public void save(Role role, String ids);
	
	/**
	 * 启用角色
	 * @param ids
	 */
	public void enable(String[] ids);
	
	/**
	 * 禁用角色
	 * @param ids
	 */
	public void disable(String[] ids);
	
	public List<Role> queryAll();
	
	public List<Role> queryByUserId(String userId);
}
