package br.com.caelum.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.caelum.dao.LojaDao;
import br.com.caelum.dao.ProdutoDao;
import br.com.caelum.model.Loja;
import br.com.caelum.model.Produto;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private LojaDao lojaDao;
	
	@Transactional
	@RequestMapping(value="/produto", method=RequestMethod.POST)
	public String produto(Produto produto, Integer lojaId) {
		Loja loja = lojaDao.getLoja(lojaId);
		produto.setLoja(loja);
		
		produtoDao.insere(produto);
		
		return "redirect:/";
	}
	
}
