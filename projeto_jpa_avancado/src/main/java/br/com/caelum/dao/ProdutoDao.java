package br.com.caelum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		
		return produtos;
		
	}

	public Produto getProduto(Integer id) {
		
		Produto produto = em.find(Produto.class, id);
		return produto;
	}

	public List<Produto> getProdutos(String nome, String categoria, String loja) {
		
		TypedQuery<Produto> query = em.createQuery("select p from Produto p join p.categorias ", Produto.class);
		
		
		/*
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate conjuncao = builder.conjunction();
		
		if(!nome.isEmpty()) { 
			Path<String> nomeProduto = from.<String>get("nome");
			conjuncao = builder.and(builder.equal(nomeProduto, nome));
		}
		
		if(!categoria.isEmpty()) { 
			Path<List<String>> categoriaProduto = from.<List<String>>get("categorias");
			//conjuncao = builder.and(conjuncao, builder.like(categoriaProduto, "a"));
		}
		
		if(!loja.isEmpty()) { 
			Path<String> nomeLoja = from.<String>get("loja.nome");
			conjuncao = builder.and(conjuncao, builder.equal(nomeLoja, loja));
		}
		
		return em.createQuery(query.where(conjuncao)).getResultList();
		*/
	}	
}
