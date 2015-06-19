package br.com.caelum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.model.Produto;

@Repository
public class ProdutoDao {
	
	@PersistenceContext 
	private EntityManager em;
	
	public List<Produto> getProdutos() { 
		
		List<Produto> produtos = em
				.createQuery("from Produto", Produto.class)
				.getResultList();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		return produtos;
		
	}

	public Produto getProduto(Integer id) {
		
		Produto produto = em.find(Produto.class, id);
		return produto;
	}	
}
