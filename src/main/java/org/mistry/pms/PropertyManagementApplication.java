package org.mistry.pms;


import org.mistry.pms.config.FileStorageProperties;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.system.JavaVersion;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class PropertyManagementApplication extends SpringBootServletInitializer {
		
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context  = SpringApplication.run(PropertyManagementApplication.class, args);
		
		System.out.println("Spring version: " + SpringVersion.getVersion()); //Get the version of Spring that we are running
		//SPRING CORE 5.1.4 Release
		System.out.println("J2EE version: " + JavaVersion.getJavaVersion()); //Get the version of Java that we are running
		//Java 1.8 
	}

}