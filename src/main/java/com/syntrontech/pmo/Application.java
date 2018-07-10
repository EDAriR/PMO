package com.syntrontech.pmo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*
 * 在spring boot中,此為serve啟動的主程式,會執行在此package下的所有程式(可以看成一個package內為一個專案)
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@PropertySource(value="${spring.config.location:classpath:application.yml}")
@EnableJpaRepositories(basePackages = {
		"com.syntrontech.pmo.auth.repository.*",
		"com.syntrontech.pmo.cip.repository.*",
		"com.syntrontech.pmo.syncare1.repository.*"
})
@EntityScan(basePackages = {
		"com.syntrontech.pmo.auth.model.*",
		"com.syntrontech.pmo.cip.model.*",
		"com.syntrontech.pmo.syncare1.model.*"
})
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
		.sources(Application.class)
		.run(args);
	}
	
}
