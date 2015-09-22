package com.aehtiopicus.licpad.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@EnableTransactionManagement

public class PersistenceContext {


	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSize(1 * 1024 * 1024 * 1024);// 1gb??
		return cmr;
	}
	
	@Bean(name ="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(restDataSource());
		factoryBean
				.setPackagesToScan(new String[] { "com.aehtiopicus.licpad.domain" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() {

			{
				// JPA properties
				setShowSql(true);
				setDatabase(Database.DERBY);
				setGenerateDdl(true);
			}
		};
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setJpaProperties(additionalProperties());

		return factoryBean;
	}

	@Bean(name = "dataSource")
	public DataSource restDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		dataSource.setUrl("jdbc:derby:db/LICCPaDDB;create=true");
		dataSource.setUsername("lw");
		dataSource.setPassword("lw");		
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean()
				.getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean 
	public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
		return new HibernateExceptionTranslator(); 
	}
	 

	final Properties additionalProperties() {
		return new Properties() {

			/**
             *
             */
			private static final long serialVersionUID = 3996171551371239499L;

			{
				// use this to inject additional properties in the EntityManager
				setProperty("hibernate.hbm2ddl.auto", "update");
				setProperty("hibernate.show_sql","true");
				setProperty("hibernate.dialect","org.hibernate.dialect.DerbyDialect");
				setProperty("hibernate.format_sql","true");
			}
		};
	}
}
