package br.com.faceCookie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.faceCookie.model.Usuario;
import br.com.faceCookie.repository.UsuarioRepository;
import lombok.NonNull;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	/**
	 * Verificação da fila
	 * 
	 * @param macAddress
	 * @param ipAddress
	 * @param url
	 * @param identifcadorFila
	 * @return
	 */
	public Usuario pegarUsuarioById (@NonNull Long Id) {
		
		Optional<Usuario> optUsuario = usuarioRepository.findById(Id);
		
		if (optUsuario.isEmpty()) {
			return null;
		}
		
		return optUsuario.get();
	}
}
