package br.com.faceCookie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.faceCookie.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<?> getUsuarioId(@PathVariable(name = "usuarioId", required = true) Long usuarioId) {
		return ResponseEntity.ok(usuarioService.pegarUsuarioById(usuarioId));
	}
}
