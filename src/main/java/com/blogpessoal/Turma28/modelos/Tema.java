package com.blogpessoal.Turma28.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe tema utilizada como Entidade para tema no Banco de dados
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
@Entity
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20, message = "Maximo 20 caracteres")
	private String tema;

	@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "tema" })
	private List<Postagem> listaDePostagens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<Postagem> getListaDePostagens() {
		return listaDePostagens;
	}

	public void setListaDePostagens(List<Postagem> listaDePostagens) {
		this.listaDePostagens = listaDePostagens;
	}

}
