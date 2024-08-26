package jdev.triersistemas.primeiro_projeto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdev.triersistemas.primeiro_projeto.entity.Tarefa;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

	private static List<Tarefa> listaTarefas = new ArrayList<>();
	private static AtomicLong contador = new AtomicLong();

	@GetMapping
	public List<Tarefa> findAll() {
		return listaTarefas;
	}

	@GetMapping("/{id}")
	public Tarefa findById(@PathVariable("id") Long id) {
		return listaTarefas.stream().filter(obj -> obj.getId().equals(id)).findFirst().orElse(null);
	}

	@GetMapping("/completas")
	public List<Tarefa> findCompletas() {
		return listaTarefas.stream().filter(tarefa -> tarefa.isCompleta()).toList();
	}

	@GetMapping("/incompletas")
	public List<Tarefa> findIncompletas() {
		return listaTarefas.stream().filter(tarefa -> !tarefa.isCompleta()).toList();
	}

	@PostMapping
	public Tarefa create(@RequestBody Tarefa tarefa) {
		tarefa.setId(contador.incrementAndGet());
		listaTarefas.add(tarefa);
		return listaTarefas.get(listaTarefas.size() - 1);
	}

	@PostMapping("/criarTodos")
	public List<Tarefa> createAll(@RequestBody List<Tarefa> tarefas) {
		tarefas.stream().forEach(tarefa -> tarefa.setId(contador.incrementAndGet()));
		tarefas.stream().forEach(tarefa -> listaTarefas.add(tarefa));

		List<Long> idsInseridos = tarefas.stream().map(tarefa -> tarefa.getId()).toList();
		
		return listaTarefas.stream().filter(tarefa -> idsInseridos.contains(tarefa.getId())).toList();
	}

	@PutMapping
	public Tarefa update(@RequestBody Tarefa tarefaAtualizada) {
		Optional<Tarefa> tarefaOptional = listaTarefas.stream()
				.filter(tarefa -> tarefa.getId().equals(tarefaAtualizada.getId())).findFirst();

		if (tarefaOptional.isEmpty()) {
			return null;
		}

		tarefaOptional.get().setTitulo(tarefaAtualizada.getTitulo());
		tarefaOptional.get().setDescricao(tarefaAtualizada.getDescricao());
		tarefaOptional.get().setCompleta(tarefaAtualizada.isCompleta());

		return listaTarefas.stream().filter(tarefaSalva -> tarefaSalva.getId().equals(tarefaAtualizada.getId()))
				.findFirst().get();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		Optional<Tarefa> tarefaOptional = listaTarefas.stream().filter(tarefa -> tarefa.getId().equals(id)).findFirst();
		tarefaOptional.ifPresent(tarefa -> listaTarefas.remove(tarefa));
	}
}
