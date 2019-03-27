package org.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.domain.AbstractEntity;

import pagination.PageRequest;
import pagination.PageResponse;

public interface AbstractDao<T extends AbstractEntity> {

	T queryById(Object id);
	
	void save(T t);
	
	void update(T t);
	
	List<T> queryList(String HQL,Map<String,Object> condition);
	
	List<T> queryList(String HQL);
	
	List<T> nativeQueryList(String SQL,Map<String,Object> condition);
	
	List<T> nativeQueryList(String SQL);
	
	PageResponse<T> queryPageByHQL(String HQL,Map<String,Object> condition,PageRequest request);
	
	Long countHSQL(String HSQL,Map<String,Object> condition);
	
	T findSingleResultByHQL(String HQL,Map<String,Object> condition);
	
	T findSingleResultByHQL(String HQL);
	
	void saveOrUpdate(T t);
}
