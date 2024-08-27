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
	public ResponseEntity<TarefaDto> findById(@PathVariable("id") Long id) {
		return tarefaService.findById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}

	@GetMapping("/completas")
	public List<TarefaDto> findCompletas() {
		return tarefaService.findCompletas();
	}

	@GetMapping("/incompletas")
	public List<TarefaDto> findIncompletas() {
		return tarefaService.findIncompletas();
	}

	@PostMapping
	public TarefaDto create(@RequestBody TarefaDto tarefa) {
		return tarefaService.create(tarefa);
	}

	@PostMapping("/criarTodos")
	public List<TarefaDto> createAll(@RequestBody List<TarefaDto> tarefas) {
		return tarefaService.createAll(tarefas);
	}

	@PutMapping
	public TarefaDto update(@RequestBody TarefaDto tarefaAtualizada) {
		return tarefaService.update(tarefaAtualizada);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		tarefaService.delete(id);
	}
}
