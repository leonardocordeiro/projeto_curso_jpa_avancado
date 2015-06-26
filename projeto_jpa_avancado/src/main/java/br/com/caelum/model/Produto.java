package br.com.caelum.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;

@Filters({
	@Filter(name="Casa do Código", condition="loja_id=1"),
	@Filter(name="Musical Alegre", condition="loja_id=2")
})

@FilterDefs({
	@FilterDef(name="Casa do Código"),
	@FilterDef(name="Musical Alegre")
})

@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String linkDaFoto;
	
	@Column(columnDefinition="TEXT")
	private String descricao;
	
	private double preco;
	
	@ManyToOne
	private Loja loja;
	
	@ManyToMany
	private List<Categoria> categorias = new ArrayList<>();
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void adicionarCategorias(Categoria... categorias) {
		for (Categoria categoria : categorias) {
			this.categorias.add(categoria);
		}
	}

	public String getLinkDaFoto() {
		return linkDaFoto;
	}
	
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setLinkDaFoto(String linkDaFoto) {
		this.linkDaFoto = linkDaFoto;
	}

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
