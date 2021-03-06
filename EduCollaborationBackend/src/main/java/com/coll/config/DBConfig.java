package com.coll.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.coll.model.Blog;
import com.coll.model.BlogComment;
import com.coll.model.Friend;
import com.coll.model.Job;
import com.coll.model.ProfilePicture;
import com.coll.model.UserDetail;

@Configuration
@ComponentScan("com.coll")
@EnableTransactionManagement
public class DBConfig {
	
	public DataSource getOracleDataSource()
	{
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("collaborate");
		dataSource.setPassword("root123");
		System.out.println("----DataSource is created---");
		
		return dataSource;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties properties=new Properties();
		
		properties.put("hibernate.hbmddl2.auto","update");
		properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		
		LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(this.getOracleDataSource());
		factory.addProperties(properties);
		
		factory.addAnnotatedClass(Blog.class);
		factory.addAnnotatedClass(BlogComment.class);
		factory.addAnnotatedClass(UserDetail.class);
		factory.addAnnotatedClass(Job.class);
		factory.addAnnotatedClass(Friend.class);
		factory.addAnnotatedClass(ProfilePicture.class);
		
		SessionFactory sessionFactory=factory.buildSessionFactory();
		System.out.println("---Session Factory Object is created---");
		
		return sessionFactory;
	}

	@Bean(name="txManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		System.out.println("---Transaction Manager Object is created ---");
		return new HibernateTransactionManager(sessionFactory);
	}
}
