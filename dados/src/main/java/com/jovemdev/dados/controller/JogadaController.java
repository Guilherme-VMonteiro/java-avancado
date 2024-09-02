package com.jovemdev.dados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jovemdev.dados.dto.JogadaDto;
import com.jovemdev.dados.exceptions.ExcessaoPersonalizada;
import com.jovemdev.dados.service.JogadaService;

@RestController
@RequestMapping("/jogar")
public class JogadaController {

	@Autowired
	private JogadaService service;

	@GetMapping
	public List<JogadaDto> findAll() {
		return service.listarJogadas();
	}

	@PostMapping()
	private ResponseEntity<?> realizarJogada(@RequestParam Integer qtdDados, @RequestParam Integer valorAposta,
			@RequestParam Long idUsuario) {
		try {
			return ResponseEntity.ok(service.realizarJogada(qtdDados, valorAposta, idUsuario));
		} catch (ExcessaoPersonalizada e) {
			return ResponseEntity.badRequest().body(e.getMensagem());
		}
	}
}
