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

		List<Produto> produtos = em.createQuery("from Produto", Produto.class)
								   .getResultList();

		return produtos;

	}

	public Produto getProduto(Integer id) {

		Produto produto = em.find(Produto.class, id);
		return produto;
	}

	public List<Produto> getProdutos(String nome, String categoria, String loja) {
		String jpql = "select p from Produto p ";
		
		StringBuilder builder = new StringBuilder(jpql);
		
		if (!categoria.isEmpty()) 
			builder.append("join fetch p.categorias c where c.nome = :pCategoria and ");
		else
			builder.append("where ");
		
		if (!loja.isEmpty()) 
			builder.append("p.loja = :pLoja and ");
		if (!nome.isEmpty()) 
			builder.append("p.nome like :pNome and ");

		builder.append("1=1");
		jpql = builder.toString();

		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

		if (!categoria.isEmpty())
			query.setParameter("pCategoria", categoria);
		if (!loja.isEmpty())
			query.setParameter("pLoja", loja);
		if (!nome.isEmpty()) 
			query.setParameter("pNome", nome);

		List<Produto> resultList = query.getResultList();

		return resultList;

			// select Produto.nome from Produto, Categoria, Produto_Categoria
			// where Categoria.nome = "Música"
			// and Produto_Id = Produto.id and Categoria.id = categorias_id;

			// CriteriaBuilder builder = em.getCriteriaBuilder();
			// CriteriaQuery<Produto> query =
			// builder.createQuery(Produto.class);
			// Root<Produto> produtoRoot = query.from(Produto.class);
			// Root<Categoria> categoriaRoot = query.from(Categoria.class);
			//
			// Predicate conjuncao = builder.conjunction();
			//
			// if(!nome.isEmpty()) {
			// Path<String> nomeProduto = produtoRoot.<String>get("nome");
			// conjuncao = builder.and(builder.equal(nomeProduto, nome));
			// }
			//
			// if(!categoria.isEmpty()) {
			// Path<List<String>> categoriaProduto =
			// produtoRoot.<List<String>>get("categorias");
			// //conjuncao = builder.and(conjuncao,
			// builder.like(categoriaProduto, "a"));
			// }
			//
			// if(!loja.isEmpty()) {
			// Path<String> nomeLoja = produtoRoot.<String>get("loja.nome");
			// conjuncao = builder.and(conjuncao, builder.equal(nomeLoja,
			// loja));
			// }
			//
			// return em.createQuery(query.where(conjuncao)).getResultList();
	}
}
