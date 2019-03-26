package org.background;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@Import(value={ShiroConfig.class})//可以将这些配置文件放入扫描包内，可以达到同样的效果
@ComponentScan(basePackages={"org.dao.hibernate","org.business","org.background"})
//@EnableJpaRepositories(basePackages={"org.domain"})
@EntityScan(basePackages={"org.domain"})
@EnableTransactionManagement//开启事务支持
public class App 
{
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
