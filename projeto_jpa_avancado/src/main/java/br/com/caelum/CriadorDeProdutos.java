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

				Produto livroDeArquitetura = new Produto();
				livroDeArquitetura.setNome("Introduçao a Arquitetura Java e Design de projetos com Java");
				livroDeArquitetura.setLoja(loja1);
				livroDeArquitetura.setPreco(30.0);
				livroDeArquitetura.setDescricao("Neste livro, os autores, conhecidos especialistas da "
											   + " área, apresentam muitos tópicos que aparecem com frequência"
											   + " na plataforma Java, incluindo desde modelagem e design das "
											   + "classes, até detalhes importantes das tecnologias mais utilizadas."
											   + "  Sempre com uma visão técnica e prática capaz de elucidar muitas "
											   + "questões enfrentadas tanto pelo profissional iniciante quanto por "
											   + "aquele que está adquirindo mais experiência na plataforma.");
				
				livroDeArquitetura.setLinkDaFoto("http://www.arquiteturajava.com.br/img/capa-livro.png");
				
				em.persist(livroDeArquitetura);
				
				Produto livroDeSpring = new Produto();
				livroDeSpring.setNome("Vire o jogo com Spring Framework");
				livroDeSpring.setLoja(loja1);
				livroDeSpring.setPreco(30.0);
				livroDeSpring.setDescricao("Criado para simplificar o desenvolvimento de aplicações Java, "
						+ "o Spring se tornou um dos frameworks de mais destaque dentro desse grande ambiente.  "
						+ "Aprenda muito mais que o básico do Spring, desde o tradicional Container de Inversão "
						+ "de Controle e Injeção de Dependências, passando pelos robustos módulos de segurança, "
						+ "transações, programação orientada a aspectos e também o fantástico módulo MVC, o SpringMVC.");
				
				livroDeSpring.setLinkDaFoto("http://cdn.shopify.com/s/files/1/0155/7645/products/spring-framework-featured_large.png?v=1411567960");
				
				em.persist(livroDeSpring);
				
				Produto violao = new Produto();
				violao.setNome("Violão");
				violao.setLoja(loja2);
				violao.setDescricao("Excelente violão");
				violao.setPreco(500.0);
				violao.setLinkDaFoto("http://www.marillac.g12.br/imgs/atividade%20complementar/violao.jpg");
				
				em.persist(violao);
				
				Produto flauta = new Produto();
				flauta.setNome("Flauta Doce");
				flauta.setLoja(loja2);
				flauta.setDescricao("Flauta doce");
				flauta.setPreco(300.0);
				flauta.setLinkDaFoto("http://i.mlcdn.com.br/1500x1500/flauta-doce-germanicayamaha-yrs-23g-204013000.jpg");
				
				em.persist(flauta);
				
			}
		});		
	}
	
}
