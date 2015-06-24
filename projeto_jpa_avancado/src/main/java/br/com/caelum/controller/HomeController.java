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
import br.com.caelum.model.Categoria;
import br.com.caelum.model.Loja;
import br.com.caelum.model.Produto;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private LojaDao lojaDao;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Produto> produtos = produtoDao.getProdutos();
		List<Loja> lojas = lojaDao.getLojas();
		List<Categoria> categorias = categoriaDao.getCategorias();
		
		model.addAttribute("produtos", produtos);
		model.addAttribute("lojas", lojas);
		model.addAttribute("categorias", categorias);
		
		return "home";
	}
	
	@RequestMapping("/produto/{id}")
	public String produto(@PathVariable Integer id, Model model) {
		
		Produto produto = produtoDao.getProduto(id);
		List<Loja> lojas = lojaDao.getLojas();
		List<Categoria> categorias = categoriaDao.getCategorias();
		
		model.addAttribute("produto", produto);
		model.addAttribute("lojas", lojas);
		model.addAttribute("categorias", categorias);
		
		return "saber_mais";
	}	
	
	@RequestMapping(value="/produtos", method=RequestMethod.POST)
	public String produtos(Model model,
			@RequestParam String nome, 
			@RequestParam String categoria,
			@RequestParam String loja) {
		
		List<Produto> produtos = produtoDao.getProdutos(nome, categoria, loja);
		List<Loja> lojas = lojaDao.getLojas();
		List<Categoria> categorias = categoriaDao.getCategorias();
		
		model.addAttribute("produtos", produtos);
		model.addAttribute("lojas", lojas);
		model.addAttribute("categorias", categorias);		
		return "home";
		
	}
}
