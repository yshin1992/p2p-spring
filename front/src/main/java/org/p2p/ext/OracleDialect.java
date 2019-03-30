package org.p2p.ext;

/**
 * Oracle分页实现
 * @author yanshuai
 *
 */
public class OracleDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		sql = sql.trim();  
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);  
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");  
		pagingSelect.append(sql);  
		pagingSelect.append(" ) row_ ) where rownum_ > ").append(skipResults).append(
				" and rownum_ <= ").append(skipResults + maxResults);  
		return pagingSelect.toString(); 
	}

}
