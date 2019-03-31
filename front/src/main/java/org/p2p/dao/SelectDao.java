package org.p2p.dao;

import java.util.List;

/**
 * 查询Dao接口
 * @author yanshuai
 *
 */
public interface SelectDao {

	/**
     * 获取多条数据
     * @param sqlId SQLID
     * @return 查询数据集
     * */
    <E> List<E> selectForList(String sqlId);
    
    /**
     * 获取多条数据
     * @param sqlId SQLID
     * @param condition 检索条件
     * @return 查询数据集
     * */
    <E> List<E> selectForList(String sqlId,Object condition);
	
    /**
     * ibatis分页查询
     * @param sqlId SQLID
     * @param skipRecord 分页开始行数
     * @param maxRecord 一次返回的最多条数
     * @return 查询数据集
     * */
    <E> List<E> selectForList(String sqlId,int skipRecord,int maxRecord);
    
    /**
     * ibatis分页查询
     * @param sqlId SQLID
     * @param condition 检索条件
     * @param skipRecord 分页开始行数
     * @param maxRecord 一次返回的最多条数
     * @return 查询数据集
     * */
    <E> List<E> selectForList(String sqlId,Object condition,int skipRecord,int maxRecord);
    
    /**
     * 查询返回单个对象
     * @param sqlId SQLID
     * @param returnType 返回对象类型
     * @return 单个对象
     * */
    <E> E selectForObject(String sqlId,Class<E> returnType);
    
    /**
     * 查询返回单个对象
     * 检索0条记录时返回returnType类型各属性值为null的对象
     * @param sqlId SQLID
     * @param condition 查询条件
     * @param returnType 返回对象类型
     * @return 单个对象
     * */
    <E> E selectForObject(String sqlId,Object condition,Class<E> resultType);
    
    
    /**
     * 返回满足检索条件的数据条数
     * 
     * @param sqlId 此SQLID必须以 _page 结尾（半角下划线加单词page)并且sql返回类必须继承BaseEntity
     * @param condition 检索条件对象
     * @return
     */
    int count(String sqlId,Object condition);
    
    
    /**
     * 根据分页SQL返回总数据条数
     * @param sqiId 此SQLID必须以 _page 结尾（半角下划线加单词page)并且sql返回类必须继承BaseEntity
     * @return
     */
    int count(String sqlId);
}
