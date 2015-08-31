package br.com.caelum.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.caelum.dao.ProdutoDao;
import br.com.caelum.model.Produto;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public String produto(Produto produto, Integer lojaId) {
		System.out.println(produto.getNome());
		produtoDao.insere(produto);
		
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String novoProduto() { 
		return "novo_produto";
	}
	
	@RequestMapping("/{id}")
	public String produto(@PathVariable Integer id, Model model) {
		Produto produto = produtoDao.getProduto(id);
		
		model.addAttribute("produto", produto);
		return "saber_mais";
	}	
	
	@RequestMapping(value="/produtos", method=RequestMethod.POST)
	public String produtos(Model model,
			@RequestParam String nome, 
			@RequestParam String categoria,
			@RequestParam(required=false) Integer lojaId) {
		
		List<Produto> produtos = produtoDao.getProdutos(nome, categoria, lojaId);
		model.addAttribute("produtos", produtos);
		
		return "home";
		
	}
	
}
