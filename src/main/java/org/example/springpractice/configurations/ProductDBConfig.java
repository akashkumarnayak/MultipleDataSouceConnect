package org.example.springpractice.configurations;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages  = "org.example.springpractice.repositories.product",
        entityManagerFactoryRef = "ProductEntityManagerFactory",
        transactionManagerRef = "ProductTransactionManager"
)

public class ProductDBConfig {

    @Autowired
    private Environment env;

    @Bean(name = "ProductDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-productdb.configuration")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(this.env.getProperty("spring.datasource-productdb.driver-class-name"));
        dataSource.setJdbcUrl(this.env.getProperty("spring.datasource-productdb.url"));
        dataSource.setUsername(this.env.getProperty("spring.datasource-productdb.username"));
        dataSource.setPassword(this.env.getProperty("spring.datasource-productdb.password"));
        return dataSource;
    }


    @Bean(name = "ProductEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("ProductDataSource") DataSource dataSource) {

        Map<String, String> productDBJpaProperties = new HashMap<>();
        productDBJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        productDBJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return builder
                .dataSource(dataSource)
                .packages("org.example.springpractice.models.product") // Entity package
                .properties(productDBJpaProperties)
                .persistenceUnit("productdb")
                .build();
    }


    @Bean(name = "ProductTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("ProductEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
