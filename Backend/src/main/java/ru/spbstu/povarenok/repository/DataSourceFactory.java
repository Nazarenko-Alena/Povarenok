package ru.spbstu.povarenok.repository;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataSourceFactory {

    @Value("${database.url}")
    public String DB_URL;

    @Value("${database.name}")
    public String DB_NAME;

    @Value("${database.user}")
    public String DB_USER;

    @Value("${database.password}")
    public String DB_PASSWORD;

    @Bean
    public PGSimpleDataSource DataSourceFactory() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(DB_URL);
        dataSource.setDatabaseName(DB_NAME);
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }
}