package ru.otus.dz21.controller;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.sql.DataSource;

//@Configuration
//@ConfigurationProperties("application")
//@PropertySource("classpath:application.yml")
public class DataSourceConf {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.schema}")
    private String dataSourceSchema;

    @Bean
    DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
            dataSourceConfig.setJdbcUrl("jdbc:h2:mem:testdb");
//        dataSourceConfig.setJdbcUrl(dataSourceUrl);
        dataSourceConfig.setSchema(dataSourceSchema);
//        dataSourceConfig.setSchema("schema.sql");
//        dataSourceConfig.setSchema("classpath:schema.sql");
//            dataSourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
//            dataSourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
        return new HikariDataSource(dataSourceConfig);
    }
}
