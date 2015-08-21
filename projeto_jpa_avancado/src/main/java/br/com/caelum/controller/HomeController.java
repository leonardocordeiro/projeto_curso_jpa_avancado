package br.com.caelum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.caelum.dao.CategoriaDao;
import br.com.caelum.dao.LojaDao;
import br.com.caelum.dao.ProdutoDao;
import br.com.caelum.model.Produto;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private LojaDao lojaDao;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}
	
	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping("/tenancy")
	public String home() {
		return "redirect:/";
	}
	
	@RequestMapping("/produto")
	public String novoProduto() { 
		return "novo_produto";
	}
	
	@RequestMapping("/produto/{id}")
	public String produto(@PathVariable Integer id, Model model) {
		Produto produto = produtoDao.getProduto(id);
		
		model.addAttribute("produto", produto);
		return "saber_mais";
	}	
	
	@RequestMapping(value="/produtos", method=RequestMethod.POST)
	public String produtos(Model model,
			@RequestParam String nome, 
			@RequestParam String categoria,
			@RequestParam Integer lojaId) {
		
		List<Produto> produtos = produtoDao.getProdutos(nome, categoria, lojaId);
		model.addAttribute("produtos", produtos);
		
		return "home";
		
	}
}
