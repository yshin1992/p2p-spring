package org.dao.hibernate.system.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.dao.hibernate.system.MenuDao;
import org.domain.system.Menu;
import org.springframework.stereotype.Repository;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Menu> queryAll(String appCd, String resourcePcd) {
		StringBuilder hql = new StringBuilder("from Menu m where m.appCd=:appCd ");
		if(!StringUtils.isEmpty(resourcePcd)){
			hql.append(" and m.parent!=null and m.parent.resourceCd=:resourcePcd");
		}
		hql.append(" order by listSort asc");
		TypedQuery<Menu> query = entityManager.createQuery(hql.toString(), Menu.class);
		query.setParameter("appCd", appCd);
		if(!StringUtils.isEmpty(resourcePcd)){
			query.setParameter("resourcePcd", resourcePcd);
		}
		return query.getResultList();
	}

}
