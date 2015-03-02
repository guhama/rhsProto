package com.rhs.data.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "rhsEntityManagerFactory", transactionManagerRef = "rhsTransactionManager", basePackages = { "com.rhs.data" })
public class EntityMgrConfig {

	@Autowired
	JpaVendorAdapter jpaVendorAdapter;
	
	@Autowired
	JpaDialect rhsJpaDialect;

	/**
	 * Primary because if we have activated embedded databases, we do not want
	 * the application to connect to an external database.
	 * 
	 * @return the data source
	 */
	@Bean(name = "rhsDataSource")
	public DataSource dataSource() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl("jdbc:hsqldb:mem:test");
        cpds.setUser("sa");
        cpds.setPassword("");
		return cpds;
	}

	/**
	 * Entity manager.
	 * 
	 * @return the entity manager
	 */
	@Bean(name = "rhsEntityManager")
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}

	/**
	 * Entity manager factory.
	 * 
	 * @return the entity manager factory
	 */
	@Bean(name = "rhsEntityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource());
		Properties jpaProp = new Properties();
		jpaProp.put("hibernate.hbm2ddl.auto", "create-drop");
		jpaProp.put("hibernate.show_sql", "true");
		lef.setJpaProperties(jpaProp);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan("com.rhs.data.entity");
		lef.setPersistenceUnitName("patientPersistenceUnit");
		lef.setJpaDialect(rhsJpaDialect);
		lef.afterPropertiesSet();
		return lef.getObject();
	}
	
	
	/**
	 * Transaction manager.
	 * 
	 * @return the platform transaction manager
	 */
	@Bean(name = "rhsTransactionManager")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}

}
