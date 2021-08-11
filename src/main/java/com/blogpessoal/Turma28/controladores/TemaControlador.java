package com.blogpessoal.Turma28.controladores;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.Turma28.modelos.Tema;
import com.blogpessoal.Turma28.repositorios.TemaRepositorio;
import com.blogpessoal.Turma28.servicos.TemaServicos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/tema")
@Api(tags = "Controlador de Tema", description = "Utilitario de Temas")
@CrossOrigin("*")
public class TemaControlador {

	private @Autowired TemaRepositorio repositorio;
	private @Autowired TemaServicos servicos;

	@ApiOperation(value = "Salva novo tema no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna tema cadastrado")
	})
	@PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarPostagem(@Valid @RequestBody Tema novoTema) {
		return ResponseEntity.status(201).body(repositorio.save(novoTema));
	}

	@ApiOperation(value = "Busca lista de temas no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de temas"),
			@ApiResponse(code = 204, message = "Retorno sem tema")
	})
	@GetMapping("/todos")
	public ResponseEntity<Object> buscarTodos() {
		List<Tema> listaTema = repositorio.findAll();

		if (listaTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaTema);
		}

	}

	@ApiOperation(value = "Busca tema por Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna tema existente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/{id_tema}")
	public ResponseEntity<Tema> buscarPorId(@PathVariable(value = "id_tema") Long id) {
		Optional<Tema> objetoTema = repositorio.findById(id);
		if (objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@ApiOperation(value = "Busca tema por nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna tema existente ou inexistente")
	})
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Tema>> buscarPorTema(@RequestParam(defaultValue = "") String tema) {
		return ResponseEntity.status(200).body(repositorio.findAllByTemaContainingIgnoreCase(tema));
	}

	@ApiOperation(value = "Alterar tema existente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna tema alterado"),
			@ApiResponse(code = 400, message = "Id de tema invalido")
	})
	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Tema temaParaAlterar) {
		Optional<Tema> objetoAlterado = servicos.alterarTema(temaParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	@ApiOperation(value = "Deletar tema existente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Caso deletado!"),
			@ApiResponse(code = 400, message = "Id de usuario invalido")
	})
	@DeleteMapping("/deletar/{id_tema}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<Tema> objetoExistente = repositorio.findById(idTema);
		if (objetoExistente.isPresent()) {
			repositorio.deleteById(idTema);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}

}
