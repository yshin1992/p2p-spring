package org.dao.hibernate.system.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dao.hibernate.system.ApplicationDao;
import org.domain.system.Application;
import org.springframework.beans.factory.annotation.Autowired;
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

}
