package br.com.caelum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.caelum.dao.LojaDao;
import br.com.caelum.dao.ProdutoDao;
import br.com.caelum.model.Produto;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private LojaDao lojaDao;
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST, name="cadastraProduto")
	public String salvar(@ModelAttribute @Valid Produto produto, BindingResult result) {
		if(result.hasErrors()) {
			return form(produto);
		}
		
		produtoDao.insere(produto);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form(Produto produto) { 
		return "produto/form";
	}
	
	@RequestMapping(value="/{id}/form", method=RequestMethod.GET)
	public String update(@PathVariable Integer id, Model model) {
		Produto produto = produtoDao.getProduto(id);
		
		model.addAttribute("produto", produto);
		return form(produto);
	}
	
	@RequestMapping("/{id}")
	public String detalhe(@PathVariable Integer id, Model model) {
		Produto produto = produtoDao.getProduto(id);
		
		model.addAttribute("produto", produto);
		return "produto/detalhe";
	}	
	
	@RequestMapping(value="/buscar", method=RequestMethod.POST, name="buscarProdutos")
	public String buscarPor(Model model,
			@RequestParam String nome, 
			@RequestParam String categoria,
			@RequestParam(required=false) Integer lojaId) {
		
		List<Produto> produtos = produtoDao.getProdutos(nome, categoria, lojaId);
		model.addAttribute("produtos", produtos);
		
		return "home";
		
	}
}
