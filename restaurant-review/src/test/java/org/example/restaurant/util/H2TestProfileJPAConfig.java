package org.example.restaurant.util;


import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class H2TestProfileJPAConfig {

//    @Bean
//    @Profile("test")
//    public DataSource dataSource() {
//        DriverManagerDataSource originalDataSource = new DriverManagerDataSource();
//        originalDataSource.setDriverClassName("org.h2.Driver");
//        originalDataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
//        originalDataSource.setUsername("sa");
//        originalDataSource.setPassword("sa");
//
//        ChainListener listener = new ChainListener();
//        listener.addListener(new DataSourceQueryCountListener());
//        return ProxyDataSourceBuilder
//                .create(originalDataSource)
//                .name("DS-Proxy")
//                .listener(listener)
//                .build();
//    }

    // configure entityManagerFactory
    // configure transactionManager
    // configure additional Hibernate properties
}
