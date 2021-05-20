package com.testapp.companymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@SpringBootApplication
@PropertySource(ignoreResourceNotFound = true, value = {"file:classpath:application.properties"} )
@ComponentScan(basePackages = "com.application")
public class CompanyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementApplication.class, args);
	}

}
