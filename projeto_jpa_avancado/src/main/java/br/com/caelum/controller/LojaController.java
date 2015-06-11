package br.com.caelum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loja")
public class LojaController {
	
	@RequestMapping("/cadastro")
	public String cadastro() { 
		return "cadastro_loja";
	}
}
