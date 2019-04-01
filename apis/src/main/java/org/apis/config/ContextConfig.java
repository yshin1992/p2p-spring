package org.apis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"org.apis.service","org.apis.listener"})
public class ContextConfig {

}
