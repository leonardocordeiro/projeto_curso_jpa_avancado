package br.com.caelum;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.caelum.dao.Dao;
import br.com.caelum.model.Produto;

public class TesteCache {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfigurator.class);
		
		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean(EntityManagerFactory.class);
		
		EntityManager em = emf.createEntityManager();
		EntityManager em2 = emf.createEntityManager(); // criando o segundo EntityManager

		List<Produto> produtos = (List<Produto>) new Dao(em).listar(Produto.class);
		
//		Produto produto = em.find(Produto.class, 1);
//		System.out.println("Nome: " + produto.getNome());
//		
//		Produto outroProduto = em2.find(Produto.class, 1); // buscando o mesmo produtu com o segundo EntityManager
//		System.out.println("Nome: " + outroProduto.getNome());
//		
	}
}
