package com.wordprocessor.worker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.yml")
public class DBConfig {


    @Bean
    public DataSource dataSource(Environment env) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUrl(env.getProperty("hsql.url"));
        ds.setUsername(env.getProperty("hsql.username"));
        ds.setPassword(env.getProperty("hsql.password"));

        var populator = new ResourceDatabasePopulator(
                new ClassPathResource("db/migration/V1_Create_table.sql"));

        DatabasePopulatorUtils.execute(populator, ds);

        return ds;
    }

}
