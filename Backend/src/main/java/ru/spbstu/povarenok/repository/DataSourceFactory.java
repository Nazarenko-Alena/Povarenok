package ru.spbstu.povarenok.repository;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Profile("test")
public class DataSourceFactory {

    @Value ("${database.driverClassName}")
    public String DB_CLASSNAME;

    @Value("${database.url}")
    public String DB_URL;

    @Value("${database.user}")
    public String DB_USER;

    @Value("${database.password}")
    public String DB_PASSWORD;

    @Bean
    public DataSource DataSourceFactory() {

            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(DB_CLASSNAME);
            dataSourceBuilder.url(DB_URL);
            dataSourceBuilder.username(DB_USER);
            dataSourceBuilder.password(DB_PASSWORD);
            return dataSourceBuilder.build();
    }
}