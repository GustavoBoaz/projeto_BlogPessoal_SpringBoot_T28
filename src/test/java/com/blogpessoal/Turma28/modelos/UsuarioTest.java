package com.blogpessoal.Turma28.modelos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTest {
	
	public Usuario usuario;
	public Usuario usuarioNulo = new Usuario();
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	Validator validador = factory.getValidator();

	@BeforeEach
	void start() {
		usuario = new Usuario(0L, "Camila Boaz", "camila@email.com", "134652");
	}
	
	@Test
	@DisplayName("✔ Valida atributos não Nulos")
	void validaUsuarioComAtributosNaoRetornaErro() {
		Set<ConstraintViolation<Usuario>> violacao = validador.validate(usuario);
		System.out.println(violacao.toString());
		
		assertTrue(violacao.isEmpty());

	}
	
	@Test
	@DisplayName("❌ Não Valida atributos Nulos")
	void validaUsuarioComAtributosRetornaErro() {
		Set<ConstraintViolation<Usuario>> violacao = validador.validate(usuarioNulo);
		System.out.println(violacao.toString());
		
		assertTrue(violacao.isEmpty());

	}

}
