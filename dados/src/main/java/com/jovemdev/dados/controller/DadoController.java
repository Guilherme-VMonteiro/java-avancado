package com.jovemdev.dados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jovemdev.dados.dto.JogadaDto;
import com.jovemdev.dados.service.DadoService;

@RestController
@RequestMapping("/apostar")
public class DadoController {

	@Autowired
	private DadoService dadoService;

	@GetMapping
	public List<JogadaDto> listarJogadas() {
		return dadoService.listarJogadas();
	}

	@PostMapping("/{qtdDados}/{valorAposta}")
	public ResponseEntity<?> realizarAposta(@PathVariable Integer qtdDados, @PathVariable Integer valorAposta) {
		try {
			return ResponseEntity.ok(dadoService.realizarAposta(qtdDados, valorAposta).get());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
