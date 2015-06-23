package br.com.caelum;

import java.beans.PropertyVetoException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@ComponentScan("br.com.caelum")
@EnableWebMvc
public class Configurador extends WebMvcConfigurerAdapter {
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean(destroyMethod = "close")
	public DataSource getDataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setMinPoolSize(3);
			ds.setMaxIdleTime(30);
			ds.setMaxPoolSize(10);
			ds.setAcquireIncrement(1);

		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		ds.setUser("root");
		ds.setPassword("");
		ds.setJdbcUrl("jdbc:mysql://localhost/projeto_jpa");

		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = 
							new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(getDataSource());
		
		return entityManagerFactory;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addWebRequestInterceptor(getOpenEMInViewInterceptor());
	}
	
	@Bean
	public OpenEntityManagerInViewInterceptor getOpenEMInViewInterceptor() { 
		return new OpenEntityManagerInViewInterceptor();
	}
	
	@Bean
	public JpaTransactionManager getTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

}
