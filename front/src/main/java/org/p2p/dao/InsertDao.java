package org.p2p.dao;

import java.util.List;

public interface InsertDao {
	/**
	 * 插入单条数据
	 * @param obj 插入对象
	 * @param sqlId 操作SQLID
	 * @return 成功插入的数据条数
	 * */
	public int insertForObject(String sqlId, Object obj);
	
	/**
	 * 插入复数条数据
	 * @param objList 插入对象
	 * @param sqlId 操作SQLID
	 * @return 成功插入的数据条数
	 * */
	public int insertForList(String sqlId, List<Object> objList);
}
