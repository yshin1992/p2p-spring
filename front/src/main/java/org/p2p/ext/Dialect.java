package org.p2p.ext;

/**
 * 数据库方言类，定义抽象的查询语句方法（用于分页）
 * @author yanshuai
 *
 */
public abstract class Dialect {

	public static enum Type{  
	     MYSQL,  
	     ORACLE  
	  }  
	
	public abstract String getLimitString(String sql, int skipResults, int maxResults); 
	
}
