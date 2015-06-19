package br.com.caelum.controller;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.dao.ProdutoDao;
import br.com.caelum.model.Produto;

@Controller
public class HomeController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ProdutoDao produtoDao;
	
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Produto> produtos = produtoDao.getProdutos();
		
		
		model.addAttribute("produtos", produtos);
		return "home";
	}
	
	@RequestMapping("/produto/{id}")
	public String produto(@PathVariable Integer id, Model model) {
		
		Produto produto = produtoDao.getProduto(id);
		
		model.addAttribute("produto", produto);
		return "saber_mais";
	}	
}
