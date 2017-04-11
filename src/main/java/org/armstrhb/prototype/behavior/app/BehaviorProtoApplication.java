package org.armstrhb.prototype.behavior.app;

import org.armstrhb.prototype.behavior.service.InitService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableAutoConfiguration
@ComponentScan("org.armstrhb.prototype")
@SpringBootApplication
public class BehaviorProtoApplication {
	public static ApplicationContext context;
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BehaviorProtoApplication.class);
	}
	
	public static void main(String[] args) {
		context = SpringApplication.run(BehaviorProtoApplication.class, args);
		context.getBean(InitService.class).initialize();
	}
}
