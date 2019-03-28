package org.business.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.business.AbstractServiceImpl;
import org.business.system.RoleService;
import org.dao.hibernate.system.PermissionDao;
import org.dao.hibernate.system.ResourceDao;
import org.dao.hibernate.system.RoleDao;
import org.domain.system.Permission;
import org.domain.system.Resource;
import org.domain.system.Role;
import org.domain.system.RolePermission;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import pagination.PageRequest;
import pagination.PageResponse;

@Service("roleService")
public class RoleServiceImpl extends AbstractServiceImpl<Role> implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
    private PermissionDao permissionDao;
	
	@Autowired
	private ResourceDao resourceDao;
	@Override
	public PageResponse<Role> queryRolesByPage(PageRequest request) {
		return roleDao.queryRolesByPage(request);
	}

	@Transactional
	@Override
	public void save(Role role, String ids) {
		if(null != role.getRoleId()){
			Role roleFind = roleDao.queryById(role.getRoleId());
			BeanUtils.copyProperties(roleFind, role,new String[]{"roleCd","roleNm"});
		}
		
		roleDao.saveOrUpdate(role);//保存或更新角色信息

		//数据库中已有的权限信息
		List<Permission> oldPs = permissionDao.findByRoleId(role.getRoleId());
		
		//这次选择的资源信息
		List<Resource> selected = StringUtils.isEmpty(ids)?new ArrayList<Resource>():resourceDao.findByIds(ids.split(","));
		
		//循环遍历，这次有没有将之前的权限选中，选中则从selected移出，否则此次未选中，则将其置为失效
		if(null != oldPs && oldPs.size() >0){
			for(Permission permission : oldPs){
				Resource resource = permission.getResource();
				if(selected.contains(resource)){
					selected.remove(resource);
				}else{
					permission.disable();
					permissionDao.update(permission);
				}
			}
		}
		
		//将剩下的，也就是这次新添加的权限信息入库
		if(selected.size()>0){
			for(Resource res : selected){
				RolePermission permission = new RolePermission(role, res);
				permission.init();
				permissionDao.save(permission);
			}
		}
	}

	@Transactional
	@Override
	public void enable(String[] ids) {
		if(!ObjectUtils.isEmpty(ids)){
			for(String id : ids){
				Role role = roleDao.queryById(id);
				role.enable();
				roleDao.update(role);
			}
		}
	}

	@Transactional
	@Override
	public void disable(String[] ids) {
		if(!ObjectUtils.isEmpty(ids)){
			for(String id : ids){
				Role role = roleDao.queryById(id);
				role.disable();
				roleDao.update(role);
			}
		}
	}

	@Override
	public List<Role> queryAll() {
		return roleDao.queryAll();
	}

	@Override
	public List<Role> queryByUserId(String userId) {
		return null;
	}

}
