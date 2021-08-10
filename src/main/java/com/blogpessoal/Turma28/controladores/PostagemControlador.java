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

import com.blogpessoal.Turma28.modelos.Postagem;
import com.blogpessoal.Turma28.repositorios.PostagemRepositorio;
import com.blogpessoal.Turma28.servicos.PostagemServicos;

@RestController
@RequestMapping("/api/v1/postagem")
@CrossOrigin("*")
public class PostagemControlador {

	private @Autowired PostagemRepositorio repositorio;
	private @Autowired PostagemServicos servicos;

	@PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarPostagem(@Valid @RequestBody Postagem novaPostagem) {
		Optional<Postagem> objetoCadastrado = servicos.cadastrarPostagem(novaPostagem);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}

	@GetMapping("/todas")
	public ResponseEntity<Object> buscarTodas() {
		List<Postagem> listaPostagem = repositorio.findAll();

		if (listaPostagem.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaPostagem);
		}

	}
	
	@GetMapping("/{id_postagem}")
	public ResponseEntity<Postagem> buscarPorId(@PathVariable(value = "id_postagem") Long id) {
		Optional<Postagem> objetoPostagem = repositorio.findById(id);
		if (objetoPostagem.isPresent()) {
			return ResponseEntity.status(200).body(objetoPostagem.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Postagem>> buscarPorTitulo(@RequestParam(defaultValue = "") String titulo) {
		return ResponseEntity.status(200).body(repositorio.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Postagem postagemParaAlterar) {
		Optional<Postagem> objetoAlterado = servicos.alterarPostagem(postagemParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	
	@DeleteMapping("/deletar/{id_postagem}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoExistente = repositorio.findById(idPostagem);
		if (objetoExistente.isPresent()) {
			repositorio.deleteById(idPostagem);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}
		
	}

}
