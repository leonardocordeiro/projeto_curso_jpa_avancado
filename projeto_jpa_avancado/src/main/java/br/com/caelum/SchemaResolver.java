package br.com.caelum;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class SchemaResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		return "CASADOCODIGO";
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

}
