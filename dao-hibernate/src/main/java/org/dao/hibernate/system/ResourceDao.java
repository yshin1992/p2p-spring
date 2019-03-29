package org.dao.hibernate.system;

import java.util.List;

import org.domain.system.Resource;

public interface ResourceDao {
 
	public Resource findByCd(String resourceCd);
	
	public List<Resource> findAll();
	
	public void save(Resource resource);
	
	public void update(Resource resource);
	
	public void saveOrUpdate(Resource resource);
	
	public List<Resource> findByIds(String[] ids);
	
	public List<Resource> findByUser(String userCd);
}
