package br.com.caelum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.com.caelum.model.Loja;
import br.com.caelum.model.Produto;

@Repository
public class ProdutoDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Produto> getProdutos() {
		Session session = (Session) em.getDelegate();
		
		return session.createQuery("from Produto p").list();
		
	}

	public Produto getProduto(Integer id) {
		Produto produto = em.find(Produto.class, id);
		return produto;
	}

	public List<Produto> getProdutos(String nome, String categoria, Integer lojaId) {
		// Começar com JPQL
		/*

		// String jpql = "select p from Produto p ";
		//
		// StringBuilder builder = new StringBuilder(jpql);
		//
//		 if (!categoria.isEmpty())
//		 builder.append("join fetch p.categorias c where c.nome = :pCategoria and ");
//		 else
//		 builder.append("where ");
//		
		// if (!loja.isEmpty())
		// builder.append("p.loja.nome = :pLoja and ");
		// if (!nome.isEmpty())
		// builder.append("p.nome like :pNome and ");
		//
		// builder.append("1=1");
		// jpql = builder.toString();
		//
		// TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		//
		// if (!categoria.isEmpty())
		// query.setParameter("pCategoria", categoria);
		// if (!loja.isEmpty())
		// query.setParameter("pLoja", loja);
		// if (!nome.isEmpty())
		// query.setParameter("pNome", nome);
		//
		// List<Produto> resultList = query.getResultList();
		//
		// return resultList;
		/*
		 * select Produto.nome from Produto, Categoria, Produto_Categoria where
		 * Categoria.nome = "Música" and Produto_Id = Produto.id and
		 * Categoria.id = categorias_id;
		 */

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> produtoRoot = query.from(Produto.class);

		Predicate conjuncao = builder.conjunction();

		if (!nome.isEmpty()) {
			Path<String> nomeProduto = produtoRoot.<String> get("nome");
			conjuncao = builder.and(builder.like(nomeProduto, "%" + nome + "%"));
		}

		if (!categoria.isEmpty()) {
			Join<Produto, List<String>> join = produtoRoot.join("categorias");
			Path<String> categoriaProduto = join.get("nome");

			conjuncao = builder.and(conjuncao,
			builder.equal(categoriaProduto, categoria));
		}

		if (lojaId != null) {
			Path<Integer> nomeLoja = produtoRoot.<Loja> get("loja")
											   .<Integer> get("id");
			
			conjuncao = builder.and(conjuncao, builder.equal(nomeLoja, lojaId));
		}

		return em.createQuery(query.where(conjuncao)).getResultList();
	}

	public void insere(Produto produto) {
		System.out.println("ID: " + produto.getId());
		System.out.println("ID: " + produto.getLoja().getId());
		if(produto.getId() == null) em.persist(produto);
		else produto = em.merge(produto);
	}

}
