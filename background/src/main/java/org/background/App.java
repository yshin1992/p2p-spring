package org.background;

import org.background.config.ShiroConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Import(value={ShiroConfig.class})
@ComponentScan(basePackages={"org.dao.hibernate","org.business","org.background.controller","org.background.shiro"})
//@EnableJpaRepositories(basePackages={"org.domain"})
@EntityScan(basePackages={"org.domain"})
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
