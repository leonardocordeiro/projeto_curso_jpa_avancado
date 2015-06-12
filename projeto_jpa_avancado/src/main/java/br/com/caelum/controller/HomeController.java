package br.com.caelum.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.model.Produto;

@Controller
public class HomeController {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@RequestMapping("/")
	public String home(Model model) {
		List<Produto> produtos = em.createQuery("from Produto", Produto.class).getResultList();
		model.addAttribute("produtos", produtos);
		return "home";
	}
	
	
}
