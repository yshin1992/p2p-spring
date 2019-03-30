package org.p2p.ext;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义MyBatis分页拦截器
 * https://www.cnblogs.com/snake-hand/p/3177907.html
 * @author yanshuai
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})  
public class PaginationInterceptor implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final String PAGE_SQL_ID = "page"; 
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		
		MetaObject metaStatementHandler = MetaObject.forObject(
				statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		
		Object object = metaStatementHandler.getValue("delegate.rowBounds");
		RowBounds rowBounds = null;
		if (object != null){
			rowBounds = (RowBounds) object;
		}
		Object tmpObject = metaStatementHandler.getValue("delegate.mappedStatement");
		MappedStatement mappedStatement = null;
		if (tmpObject != null) {
			mappedStatement = (MappedStatement)tmpObject;
		}
		String sqlId = mappedStatement.getId(); 
		String[] temp = sqlId.split("_");
		String ident = temp[temp.length-1];
		boolean flag = false;
		if (PAGE_SQL_ID.equalsIgnoreCase(ident)) {
			flag = true;
		} 
		
		if (flag && (rowBounds == null || rowBounds == RowBounds.DEFAULT)) {
			
			String originalSql = (String) metaStatementHandler
					.getValue("delegate.boundSql.sql");
		
			if (logger.isErrorEnabled()) {
				logger.error("old sql:{}",originalSql);
			}
 			
			StringBuffer cntSql = new StringBuffer();
			cntSql.append("SELECT COUNT(0) AS dataRecordCount FROM (");
			cntSql.append(originalSql);
			cntSql.append(" ) AS _temp_");
			
			if (logger.isErrorEnabled()) {
				logger.error("new sql {}",cntSql.toString());
			}
			
			metaStatementHandler.setValue("delegate.boundSql.sql",cntSql.toString());
			
			return invocation.proceed();
			
		}else if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}
		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		Configuration configuration = (Configuration) metaStatementHandler
				.getValue("delegate.configuration");
		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables()
					.getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		if (databaseType == null) {
			throw new RuntimeException(
					"the value of the dialect property in configuration.xml is not defined : "
							+ configuration.getVariables().getProperty(
									"dialect"));
		}
		Dialect dialect = null;
		switch (databaseType) {
		case ORACLE:
			dialect = new OracleDialect();
			break;
		case MYSQL:
			dialect = new MysqlDialect();
			break;
		}

		metaStatementHandler.setValue("delegate.boundSql.sql", dialect
				.getLimitString(originalSql, rowBounds.getOffset(),
						rowBounds.getLimit()));
		metaStatementHandler.setValue("delegate.rowBounds.offset",
				RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit",
				RowBounds.NO_ROW_LIMIT);
		if (logger.isErrorEnabled()) {
			BoundSql boundSql = statementHandler.getBoundSql();
			logger.error("生成分页SQL : {}" , boundSql.getSql());
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

}
