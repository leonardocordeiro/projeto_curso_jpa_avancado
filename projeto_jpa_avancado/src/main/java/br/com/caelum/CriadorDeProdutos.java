package br.com.caelum;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class CriadorDeProdutos {
	
	@PersistenceContext
	private EntityManager emf;
	
	@PostConstruct
	public void init() { 
		
		
		
	}

}
