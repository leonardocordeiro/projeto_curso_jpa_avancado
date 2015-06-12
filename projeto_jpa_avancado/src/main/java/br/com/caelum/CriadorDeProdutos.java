package br.com.caelum;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.caelum.model.Loja;
import br.com.caelum.model.Produto;

@Component
public class CriadorDeProdutos {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private JpaTransactionManager transactionManager;
	
	@PostConstruct
	public void init() {
		TransactionTemplate template = new TransactionTemplate(transactionManager);
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				
				Loja loja1 = new Loja();
				loja1.setNome("Casa do Código");
				
				em.persist(loja1);
				
				Loja loja2= new Loja();
				loja2.setNome("Musical Alegre");
				
				em.persist(loja2);
				
				Loja loja3 = new Loja();
				loja3.setNome("Papelaria do Nico");
				
				em.persist(loja3);

				Produto produto = new Produto();
				produto.setNome("Arquitetura e Design de Projetos Java");
				produto.setLoja(loja1);
				produto.setLinkDaFoto("http://www.arquiteturajava.com.br/img/capa-livro.png");
				
				em.persist(produto);
				
				Produto produto2 = new Produto();
				produto2.setNome("Vire o jogo com Spring Framework");
				produto2.setLoja(loja1);
				produto2.setLinkDaFoto("http://cdn.shopify.com/s/files/1/0155/7645/products/spring-framework-featured_large.png?v=1411567960");
				
				em.persist(produto2);
			}
		});		
	}
	
}
