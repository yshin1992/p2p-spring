package org.dao.hibernate.system.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.dao.hibernate.system.ResourceDao;
import org.domain.system.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository("resourceDao")
public class ResourceDaoImpl implements ResourceDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Resource findByCd(String resourceCd) {
		TypedQuery<Resource> query = entityManager.createQuery("from Resource where resourceCd=:resourceCd",Resource.class);
		query.setParameter("resourceCd", resourceCd);
		return query.getResultList().size()>0 ? query.getResultList().get(0) : null;
	}

	@Override
	public List<Resource> findAll() {
		StringBuffer sb=new StringBuffer("from Resource a");
		sb.append(" where a.state='F0A' and now() between a.effTime and a.expTime");
		sb.append(" order by a.listSort");
		TypedQuery<Resource> createQuery = entityManager.createQuery(sb.toString(), Resource.class);
		return createQuery.getResultList();
	}

	@Override
	public void save(Resource resource) {
		entityManager.persist(resource);
		entityManager.flush();
	}

	@Override
	public void update(Resource resource) {
		entityManager.merge(resource);
		entityManager.flush();
	}

	@Override
	public void saveOrUpdate(Resource resource) {
		Resource resourceFind = this.findByCd(resource.getResourceCd());
		if(null != resourceFind){
			BeanUtils.copyProperties(resource, resourceFind,new String[]{"resourceId","state","createTime","effTime","expTime","createBy"});
			entityManager.merge(resourceFind);
		}else{
			entityManager.persist(resource);
		}
		entityManager.flush();
		
	}

}
