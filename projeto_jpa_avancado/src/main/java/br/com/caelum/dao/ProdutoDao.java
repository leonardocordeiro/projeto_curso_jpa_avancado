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
		return em.createQuery("from Produto", Produto.class).getResultList();
	}

	public Produto getProduto(Integer id) {
		Produto produto = em.find(Produto.class, id);
		return produto;
	}

	public List<Produto> getProdutos(String nome, Integer categoriaId, Integer lojaId) {
		// Começar com JPQL

		 String jpql = "select p from Produto p ";
		
		
		 if (categoriaId != null)
		 jpql += "join fetch p.categorias c where c.id = :pCategoria and ";
		 else
		 jpql += "where ";
		
		 if (lojaId != null)
		 jpql += "p.loja.id = :pLoja and ";
		 if (!nome.isEmpty())
		 jpql += "p.nome like :pNome and ";
		
		 jpql += "1=1";
		
		 TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		
		 if (categoriaId != null)
		 query.setParameter("pCategoria", categoriaId);
		 if (lojaId != null)
		 query.setParameter("pLoja", lojaId);
		 if (!nome.isEmpty())
		 query.setParameter("pNome", nome);
		
		 List<Produto> resultList = query.getResultList();
		
		 return resultList;
		/*
		 * select Produto.nome from Produto, Categoria, Produto_Categoria where
		 * Categoria.nome = "Música" and Produto_Id = Produto.id and
		 * Categoria.id = categorias_id;
		 */
//
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
//		Root<Produto> produtoRoot = query.from(Produto.class);
//
//		Predicate conjuncao = builder.conjunction();
//
//		if (!nome.isEmpty()) {
//			Path<String> nomeProduto = produtoRoot.<String> get("nome");
//			Predicate nomeIgual = builder.like(nomeProduto, "%" + nome + "%");
//			conjuncao = builder.and(nomeIgual);
//			
//		}
//
//		if (categoriaId != null) {
//			Join<Produto, List<Categoria>> join = produtoRoot.join("categorias");
//			Path<Integer> categoriaProduto = join.get("id");
//
//			conjuncao = builder.and(conjuncao,
//			builder.equal(categoriaProduto, categoriaId));
//		}
//
//		if (lojaId != null) {
//			Path<Loja> loja = produtoRoot.<Loja> get("loja");
//			Path<Integer> id = loja.<Integer> get("id");
//
//			conjuncao = builder.and(conjuncao, builder.equal(id, lojaId));
//		}
//
//		return em.createQuery(query.where(conjuncao)).getResultList();
	}

	public void insere(Produto produto) {
		if(produto.getId() == null) em.persist(produto);
		else em.merge(produto);
	}

}
