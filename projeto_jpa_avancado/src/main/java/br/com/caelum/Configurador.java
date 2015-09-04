package br.com.caelum;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.caelum.dao.CategoriaDao;
import br.com.caelum.dao.LojaDao;
import br.com.caelum.dao.ProdutoDao;
import br.com.caelum.model.Categoria;
import br.com.caelum.model.Loja;
import br.com.caelum.model.Produto;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableWebMvc
@Configuration
@ComponentScan("br.com.caelum")
@EnableTransactionManagement
public class Configurador extends WebMvcConfigurerAdapter {
	
	@Bean
	public ProdutoDao produtoDao() { 
		return new ProdutoDao();
	}
	
	@Bean
	public LojaDao lojaDao() { 
		return new LojaDao();
	}

	@Bean
	public CategoriaDao categoriaDao() { 
		return new CategoriaDao();
	}

	@Bean
	@Scope("request")
	public List<Produto> produtos(ProdutoDao produtoDao) {
		List<Produto> produtos = produtoDao.getProdutos();
		
		return produtos;
	}
	
	@Bean
	public List<Categoria> categorias(CategoriaDao categoriaDao) { 
		List<Categoria> categorias = categoriaDao.getCategorias();
		
		return categorias;
	}
	
	@Bean
	public List<Loja> lojas(LojaDao lojaDao) { 
		List<Loja> lojas = lojaDao.getLojas();
		
		return lojas;
	}
	
	@Bean
	public MessageSource messageSource() { 
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setCacheSeconds(1);
		messageSource.setDefaultEncoding("ISO-8859-1");
		
		return messageSource;
		
	}
	
	@Bean 
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		viewResolver.setExposeContextBeansAsAttributes(true);

		return viewResolver;
	}

	@Bean(destroyMethod="close")
	public DataSource getDataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setMinPoolSize(3);
			ds.setMaxIdleTime(30);
			ds.setMaxPoolSize(5);
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
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = 
							new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactory.setPackagesToScan("br.com.caelum");
		entityManagerFactory.setDataSource(dataSource);
		
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		Properties props = new Properties();
		
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		
		props.setProperty("hibernate.cache.use_query_cache", "true");
		props.setProperty("hibernate.cache.use_second_level_cache", "true");
		props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
		
		entityManagerFactory.setJpaProperties(props);
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
