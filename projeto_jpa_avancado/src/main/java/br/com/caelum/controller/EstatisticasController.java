package br.com.caelum.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estatisticas")
public class EstatisticasController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private Statistics statistics;

	@RequestMapping
	public String index(Model model) {
		long hit = statistics.getSecondLevelCacheHitCount();
		long miss = statistics.getSecondLevelCacheMissCount();
		
		model.addAttribute("hit", hit);
		model.addAttribute("miss", miss);
		
		return "estatisticas/index";
	}
	
	@RequestMapping("/invalidar-cache")
	public String invalidar(Model model) {
		em.getEntityManagerFactory().getCache().evictAll();
		statistics.clear();
		
		return index(model);
		
	}
	
	
}
