package org.apis.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源配置文件
 * @author yanshuai
 *
 */
@Configuration
@EnableJpaRepositories(basePackages={"org.apis.dao"},entityManagerFactoryRef="entityManagerFactory",transactionManagerRef="transactionManager")//开启jpa:repositories
@EnableTransactionManagement//开启事务管理
@PropertySource(value={"classpath:c3p0.properties"})
public class DataSourceConfig {

	@Value("${driverClass}")
	private String driverClass;
	
	@Value("${jdbcUrl}")
	private String jdbcUrl;
	
	@Value("${jdbc.user}")
	private String user;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${c3p0.minPoolSize}")
	private Integer minPoolSize;
	
	@Value("${c3p0.maxPoolSize}")
	private Integer maxPoolSize;
	
	@Value("${c3p0.acquireIncrement}")
	private Integer acquireIncrement;
	
	@Value("${c3p0.acquireRetryAttempts}")
	private Integer acquireRetryAttempts;
	
	@Value("${c3p0.autoCommitOnClose}")
	private Boolean autoCommitOnClose;
	
	@Value("${c3p0.acquireRetryDelay}")
	private Integer acquireRetryDelay;
	
	@Value("${c3p0.checkoutTimeout}")
	private Integer checkoutTimeout;
	
	@Value("${c3p0.idleConnectionTestPeriod}")
	private Integer idleConnectionTestPeriod;
	
	@Value("${c3p0.maxIdleTime}")
	private Integer maxIdleTime;
	
	@Value("${c3p0.testConnectionOnCheckin}")
	private Boolean testConnectionOnCheckin;
	
	//配置DataSource Bean
	@Bean
	public DataSource dataSource(){
		ComboPooledDataSource  dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driverClass);
			dataSource.setJdbcUrl(jdbcUrl);
			dataSource.setUser(user);
			dataSource.setPassword(password);
			dataSource.setMinPoolSize(minPoolSize);
			dataSource.setMaxPoolSize(maxPoolSize);
			dataSource.setAcquireIncrement(acquireIncrement);
			dataSource.setAcquireRetryAttempts(acquireRetryAttempts);
			dataSource.setAutoCommitOnClose(autoCommitOnClose);
			dataSource.setAcquireRetryDelay(acquireRetryDelay);
			dataSource.setCheckoutTimeout(checkoutTimeout);
			dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
			dataSource.setMaxIdleTime(maxIdleTime);
			dataSource.setTestConnectionOnCheckin(testConnectionOnCheckin);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		return dataSource;
	}
	
	//配置EntityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
		jpaVendorAdapter.setShowSql(true);
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("org.domain");
		entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		
		return entityManagerFactory;
	}
	
	//配置事务管理器
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
}
	
}
