package com.blogpessoal.Turma28.excecoes.modelo.usuario;

/**
 * Classe reponsavel por construtor de excecao caso id de usuario seja invalido
 * no sistema
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
public class ExcecaoIdUsuarioNaoExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoIdUsuarioNaoExistente(Long idUsuario) {
		super("Id " + idUsuario + " não existente!.");
	}

}
