package org.dao.hibernate.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<Menu> findByUser(String userCd,String appCd,String resourcePCd) {
		Map<String, Object> condition=new HashMap<String, Object>();
		StringBuffer sb=new StringBuffer(" from Menu a where a.appCd=:appCd and a.state='F0A' ");
		if(StringUtils.isNotEmpty(resourcePCd)){
			sb.append(" and a.parent!=null and a.parent.resourceCd=:resourceCd");
			condition.put("resourceCd", resourcePCd);
		}
		sb.append(" and a.id in(select c.resource.id from RolePermission c,UserRole d where c.role.id=d.role.id and d.user.userCd=:userCd")
		  .append("     and c.role.state='F0A' and now() between c.role.effTime and c.role.expTime") //角色有效
		  .append("     and c.state='F0A' and now() between c.effTime and c.expTime")                //角色资源关系有效
  		  .append("     and d.user.state='F0A' and now() between d.user.effTime and d.user.expTime") //用户有效
		  .append("     and d.state='F0A' and now() between d.effTime and d.expTime)");              //用户角色关系有效
		sb.append(") order by a.listSort");
		  condition.put("userCd", userCd);
		  condition.put("appCd", appCd);
		  
		 TypedQuery<Menu> query = entityManager.createQuery(sb.toString(),Menu.class);
		 for(String key:condition.keySet()){
			 query.setParameter(key, condition.get(key));
		 }
		return query.getResultList();
	}

}
