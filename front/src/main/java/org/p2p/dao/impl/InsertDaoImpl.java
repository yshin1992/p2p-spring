package org.p2p.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.p2p.dao.InsertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("insertDao")
public class InsertDaoImpl implements InsertDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insertForObject(String sqlId, Object obj) {
		return sqlSessionTemplate.insert(sqlId, obj);
	}

	@Override
	public int insertForList(String sqlId, List<Object> objList) {
		Integer result = 0;
		if(null == objList || objList.isEmpty()){
			return result;
		}
		for(Object obj : objList){
			result += sqlSessionTemplate.insert(sqlId, obj);
		}
		return result;
	}

}
