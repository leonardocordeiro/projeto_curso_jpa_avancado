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
import br.com.caelum.model.Loja;
import br.com.caelum.model.Produto;

@Controller
@RequestMapping("/")
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
	
	@RequestMapping
	public String home(Model model, @RequestParam(required=false) String tenancy) {
		return "home";
	}
		
}
