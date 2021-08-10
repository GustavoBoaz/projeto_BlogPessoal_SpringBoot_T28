package com.blogpessoal.Turma28.modelos.utilidades;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Classe DTO modelo para Logar e Alter usuario - Para loguin: necessario apenas
 * email e senha como atributos; - Para Alterar: necessario o id para alterar
 * nome e senha; - nota: email é um atributo que não pode ser alterado
 * 
 * @since 1.5
 * @author Turma 28
 *
 */
public class UsuarioDTO {

	private Long id;

	private String nome;

	@NotBlank(message = "Necessario Email")
	@Email(message = "Necessario Email")
	private String email;

	@Size(min = 5, max = 100, message = "Necessario min 5 caracteres")
	private String senha;

	private String token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
