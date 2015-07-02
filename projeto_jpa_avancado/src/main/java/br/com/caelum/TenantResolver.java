package br.com.caelum;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		return TenancyContext.getCurrentTenant();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

}
