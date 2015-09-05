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
		long transacoesAbertas = statistics.getTransactionCount();
		long transacoesFechadas = statistics.getSuccessfulTransactionCount();
		
		long conexoesAbertas = statistics.getConnectCount();
		long conexoesFechadas = statistics.getFlushCount();
		
		model.addAttribute("transacoesAbertas", transacoesAbertas);
		model.addAttribute("transacoesFechadas", transacoesFechadas);
		
		model.addAttribute("conexoesAbertas", conexoesAbertas);
		model.addAttribute("conexoesFechadas", conexoesFechadas);
		
		return "estatisticas/index";
	}
	
	@RequestMapping("/limpar")
	public String invalidar() {
		statistics.clear();
		
		return "redirect:/estatisticas"; 
	}
}
