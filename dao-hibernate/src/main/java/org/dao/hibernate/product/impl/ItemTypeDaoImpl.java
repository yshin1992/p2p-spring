package org.dao.hibernate.product.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.dao.hibernate.product.ItemTypeDao;
import org.domain.product.ItemType;
import org.springframework.stereotype.Repository;

import pagination.PageRequest;
import pagination.PageResponse;

@Repository("itemTypeDao")
public class ItemTypeDaoImpl implements ItemTypeDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(ItemType itemType) {
		entityManager.persist(itemType);
		entityManager.flush();
	}

	@Override
	public PageResponse<ItemType> queryAll(PageRequest pageRequest,ItemType itemType) {
		StringBuilder hql = new StringBuilder("from ItemType where 1=1 ");
		Map<String,Object> condition = new HashMap<String,Object>();
		if(null != itemType){
			if(!StringUtils.isEmpty(itemType.getTemplateNm())){
				hql.append(" and templateNm=:templateNm ");
				condition.put("templateNm", itemType.getTemplateNm());
			}
		}
		TypedQuery<Long> countQuery = entityManager.createQuery("select count(0) "+hql.toString(),Long.class); 
		for(String key:condition.keySet()){
			countQuery.setParameter(key, condition.get(key));
		}
		Long totalCount = countQuery.getSingleResult();
		
		TypedQuery<ItemType> query = entityManager.createQuery(hql.toString(), ItemType.class);
		for(String key:condition.keySet()){
			query.setParameter(key, condition.get(key));
		}
		query.setFirstResult(pageRequest.getFirstResultNo());
		query.setMaxResults(pageRequest.getPageSize());
		PageResponse<ItemType> pager = new PageResponse<ItemType>(pageRequest);
		pager.setResultList(query.getResultList());
		pager.setTotalCount(totalCount);
		return pager;
	}

}
