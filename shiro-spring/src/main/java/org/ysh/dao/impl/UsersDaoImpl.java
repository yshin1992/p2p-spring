package org.ysh.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ysh.dao.UsersDao;
import org.ysh.domain.Users;

@Repository("userDao")
public class UsersDaoImpl implements UsersDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Users findUserByName(String name) {
		Query query = sessionFactory.openSession().createQuery("from Users u where u.username=:username").setParameter(1, name);
		return (Users) query.list().get(0);
	}

	@Override
	public void addUser(Users user) {
		Transaction tx = sessionFactory.openSession().beginTransaction();
		sessionFactory.openSession().save(user);
		tx.commit();
	}

}
