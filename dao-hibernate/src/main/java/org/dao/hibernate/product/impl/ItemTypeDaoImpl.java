package org.dao.hibernate.product.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.dao.hibernate.product.ItemTypeDao;
import org.domain.product.ItemType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import pagination.PageRequest;
import pagination.PageResponse;

@Repository("itemTypeDao")
public class ItemTypeDaoImpl implements ItemTypeDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void saveOrUpdate(ItemType itemType) {
		if(null != itemType){
			//获取节点最大值
			String maxCd = getCategoryMaxCd(itemType.getFeeType());
			String itemTypeCd =  itemType.getFeeType() + String.valueOf(Integer.valueOf(maxCd) + 1);
			
			if(!StringUtils.isEmpty(itemType.getItemTypeId())){
				ItemType typeFind = entityManager.find(ItemType.class, itemType.getItemTypeId());
				if(null == typeFind){
					itemType.setItemTypeId(null);
					itemType.setItemTypeCd(itemTypeCd);
					entityManager.persist(itemType);
				}else{
					BeanUtils.copyProperties(itemType, typeFind,new String[]{"itemTypeId","createTime","createBy"});
					entityManager.merge(typeFind);
				}
			}else{
				itemType.setItemTypeId(null);
				itemType.setItemTypeCd(itemTypeCd);
				entityManager.persist(itemType);
			}
			entityManager.flush();
		}
		
		
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
			if(!StringUtils.isEmpty(itemType.getItemTypeNm())){
				hql.append(" and itemTypeNm=:itemTypeNm ");
				condition.put("itemTypeNm", itemType.getItemTypeNm());
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
		query.setMaxResults(pageRequest.getLimit());
		PageResponse<ItemType> pager = new PageResponse<ItemType>(pageRequest);
		pager.setData(query.getResultList());
		pager.setCount(totalCount);
		return pager;
	}

	@Override
	public ItemType queryById(String id) {
		return entityManager.find(ItemType.class, id);
	}
	
	@Override
	public String getCategoryMaxCd(String feeType) {
		String hql = "select max(itemTypeCd) from ItemType where itemTypeCd like :feeType";
		javax.persistence.Query query = entityManager.createQuery(hql);
		query.setParameter("feeType", feeType+"%");
		Object maxCd = query.getSingleResult();
		return maxCd != null ? maxCd.toString() : "0";
	}

	@Override
	public void deleteById(String id) {
		ItemType type = this.queryById(id);
		if(type!= null){
			entityManager.remove(type);
			entityManager.flush();
		}
	}

}
