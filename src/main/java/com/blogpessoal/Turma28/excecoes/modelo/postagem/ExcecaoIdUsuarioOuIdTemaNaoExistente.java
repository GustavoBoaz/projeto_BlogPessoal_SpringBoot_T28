package com.blogpessoal.Turma28.excecoes.modelo.postagem;

/**
 * Classe reponsavel por construtor de excecao caso id Usuario ou de tema seja
 * invalido no sistema
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
public class ExcecaoIdUsuarioOuIdTemaNaoExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoIdUsuarioOuIdTemaNaoExistente() {
		super("O Id do Usuario ou do Tema esta Invalido");
	}
}
