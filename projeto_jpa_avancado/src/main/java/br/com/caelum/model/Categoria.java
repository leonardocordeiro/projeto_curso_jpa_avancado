package br.com.caelum.model;

import javax.persistence.Embeddable;

@Embeddable
public class Categoria {
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
