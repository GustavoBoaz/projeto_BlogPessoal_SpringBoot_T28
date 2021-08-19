package com.blogpessoal.Turma28.excecoes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blogpessoal.Turma28.excecoes.modelo.postagem.ExcecaoIdPostagemNaoExistente;
import com.blogpessoal.Turma28.excecoes.modelo.postagem.ExcecaoIdUsuarioOuIdTemaNaoExistente;
import com.blogpessoal.Turma28.excecoes.modelo.tema.ExcecaoIdTemaNaoExiste;
import com.blogpessoal.Turma28.excecoes.modelo.usuario.ExcecaoEmailExistente;
import com.blogpessoal.Turma28.excecoes.modelo.usuario.ExcecaoErroEmailOuSenha;
import com.blogpessoal.Turma28.excecoes.modelo.usuario.ExcecaoIdUsuarioNaoExistente;

/**
 * Classe responsavel pelo gerenciamento das respostas caso erro em requisiçoes
 * 
 * @since 1.0
 * @author Turma 28
 *
 */
@ControllerAdvice
public class ManipuladorDeExcecoes {

	/**
	 * Método responsavel por alterar mensagem de erro do tipo response BAD_REQUEST
	 * tentativa de email cadastrado ja existe no sistema (400) para saída
	 * padronizada
	 *
	 * @param e do tipo ExcecaoEmailExistente
	 * @return Map com resposta padrão
	 */
	@ExceptionHandler(ExcecaoEmailExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> emailExistenteNotFoundHandler(ExcecaoEmailExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Tente um email diferente!.");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Método responsavel por retornar excecao caso ou Email ou senha esteja errada
	 * na solicitação dos dados de credenciais do usuario
	 * 
	 * @param e do tipo ExcecaoErroEmailOuSenha
	 * @return Map com resposta padrão
	 */
	@ExceptionHandler(ExcecaoErroEmailOuSenha.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> emailOuSenhaInvalidaNotFoundHandler(ExcecaoErroEmailOuSenha e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Entre com credenciais validas ou faça o cadastro!.");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Método responsavel por retornar excecao caso o id do usuario não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdUsuarioNaoExistente
	 * @return Map com resposta padrão
	 */
	@ExceptionHandler(ExcecaoIdUsuarioNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idUsuarioInvalidoNotFoundHandler(ExcecaoIdUsuarioNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Passe o Id do usuario como parametro!.");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Método responsavel por retornar excecao caso o id do tema não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdTemaNaoExiste
	 * @return Map com resposta padrão
	 */
	@ExceptionHandler(ExcecaoIdTemaNaoExiste.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idTemaInvalidoNotFoundHandler(ExcecaoIdTemaNaoExiste e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Passe o Id do tema como parametro!.");
		response.put("problema", e.getMessage());

		return response;
	}

	/**
	 * Método responsavel por retornar excecao caso o id do usuario ou tema não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdTemaNaoExiste
	 * @return Map com resposta padrão
	 */
	@ExceptionHandler(ExcecaoIdUsuarioOuIdTemaNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idTemaInvalidoNotFoundHandler(ExcecaoIdUsuarioOuIdTemaNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Passe o Id do usuario e do tema como parametro!.");
		response.put("problema", e.getMessage());

		return response;
	}
	
	/**
	 * Método responsavel por retornar excecao caso o id da postagem não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdPostagemNaoExistente
	 * @return Map com resposta padrão
	 */
	@ExceptionHandler(ExcecaoIdPostagemNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idPostagemInvalidoNotFoundHandler(ExcecaoIdPostagemNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "400");
		response.put("mensagem", "Passe o Id da postagem como parametro!.");
		response.put("problema", e.getMessage());

		return response;
	}

}
