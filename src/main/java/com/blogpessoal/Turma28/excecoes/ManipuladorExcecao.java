package com.blogpessoal.Turma28.excecoes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ManipuladorExcecao {

	/**
	 * Método responsavel por alterar mensagem de erro do tipo response BAD_REQUEST
	 * (400) para saída padronizada
	 * 
	 * @param e do tipo Exception
	 * @return Json padão com mensagem customizada
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> mostrarMensagemCustomizada(Exception e) {
		Map<String, String> response = new HashMap<>();
		response.put("status", "Entrada de dados invalida");

		return response;
	}
}
