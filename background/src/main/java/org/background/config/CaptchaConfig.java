package org.background.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class CaptchaConfig {

	@Bean
	public Producer producer(){
		DefaultKaptcha producer = new DefaultKaptcha();
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream("/captcha.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Config config = new Config(prop);
		producer.setConfig(config);
		return producer;
	}
	
}
