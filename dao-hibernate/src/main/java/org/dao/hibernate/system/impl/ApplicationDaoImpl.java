package org.dao.hibernate.system.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.dao.hibernate.system.ApplicationDao;
import org.domain.system.Application;
import org.springframework.stereotype.Repository;

@Repository("applicationDao")
public class ApplicationDaoImpl implements ApplicationDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Application app) {
		entityManager.persist(app);
		entityManager.flush();
	}

	@Override
	public void update(Application app) {
		entityManager.merge(app);
		entityManager.flush();
	}

	@Override
	public Application findByAppCd(String appCd) {
		Query query = entityManager.createQuery("from Application where appCd=:appCd");
		query.setParameter("appCd", appCd);
		List<?> resultList = query.getResultList();
		return resultList.size()>0?(Application)resultList.get(0):null;
	}

	@Override
	public List<Application> findByUser(String userCd) {
		StringBuffer sb=new StringBuffer(" from Application a where (a.appId in");
		sb.append(" (select c.resource.app.appId from RolePermission c,UserRole d where c.role.id=d.role.id and d.user.userCd=:userCd")
		  .append("     and c.role.state='F0A' and now() between c.role.effTime and c.role.expTime") //角色有效
		  .append("     and c.state='F0A' and now() between c.effTime and c.expTime")                //角色资源关系有效
  		  .append("     and d.user.state='F0A' and now() between d.user.effTime and d.user.expTime") //用户有效
		  .append("     and d.state='F0A' and now() between d.effTime and d.expTime)");              //用户角色关系有效
		sb.append(") order by a.listSort");
		TypedQuery<Application> query = entityManager.createQuery(sb.toString(), Application.class);
		query.setParameter("userCd", userCd);
		return query.getResultList();
	}

	@Override
	public List<Application> findAll() {
		TypedQuery<Application> query = entityManager.createQuery("from Application order by listSort asc",Application.class);
		return query.getResultList();
	}

}
