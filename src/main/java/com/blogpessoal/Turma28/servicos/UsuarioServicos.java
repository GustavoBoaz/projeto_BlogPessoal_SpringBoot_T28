package com.blogpessoal.Turma28.servicos;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma28.modelos.Usuario;
import com.blogpessoal.Turma28.modelos.UsuarioDTO;
import com.blogpessoal.Turma28.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicos {

	private @Autowired UsuarioRepositorio repositorio;

	/**
	 * Método utilizado para cadastrar um usuario no banco de dados, o mesmo é
	 * responsavel por retornar vazio caso Usuario exista
	 * 
	 * @param novoUsuario do tipo Usuario
	 * @return Usuario Criado quando não existir no banco
	 * @since 1.0
	 * @author Turma 28
	 */
	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return repositorio.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaCriptografada);
			return Optional.ofNullable(repositorio.save(novoUsuario));
		});
	}

	/**
	 * Método utilizado para cadastrar um usuario no banco de dados, o mesmo é
	 * responsavel por retornar vazio caso Usuario exista
	 * 
	 * @param novoUsuario do tipo Usuario
	 * @return Usuario Criado quando não existir no banco
	 * @since 1.5
	 * @author Baby
	 */
	public Optional<Object> cadastrarUsuario2(Usuario novoUsuario) {
		Optional<Usuario> usuario = repositorio.findByEmail(novoUsuario.getEmail());
		if (usuario.isPresent()) {
			return Optional.empty();
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(senhaCriptografada);
			return Optional.ofNullable(repositorio.save(novoUsuario));
		}
	}

	/**
	 * Metodo utilizado para pegar credenciais do usuario com Tokem (Formato Basic),
	 * este método sera utilizado para retornar ao front o token utilizado para ter
	 * acesso aos dados do usuario e mantelo logado no sistema
	 * 
	 * @param usuarioParaAutenticar do tipo UsuarioLoginDTO necessario email e senha
	 *                              para validar
	 * @return UsuarioLoginDTO preenchido com informações mais o Token
	 * @since 1.0
	 * @author Turma 28
	 */
	public Optional<?> pegarCredenciais(UsuarioDTO usuarioParaAutenticar) {
		return repositorio.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {
				String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha(); // gustavoboaz@gmail.com:134652
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII"))); // hHJyigo-o+i7%0ÍUG465sas=-
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64); // Basic hHJyigo-o+i7%0ÍUG465sas=-

				usuarioParaAutenticar.setToken(autorizacaoHeader);
				usuarioParaAutenticar.setId(usuarioExistente.getId());
				usuarioParaAutenticar.setNome(usuarioExistente.getNome());
				usuarioParaAutenticar.setSenha(usuarioExistente.getSenha());
				return Optional.ofNullable(usuarioParaAutenticar);
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	/**
	 * Metodo utilizado para alterar um usuario fornecido pelo FRONT, O mesmo
	 * retorna um Optional com entidade Usuario dentro e senha criptografada. Caso
	 * falho retorna um Optional.empty()
	 * 
	 * @param usuarioParaAlterar do tipo Usuario
	 * @return Optional com Usuario Alterado
	 * @since 1.0
	 * @author Turma 28
	 */
	public Optional<?> alterarUsuario(UsuarioDTO usuarioParaAlterar) {
		return repositorio.findById(usuarioParaAlterar.getId()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());
			
			usuarioExistente.setNome(usuarioParaAlterar.getNome());
			usuarioExistente.setSenha(senhaCriptografada);
			return Optional.ofNullable(repositorio.save(usuarioExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
