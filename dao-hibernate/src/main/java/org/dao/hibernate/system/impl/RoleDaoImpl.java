package org.dao.hibernate.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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

	@Override
	public List<Role> queryByIds(String ids) {
		List<Role> resList = new ArrayList<Role>();
		if(StringUtils.isNotEmpty(ids)){
			for(String id : ids.split(",")){
				resList.add(queryById(id));
			}
		}
		return resList;
	}

	@Override
	public List<Role> queryAll() {
		String HQL ="from Role where state='F0A' order by createTime asc";
		return queryList(HQL);
	}

	@Override
	public List<Role> queryByUserId(String userId) {
		String HQL="select ur.role from UserRole ur where ur.state='F0A' and ur.user.userId=:userId";
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userId",userId);
		return queryList(HQL,condition);
	}

}
