package com.syntrontech.pmo.syncare1.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

public class Syncare1DataSourceConfig {


    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
            basePackages = {"com.syntrontech.pmo.syncare1"})
    public class CustomerDataSourceConfig {

        @Bean
        @Primary
        @ConfigurationProperties(prefix = "spring.mysql")
        public DataSource primaryDataSource() {
            return DataSourceBuilder.create().build();
        }


        @Primary
        @Bean(name = "entityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                EntityManagerFactoryBuilder builder, DataSource dataSource) {
            return builder
                    .dataSource(dataSource)
                    .packages("com.syntrontech.pmo.syncare1")
                    .persistenceUnit("syncare1")
                    .build();
        }

        @Primary
        @Bean(name = "transactionManager")
        public PlatformTransactionManager transactionManager(
                EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }
    }
}
