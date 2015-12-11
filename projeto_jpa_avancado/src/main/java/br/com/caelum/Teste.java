package br.com.caelum;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Teste {
	public static void main(String[] args) throws SQLException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfigurator.class);

		ComboPooledDataSource dataSource = ctx.getBean(ComboPooledDataSource.class);
		
		for(int i = 0; i < 10; i++) {
			dataSource.getConnection();
			
			System.out.println(i + " - Conexões existentes: " + dataSource.getNumConnections());
			System.out.println(i + " - Conexões ocupadas: " + dataSource.getNumBusyConnections());
			System.out.println(i + " - Conexões stand-by: " + dataSource.getNumIdleConnections());
			
			System.out.println("");
		}
		
		ConfigurableApplicationContext context = (ConfigurableApplicationContext) ctx;
		context.close();
		
	}
}
