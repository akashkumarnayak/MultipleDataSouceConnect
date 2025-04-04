package org.example.springpractice.configurations;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages  = "org.example.springpractice.repositories.user",
        entityManagerFactoryRef = "UserEntityManagerFactory",
        transactionManagerRef = "UserTransactionManager"
)

public class UserDBConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "UserDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-userdb.configuration")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(this.env.getProperty("spring.datasource-userdb.driver-class-name"));
        dataSource.setJdbcUrl(this.env.getProperty("spring.datasource-userdb.url"));
        dataSource.setUsername(this.env.getProperty("spring.datasource-userdb.username"));
        dataSource.setPassword(this.env.getProperty("spring.datasource-userdb.password"));
        return dataSource;
    }

    @Primary
    @Bean(name = "UserEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("UserDataSource") DataSource dataSource) {

        Map<String, String> UserDBJpaProperties = new HashMap<>();
        UserDBJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        UserDBJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return builder
                .dataSource(dataSource)
                .packages("org.example.springpractice.models.user") // Entity package
                .properties(UserDBJpaProperties)
                .persistenceUnit("userdb")
                .build();
    }

    @Primary
    @Bean(name = "UserTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("UserEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
