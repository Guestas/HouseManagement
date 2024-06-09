package com.mc.HouseManagement.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        /*DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        Resource resource = new ClassPathResource("Apartments.sql");
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(resource));
        return initializer;*/
        return null;
    }
}
