package br.com.caelum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.caelum.model.Categoria;
import br.com.caelum.model.Loja;
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
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> produtoRoot = query.from(Produto.class);

		Predicate conjuncao = builder.conjunction();

		if (!nome.isEmpty()) {
			Path<String> nomeProduto = produtoRoot.<String> get("nome");
			Predicate nomeIgual = builder.like(nomeProduto, "%" + nome + "%");
			conjuncao = builder.and(nomeIgual);

		}

		if (categoriaId != null) {
			Join<Produto, List<Categoria>> join = produtoRoot
					.join("categorias");
			Path<Integer> categoriaProduto = join.get("id");

			conjuncao = builder.and(conjuncao,
					builder.equal(categoriaProduto, categoriaId));
		}

		if (lojaId != null) {
			Path<Loja> loja = produtoRoot.<Loja> get("loja");
			Path<Integer> id = loja.<Integer> get("id");

			conjuncao = builder.and(conjuncao, builder.equal(id, lojaId));
		}

		TypedQuery<Produto> typedQuery = em.createQuery(query.where(conjuncao));
		typedQuery.setHint("org.hibernate.cacheable", "true");
		
		return typedQuery.getResultList();

	}

	public void insere(Produto produto) {
		if (produto.getId() == null)
			em.persist(produto);
		else
			em.merge(produto);
	}

}
