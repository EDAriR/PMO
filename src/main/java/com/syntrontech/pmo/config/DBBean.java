package com.syntrontech.pmo.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBBean {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.mysql")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	    
	@Bean(name = "cipdbDatasource")
	@ConfigurationProperties(prefix = "spring.cipdb")
	public DataSource cipdbDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "secondDatasource")
	@ConfigurationProperties(prefix = "spring.cipdb")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}
}
