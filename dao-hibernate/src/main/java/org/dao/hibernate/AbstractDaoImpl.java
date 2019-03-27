package org.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.domain.AbstractEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import pagination.PageRequest;
import pagination.PageResponse;

@Component("abstractDao")
public class AbstractDaoImpl<T extends AbstractEntity> implements AbstractDao<T> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> entityClass;
	
	public AbstractDaoImpl() {
		entityClass = getEntityClass();
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass(){
		ResolvableType type = ResolvableType.forClass(ClassUtils.getUserClass(getClass()));
		return (Class<T>) type.as(AbstractDao.class).getGeneric(0).resolve();
	}
	
	@Override
	public void save(T t) {
		entityManager.persist(t);
		entityManager.flush();
	}

	@Override
	public void update(T t) {
		T entityTemp = this.entityManager.find(this.entityClass, t.getId());
		BeanUtils.copyProperties(t, entityTemp);
		entityManager.merge(entityTemp);
		entityManager.flush();
		t = entityTemp;
	}

	@Override
	public List<T> queryList(String HQL, Map<String, Object> condition) {
		TypedQuery<T> query = entityManager.createQuery(HQL,entityClass);
		for(String key:condition.keySet()){
			query.setParameter(key, condition.get(key));
		}
		return query.getResultList();
	}

	@Override
	public List<T> queryList(String HQL) {
		return this.queryList(HQL,new HashMap<String,Object>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> nativeQueryList(String SQL, Map<String, Object> condition) {
		Query query = entityManager.createNativeQuery(SQL, entityClass);
		for(String key:condition.keySet()){
			query.setParameter(key, condition.get(key));
		}
		return query.getResultList();
	}

	@Override
	public List<T> nativeQueryList(String SQL) {
		return this.nativeQueryList(SQL, new HashMap<String,Object>());
	}

	@Override
	public PageResponse<T> queryPageByHQL(String HQL,
			Map<String, Object> condition, PageRequest request) {
		TypedQuery<T> query = entityManager.createQuery(HQL,entityClass);
		for(String key:condition.keySet()){
			query.setParameter(key, condition.get(key));
		}
		query.setFirstResult(request.getFirstResultNo());
		query.setMaxResults(request.getLimit());
		List<T> resultList = query.getResultList();
		
		PageResponse<T> pager = new PageResponse<T>(request);
		pager.setData(resultList);
		pager.setCount(this.countHSQL(HQL, condition));
		return pager;
	}

	@Override
	public Long countHSQL(String HSQL, Map<String, Object> condition) {
		if(StringUtils.isEmpty(HSQL)){
			return 0L;
		}
		String tmp = "select count(0) " + HSQL.substring(HSQL.indexOf("from"));
		Query query = entityManager.createQuery(tmp);
		for(String key:condition.keySet()){
			query.setParameter(key, condition.get(key));
		}
		return (Long) query.getSingleResult();
	}

	@Override
	public T queryById(Object id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public T findSingleResultByHQL(String HQL, Map<String, Object> condition) {
		TypedQuery<T> query = entityManager.createQuery(HQL,entityClass);
		for(String key:condition.keySet()){
			query.setParameter(key, condition.get(key));
		}
		List<T> resultList = query.getResultList();
		if(resultList.size()>1){
			throw new RuntimeException("查找到多条数据，期望查找到一条数据!");
		}
		return resultList.size()==1?resultList.get(0):null;
	}

	@Override
	public T findSingleResultByHQL(String HQL) {
		return this.findSingleResultByHQL(HQL,new HashMap<String,Object>());
	}

	@Override
	public void saveOrUpdate(T t) {
		T entityTemp;
		//这个实体没有设置过ID，这里便认为数据库中没有这个数据
		if(StringUtils.isEmpty(t.getId())){
			t.init();
			entityTemp = t;
		}else{
			//有ID的情况下，则从数据库中查找到这条数据
			entityTemp = entityManager.find(entityClass, t.getId());
			BeanUtils.copyProperties(t, entityTemp);
		}
		entityTemp = entityManager.merge(entityTemp);
		BeanUtils.copyProperties(entityTemp, t);
		entityManager.flush();
	}

}
