package org.p2p.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.p2p.dao.SelectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("selectDao")
public class SelectDaoImpl implements SelectDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public <E> List<E> selectForList(String sqlId) {
		return sqlSessionTemplate.selectList(sqlId);
	}

	@Override
	public <E> List<E> selectForList(String sqlId, Object condition) {
		return sqlSessionTemplate.selectList(sqlId, condition);
	}

	@Override
	public <E> List<E> selectForList(String sqlId, int skipRecord, int maxRecord) {
		return sqlSessionTemplate.selectList(sqlId, null, new RowBounds(skipRecord,maxRecord));
	}

	@Override
	public <E> List<E> selectForList(String sqlId, Object condition,
			int skipRecord, int maxRecord) {
		return sqlSessionTemplate.selectList(sqlId, condition, new RowBounds(skipRecord,maxRecord));
	}

	@Override
	public <E> E selectForObject(String sqlId, Class<E> returnType) {
		Object obj = sqlSessionTemplate.selectOne(sqlId);
		
		E retObj = null;
		if(returnType!=null){
			if(obj != null){
				retObj =  (E) returnType.cast(obj);
			}else{
				try {
					retObj = returnType.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		return retObj;
	}

	@Override
	public <E> E selectForObject(String sqlId, Object condition,
			Class<E> resultType) {
		
		Object obj = sqlSessionTemplate.selectOne(sqlId,condition);
		
		E retObj = null;
		if(resultType!=null){
			if(obj != null){
				retObj =  (E) resultType.cast(obj);
			}else{
				try {
					retObj = resultType.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		return retObj;
	}

	@Override
	public int count(String sqlId, Object condition) {
		return sqlSessionTemplate.selectOne(sqlId, condition);
	}

	@Override
	public int count(String sqlId) {
		return sqlSessionTemplate.selectOne(sqlId);
	}

	
	
}
