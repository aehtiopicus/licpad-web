package com.aehtiopicus.licpad.web.config.server;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

import com.aehtiopicus.licpad.core.utils.ExternalProperties;
import com.aehtiopicus.licpad.core.utils.PropertiesReader;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.aehtiopicus.licpad.core.repository")
@ImportResource("classpath:/licpad-properties.xml")
public class ApplicationModule{

	private static final String DERBY_STATUS = PropertiesReader.getInstance().readProperty(ExternalProperties.DERYB_CREATE_DB);
	
	private static final String DERBY_USER = "#{lpProperties['derby.user']}";
	private static final String DERBY_PASSWORD = "#{lpProperties['derby.passwd']}";
	private static final String DERBY_DRIVER = "#{lpProperties['derby.driver']}";
	private static final String DERBY_URL = "#{lpProperties['derby.url']}";
	private static final String DERBY_DIALECT = "#{lpProperties['derby.dialect']}";
	
	@Value(DERBY_USER)
	private String dbUser;
	
	@Value(DERBY_PASSWORD)
	private String dbPasswd;
	
	@Value(DERBY_DRIVER)
	private String dbDriver;
	
	@Value(DERBY_URL)
	private String dbUrl;
	
	@Value(DERBY_DIALECT)
	private String dbDialect;
	

	
	@Autowired
	private ApplicationContext ap;
	
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
				.setPackagesToScan(new String[] { "com.aehtiopicus.licpad.core" });

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
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl+";create="+DERBY_STATUS);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPasswd);		
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
				setProperty("hibernate.dialect",dbDialect);
				setProperty("hibernate.format_sql","true");
			}
		};
	}
}
