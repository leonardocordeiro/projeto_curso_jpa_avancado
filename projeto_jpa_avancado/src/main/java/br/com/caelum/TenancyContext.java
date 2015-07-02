package br.com.caelum;

public class TenancyContext {

	private static String currentTenant = "projeto_jpa";
	
	public static void setCurrentTenant(String tenant) { 
		currentTenant = tenant;
	}
	
	public static String getCurrentTenant() {
		return currentTenant;
	}
	
}
