package br.com.caelum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.model.Categoria;

@Repository
public class CategoriaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Categoria> getCategorias() { 
		return em
				.createQuery("from Categoria", Categoria.class)
				.getResultList();
	}
}
