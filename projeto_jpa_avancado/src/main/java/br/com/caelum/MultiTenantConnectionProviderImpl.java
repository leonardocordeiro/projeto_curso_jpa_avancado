package br.com.caelum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

	private static final long serialVersionUID = 1L;
	
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";
	private static final String CONNECTION_URL = "jdbc:mysql://localhost/projeto_jpa";

	@Override
	public Connection getAnyConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
	}

	@Override
	public Connection getConnection(String tenantKey) throws SQLException {
		final Connection connection = getAnyConnection();
		connection.createStatement().execute("use " + tenantKey + "");
		return connection;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	@Override
	public void releaseConnection(String tenantKey, Connection connection)
			throws SQLException {
		releaseAnyConnection(connection);

	}

	@Override
	public boolean isUnwrappableAs(Class arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

}
