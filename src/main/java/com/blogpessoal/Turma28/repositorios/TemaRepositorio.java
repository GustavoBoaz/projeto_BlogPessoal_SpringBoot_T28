package com.blogpessoal.Turma28.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.Turma28.modelos.Tema;

@Repository
public interface TemaRepositorio extends JpaRepository<Tema, Long> {
	
	/**
	 * Metodo utilizado para pesquisar coluna tema da tabela tema
	 * 
	 * @param tema
	 * @return Lista de Tema
	 * @since 1.0
	 * @author Turma28
	 */
	List<Tema> findAllByTemaContainingIgnoreCase(String tema);
}
