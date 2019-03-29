package org.dao.hibernate.system.impl;

import java.util.Arrays;
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
		Resource res = entityManager.find(Resource.class, resource.getId());
		BeanUtils.copyProperties(resource, res);
		entityManager.merge(res);
		entityManager.flush();
		resource=res;
	}

	@Override
	public void saveOrUpdate(Resource resource) {
		Resource oRes=findByCd(resource.getResourceCd());
		if(oRes!=null){
			BeanUtils.copyProperties(resource,oRes,new String[]{"resourceId","id","state","createTime","effTime","expTime","createBy"});//修改时不修改状态
			update(oRes);
		}else {
			save(resource);
		}
		
	}

	@Override
	public List<Resource> findByIds(String[] ids) {
		if(ids == null || ids.length == 0){
			return null;
		}
		StringBuilder HQL = new StringBuilder("from Resource where state='F0A' and resourceId in (:ids)");
		TypedQuery<Resource> query = entityManager.createQuery(HQL.toString(), Resource.class);
		query.setParameter("ids", Arrays.asList(ids));
		return query.getResultList();
	}

	@Override
	public List<Resource> findByUser(String userCd) {
		StringBuffer sb=new StringBuffer("from Resource a");
		sb.append(" where a.state='F0A' and now() between a.effTime and a.expTime");
		sb.append(" and (a.resourceId in");
		sb.append(" (select c.resource.resourceId from RolePermission c,UserRole d where c.role.id=d.role.id and d.user.userCd=:userCd")
		  .append("     and c.role.state='F0A' and now() between c.role.effTime and c.role.expTime") //角色有效
		  .append("     and c.state='F0A' and now() between c.effTime and c.expTime")                //角色资源关系有效
		  .append("     and d.user.state='F0A' and now() between d.user.effTime and d.user.expTime") //用户有效
		  .append("     and d.state='F0A' and now() between d.effTime and d.expTime))");              //用户角色关系有效
		sb.append(" order by a.listSort");
		TypedQuery<Resource> query = entityManager.createQuery(sb.toString(), Resource.class);
		query.setParameter("userCd", userCd);
		return query.getResultList();
	}

}
