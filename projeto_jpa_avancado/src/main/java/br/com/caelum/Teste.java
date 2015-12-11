package br.com.caelum;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Teste {
	public static void main(String[] args) throws SQLException, PropertyVetoException {
		ComboPooledDataSource dataSource = new JpaConfigurator().getDataSource();
		
		for(int i = 0; i < 10; i ++) {
			dataSource.getConnection();
			
			System.out.println(i + 1 + " - Conexões existentes: " + dataSource.getNumConnections());
			System.out.println(i + 1 + " - Conexões ocupadas: " + dataSource.getNumBusyConnections());
			System.out.println(i + 1 + " - Conexões ociosas: " + dataSource.getNumIdleConnections());
			
			System.out.println("");
		}

		
	}
}
