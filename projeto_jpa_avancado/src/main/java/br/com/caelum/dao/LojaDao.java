package br.com.caelum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.caelum.model.Loja;

@Repository
public class LojaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Loja> getLojas() { 
		TypedQuery<Loja> query = em.createQuery("from Loja", Loja.class);
		query.setHint("org.hibernate.cacheable", "true");
		
		return query.getResultList();
	}

	public Loja getLoja(Integer lojaId) {
		TypedQuery<Loja> query = em.createQuery("from Loja loja where loja.id=:id", Loja.class);
		query.setParameter("id", lojaId);

		return query.getSingleResult();
	}

}
