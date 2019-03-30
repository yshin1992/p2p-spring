package org.p2p.ext;

/**
 * MySQL 分页处理
 * @author yanshuai
 *
 */
public class MysqlDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		sqlBuffer.append(" LIMIT ").append(skipResults).append(",").append(maxResults);
		return sqlBuffer.toString();
	}

}
