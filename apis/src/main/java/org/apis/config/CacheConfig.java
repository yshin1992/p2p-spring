package org.apis.config;

import net.sf.ehcache.CacheManager;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching//开启缓存
public class CacheConfig {

	//https://blog.csdn.net/u012106290/article/details/52154241
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactory(){
		EhCacheManagerFactoryBean ehCacheManagerFactory = new EhCacheManagerFactoryBean();
		ehCacheManagerFactory.setConfigLocation(new ClassPathResource("/ehcache.xml"));
		return ehCacheManagerFactory;
	}
	
	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
		return new EhCacheCacheManager(cacheManager);
	}
	
}
