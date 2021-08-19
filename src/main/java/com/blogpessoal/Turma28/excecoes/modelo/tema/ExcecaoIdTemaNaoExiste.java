package com.blogpessoal.Turma28.excecoes.modelo.tema;

/**
 * Classe reponsavel por construtor de excecao caso id de tema seja invalido no
 * sistema
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
public class ExcecaoIdTemaNaoExiste extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoIdTemaNaoExiste(Long idTema) {
		super("Id " + idTema + " não existente!.");
	}

}
