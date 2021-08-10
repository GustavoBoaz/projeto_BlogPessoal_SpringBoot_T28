package com.blogpessoal.Turma28.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma28.modelos.Tema;
import com.blogpessoal.Turma28.repositorios.TemaRepositorio;

@Service
public class TemaServicos {

	private @Autowired TemaRepositorio repositorioT;

	/**
	 * Método utilizado para alterar um tema. O mesmo retorna um Optional com Tema
	 * caso correto ou um Optional.empyt() caso id do tema não exista.
	 * 
	 * @param temaParaAlterar do tipo Tema
	 * @return Optional com Tema alterado
	 * @since 1.0
	 * @author Turma 28
	 */
	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repositorioT.findById(temaParaAlterar.getId()).map(temaExistente -> {
			temaExistente.setTema(temaParaAlterar.getTema());
			return Optional.ofNullable(repositorioT.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
