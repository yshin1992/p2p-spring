package org.dao.hibernate.system.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dao.hibernate.system.UserDao;
import org.domain.system.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User findByCd(String userCd) {
		Query query = entityManager.createQuery("from User where userCd=:userCd");
		query.setParameter("userCd", userCd);
		return (User) query.getResultList().get(0);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		entityManager.flush();
	}

}
