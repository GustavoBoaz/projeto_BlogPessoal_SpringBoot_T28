package com.blogpessoal.Turma28.excecoes.modelo.usuario;

/**
 * Classe reponsavel por construtor de excecao caso email e senha seja invalido
 * ao tentar recuperar credenciais do usuario
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
public class ExcecaoErroEmailOuSenha extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoErroEmailOuSenha() {
		super("Email ou senha invalida!.");
	}

}
