package br.com.caelum.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@JoinColumn(unique = true)
	@OneToOne(cascade = CascadeType.PERSIST)
	private Loja loja;

	@ManyToMany
	@OneToOne(cascade = CascadeType.PERSIST)
	private List<Categoria> categorias = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Loja getLoja() {
		return loja;
	}

}
