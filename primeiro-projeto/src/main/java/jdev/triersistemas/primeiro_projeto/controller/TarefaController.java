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

import jdev.triersistemas.primeiro_projeto.dto.TarefaDto;
import jdev.triersistemas.primeiro_projeto.exceptions.DataExpiracaoInvalidaException;
import jdev.triersistemas.primeiro_projeto.exceptions.EntidadeNaoEncontradaException;
import jdev.triersistemas.primeiro_projeto.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	@GetMapping
	public List<TarefaDto> findAll() {
		return tarefaService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(tarefaService.findById(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getExceptionMessage());
		}
	}

	@GetMapping("/expiracao/{qtdDias}")
	public List<TarefaDto> findAllExpiresSoon(@PathVariable("qtdDias") Integer qtdDias) {
		return tarefaService.findAllExpiresSoon(qtdDias);
	}

	@GetMapping("/titulo/{titulo}")
	public List<TarefaDto> findAllByTitulo(@PathVariable String titulo) {
		return tarefaService.findAllByTitulo(titulo);
	}

	@GetMapping("/completa")
	public List<TarefaDto> findCompletas() {
		return tarefaService.findCompletas();
	}

	@GetMapping("/categoria/{id}/{concluido}")
	public Long contarTarefasPorCategoriaEStatus(@PathVariable Long id, @PathVariable Boolean concluido) {
		return tarefaService.contarTarefasPorCategoriaEStatus(id, concluido);
	}

	@GetMapping("/incompleta")
	public List<TarefaDto> findIncompletas() {
		return tarefaService.findIncompletas();
	}

	@GetMapping("/categoria/incompleta/{id}")
	public List<TarefaDto> findIncompletasPorCategoria(@PathVariable Long id) {
		return tarefaService.findIncompletasPorCategoria(id);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody TarefaDto tarefa) {
		try {
			return ResponseEntity.ok(tarefaService.create(tarefa));
		} catch (DataExpiracaoInvalidaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getExceptionMessage());
		}
	}

	@PostMapping("/criarTodos")
	public ResponseEntity<?> createAll(@RequestBody List<TarefaDto> tarefas) {
		try {
			return ResponseEntity.ok(tarefaService.createAll(tarefas));
		} catch (DataExpiracaoInvalidaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getExceptionMessage());
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody TarefaDto tarefaAtualizada) {
		try {
			return ResponseEntity.ok(tarefaService.update(tarefaAtualizada));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getExceptionMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		tarefaService.delete(id);
	}
}
