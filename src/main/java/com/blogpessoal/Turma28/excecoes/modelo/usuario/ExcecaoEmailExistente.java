package com.blogpessoal.Turma28.excecoes.modelo.usuario;

/**
 * Classe reponsavel por construtor de excecao caso email seja existente no
 * cadastro de um novo usuario
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
public class ExcecaoEmailExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoEmailExistente(String emailExistente) {
		super("Email " + emailExistente + " já existente!.");
	}
}
