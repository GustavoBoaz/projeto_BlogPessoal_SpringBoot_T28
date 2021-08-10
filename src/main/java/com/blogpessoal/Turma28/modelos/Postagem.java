package com.blogpessoal.Turma28.modelos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe postagem utilizada como Entidade para postagem no Banco de dados
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
@Entity
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 45, message = "Maximo 45 caracteres")
	private String titulo;

	@NotBlank
	@Size(max = 300, message = "Maximo 300 caracteres")
	private String descricao;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPostagem = LocalDate.now();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "minhasPostagens" })
	private Usuario criador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "listaDePostagens" })
	private Tema tema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(LocalDate dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
