package org.dao.hibernate.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.system.PermissionDao;
import org.domain.system.Permission;
import org.springframework.stereotype.Repository;

@Repository("permissionDao")
public class PermissionDaoImpl extends AbstractDaoImpl<Permission> implements PermissionDao{

	@Override
	public List<Permission> findByRoleId(String roleId) {
		StringBuilder HQL = new StringBuilder("from Permission where state='F0A'");
		Map<String,Object> condition = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(roleId)){
			HQL.append(" and roleId=:roleId");
			condition.put("roleId", roleId);
		}
		return queryList(HQL.toString(), condition);
	}

}
