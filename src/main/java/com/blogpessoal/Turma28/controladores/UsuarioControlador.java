package com.blogpessoal.Turma28.controladores;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.Turma28.modelos.Usuario;
import com.blogpessoal.Turma28.repositorios.UsuarioRepositorio;


@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioControlador {

	@Autowired
	private UsuarioRepositorio repositorio;	
	
	@GetMapping("/todes")
	public ResponseEntity<Object> buscarTodes(){
		List<Usuario> listaUsuarios = repositorio.findAll();
		if (listaUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaUsuarios);
		}
	}
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable(value = "id_usuario") Long id){
		return ResponseEntity.status(200).body(repositorio.findById(id).get());
	}
	
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(defaultValue = "") String nome){	
		return ResponseEntity.status(200).body(repositorio.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario novoUsuario){
		return ResponseEntity.status(201).body(repositorio.save(novoUsuario));
	}
	
	@DeleteMapping("/deletar/{idUsuario}")
	public ResponseEntity<String> deletarPorId(@PathVariable Long idUsuario) {
		Optional<Usuario> usuarioExistente = repositorio.findById(idUsuario);
		if (usuarioExistente.isPresent()) {
			repositorio.deleteById(idUsuario);
			return ResponseEntity.status(200).body("Usuario Deletado!");
		} else {
			return ResponseEntity.status(200).body("Usuario não Existe!");
		}
	}
	
}
