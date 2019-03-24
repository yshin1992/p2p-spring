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
		return null;
	}

	@Override
	public List<Application> findAll() {
		TypedQuery<Application> query = entityManager.createQuery("from Application order by listSort asc",Application.class);
		return query.getResultList();
	}

}
