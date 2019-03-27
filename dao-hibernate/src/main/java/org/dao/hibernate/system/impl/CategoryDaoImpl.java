package org.dao.hibernate.system.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.dao.hibernate.system.CategoryDao;
import org.domain.system.Category;
import org.springframework.stereotype.Repository;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Category findByCd(String code) {
		StringBuilder hql = new StringBuilder("from Category where categoryCd=:categoryCd and state='F0A'");
		TypedQuery<Category> query = entityManager.createQuery(hql.toString(),Category.class);
		query.setParameter("categoryCd", code);
		return query.getResultList().size()>0?query.getResultList().get(0):null;
	}

	@Override
	public List<Category> findAll() {
		StringBuilder hql = new StringBuilder("from Category where state='F0A'");
		TypedQuery<Category> query = entityManager.createQuery(hql.toString(),Category.class);
		return query.getResultList();
	}

}
