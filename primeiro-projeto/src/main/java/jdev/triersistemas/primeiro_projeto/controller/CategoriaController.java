package jdev.triersistemas.primeiro_projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdev.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jdev.triersistemas.primeiro_projeto.exceptions.AcaoInvalidaException;
import jdev.triersistemas.primeiro_projeto.exceptions.EntidadeNaoEncontradaException;
import jdev.triersistemas.primeiro_projeto.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@GetMapping
	public List<CategoriaDto> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getExceptionMessage());
		}
	}

	@PostMapping
	public CategoriaDto create(@RequestBody CategoriaDto dto) {
		return service.create(dto);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody CategoriaDto dto) {
		try {
			return ResponseEntity.ok(service.update(dto));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getExceptionMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			service.delete(id);
			return ResponseEntity.ok(null);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getExceptionMessage());
		} catch (AcaoInvalidaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getExceptionMessage());
		}
	}
}
