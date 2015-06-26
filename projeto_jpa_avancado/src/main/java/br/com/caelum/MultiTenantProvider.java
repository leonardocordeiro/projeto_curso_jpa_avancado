package br.com.caelum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

public class MultiTenantProvider implements MultiTenantConnectionProvider {
	private static final long serialVersionUID = 1L;
	//private final ConnectionProvider connectionProvider = ConnectionProviderUtils.buildConnectionProvider( "master" );

	

	@Override
	public boolean isUnwrappableAs(@SuppressWarnings("rawtypes") Class arg0) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:/projeto_jpa");
		return cnn;
	}

	@Override
	public Connection getConnection(String tenantName) throws SQLException {
		Connection cnn = getAnyConnection();
		cnn.createStatement().execute("SET SCHEMA '" + tenantName + "'");
		
		return cnn;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public void releaseConnection(String arg0, Connection connection) throws SQLException {
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

}
