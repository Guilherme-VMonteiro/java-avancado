package com.jovemdev.dados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jovemdev.dados.dto.UsuarioDto;
import com.jovemdev.dados.exceptions.ExcessaoPersonalizada;
import com.jovemdev.dados.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public List<UsuarioDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getMensagem());
		}
	}

	@GetMapping("/melhores")
	public List<UsuarioDto> findBests(){
		return service.findBests();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto) {
		try {
			return ResponseEntity.ok(service.create(usuarioDto));
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getMensagem());
		}
	}
}
