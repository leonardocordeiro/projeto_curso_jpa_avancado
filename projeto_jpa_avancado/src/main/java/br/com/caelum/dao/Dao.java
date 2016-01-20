package br.com.caelum.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.model.Produto;

public class Dao {
	
	private EntityManager em;
	
	public Dao(EntityManager em) {
		this.em = em;
	}

	public List<? extends Object> listar(Class<?> clazz) { 
		return em.createQuery("from " + clazz.getName(), clazz).getResultList();
	}
	
	public void salvar(Object object) { 
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
	}
	
	public void remover(Produto produto) {
		em.getTransaction().begin();
		em.remove(produto);
		em.getTransaction().commit();
	}

}
