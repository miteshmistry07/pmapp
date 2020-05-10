package org.mistry;


import org.mistry.config.FileStorageProperties;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class PropertyManagementApplication extends SpringBootServletInitializer {
		
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context  = SpringApplication.run(PropertyManagementApplication.class, args);
	}

}