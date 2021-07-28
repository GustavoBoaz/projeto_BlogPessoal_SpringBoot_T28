package com.blogpessoal.Turma28.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.Turma28.modelos.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	/**
	 * Metodo utilizado para pesquisar coluna nome da tabela Usuario
	 * 
	 * @param nome
	 * @return Lista de Usuario
	 * @since 1.0
	 * @author Turma28
	 */
	List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
}
