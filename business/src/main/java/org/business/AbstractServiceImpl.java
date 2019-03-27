package org.business;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.dao.hibernate.AbstractDao;
import org.domain.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pagination.PageRequest;
import pagination.PageResponse;

@Component("abstractService")
public class AbstractServiceImpl<T extends AbstractEntity> implements AbstractService<T> {

	@Autowired
	private AbstractDao<T> abstractDao;
	
	@Override
	public T queryById(Object id) {
		return abstractDao.queryById(id);
	}

	@Transactional
	@Override
	public void save(T t) {
		abstractDao.save(t);
	}

	@Transactional
	@Override
	public void update(T t) {
		abstractDao.update(t);
	}

	@Override
	public List<T> queryList(String HQL, Map<String, Object> condition) {
		return abstractDao.queryList(HQL,condition);
	}

	@Override
	public List<T> queryList(String HQL) {
		return abstractDao.queryList(HQL);
	}

	@Override
	public List<T> nativeQueryList(String SQL, Map<String, Object> condition) {
		return abstractDao.nativeQueryList(SQL,condition);
	}

	@Override
	public List<T> nativeQueryList(String SQL) {
		return abstractDao.nativeQueryList(SQL);
	}

	@Override
	public PageResponse<T> queryPageByHQL(String HQL,
			Map<String, Object> condition, PageRequest request) {
		return abstractDao.queryPageByHQL(HQL, condition, request);
	}

	@Override
	public Long countHSQL(String HSQL, Map<String, Object> condition) {
		return abstractDao.countHSQL(HSQL, condition);
	}

	@Override
	public T findSingleResultByHQL(String HQL, Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return abstractDao.findSingleResultByHQL(HQL, condition);
	}

	@Override
	public T findSingleResultByHQL(String HQL) {
		return abstractDao.findSingleResultByHQL(HQL);
	}
	
	@Transactional
	@Override
	public void saveOrUpdate(T t) {
		abstractDao.saveOrUpdate(t);
	}

}
