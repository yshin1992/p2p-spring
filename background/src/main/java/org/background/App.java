package org.background;

import org.background.config.ShiroConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ContextConfiguration(classes={ShiroConfig.class})
@ComponentScan(basePackages={"org.dao.hibernate","org.business","org.background.controller","org.background.controller"})
@EnableJpaRepositories(basePackages={"org.domain"})
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
