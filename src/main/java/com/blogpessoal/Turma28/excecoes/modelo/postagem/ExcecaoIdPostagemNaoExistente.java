package com.blogpessoal.Turma28.excecoes.modelo.postagem;

/**
 * Classe reponsavel por construtor de excecao caso id de postagem seja invalido
 * no sistema
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
public class ExcecaoIdPostagemNaoExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoIdPostagemNaoExistente(Long idPostagem) {
		super("O Id " + idPostagem + " da postagem esta Invalido");
	}

}
