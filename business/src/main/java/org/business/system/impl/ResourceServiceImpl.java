package org.business.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.business.system.ResourceService;
import org.dao.hibernate.system.ApplicationDao;
import org.dao.hibernate.system.PermissionDao;
import org.dao.hibernate.system.ResourceDao;
import org.domain.system.Application;
import org.domain.system.Permission;
import org.domain.system.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Transactional
	@Override
	public void save(List<Resource> resources, Application app) {
		app = applicationDao.findByAppCd(app.getAppCd());
		LinkedBlockingQueue<Resource> queue = new LinkedBlockingQueue<>(resources);
		while(queue.size() >0){
			Resource res = queue.poll();
			res.init();
			res.setApp(app);
			//如果是顶级菜单
			if(StringUtils.isEmpty(res.getParentCd())){
				res.setResourceLevel(1);
				resourceDao.saveOrUpdate(res);
				logger.error("保存菜单 : {},{}",res.getResourceCd(),res.getResourceNm());
			}
			else{
				//如果不是顶级菜单，则查询出父级菜单
				Resource parent = resourceDao.findByCd(res.getParentCd());
				if(parent!=null){ //父级菜单已经保存到数据库的情况下，直接存储即可
					res.setParent(parent);
					res.setResourceLevel(parent.getResourceLevel() +1);
					resourceDao.saveOrUpdate(res);
					logger.error("保存菜单 : {},{}",res.getResourceCd(),res.getResourceNm());
				}else{
					//如果父级菜单在数据库中未查到，则需要在我们的资源列表中查找是否有其父级菜单
					//如果有，先将此菜单重新添加到队列中，知道找到其父级菜单并入库后，再进行存库
					//如果没有找到，就说明这个菜单有问题，抛出异常，停止注册菜单资源的操作
					if(hasParent(resources,res)){
						queue.add(res);
					}else{
						logger.error("注册菜单异常 {}/{}未找到父级菜单",res.getResourceCd(),res.getResourceNm());
						throw new RuntimeException("注册菜单异常，未找到父级菜单");
					}
				}
				
			}
			
			
		}
	}
	
	private boolean hasParent(List<Resource> resources,Resource resource){
		if(ObjectUtils.isEmpty(resources) || resource == null){
			return false;
		}
		
		for(Resource res : resources){
			if(res.getResourceCd().equals(resource.getResourceCd())){
				return true;
			}
		}
		return false;
	}

	@Override
	public Resource findResourceByCd(String resourceCd) {
		return resourceDao.findByCd(resourceCd);
	}

	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	public List<Resource> findByRoleId(String roleId) {
		List<Permission> permissions = permissionDao.findByRoleId(roleId);
		List<Resource> resources=new ArrayList<Resource>();
		for (Permission permission : permissions) {
			resources.add(permission.getResource());
		}
		return resources;
	}

}
