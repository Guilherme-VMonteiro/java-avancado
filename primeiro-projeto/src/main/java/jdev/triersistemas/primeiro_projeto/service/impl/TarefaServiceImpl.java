package jdev.triersistemas.primeiro_projeto.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import jdev.triersistemas.primeiro_projeto.dto.TarefaDto;
import jdev.triersistemas.primeiro_projeto.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	private static List<TarefaDto> listaTarefas = new ArrayList<>();
	private static AtomicLong contador = new AtomicLong();

	public List<TarefaDto> findAll() {
		return listaTarefas;
	}

	public Optional<TarefaDto> findById(Long id) {
		return listaTarefas.stream().filter(obj -> obj.getId().equals(id)).findFirst();
	}

	public TarefaDto create(TarefaDto tarefa) {
		tarefa.setId(contador.incrementAndGet());
		listaTarefas.add(tarefa);
		return listaTarefas.get(listaTarefas.size() - 1);
	}

	public TarefaDto update(TarefaDto tarefaAtualizada) {
		Optional<TarefaDto> tarefaOptional = listaTarefas.stream()
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

	public void delete(Long id) {
		Optional<TarefaDto> tarefaOptional = listaTarefas.stream().filter(tarefa -> tarefa.getId().equals(id))
				.findFirst();
		tarefaOptional.ifPresent(tarefa -> listaTarefas.remove(tarefa));
	}

	public List<TarefaDto> findCompletas() {
		return listaTarefas.stream().filter(tarefa -> tarefa.isCompleta()).toList();
	}

	public List<TarefaDto> findIncompletas() {
		return listaTarefas.stream().filter(tarefa -> !tarefa.isCompleta()).toList();
	}

	public List<TarefaDto> createAll(List<TarefaDto> tarefas) {
		tarefas.stream().forEach(tarefa -> tarefa.setId(contador.incrementAndGet()));
		tarefas.stream().forEach(tarefa -> listaTarefas.add(tarefa));

		List<Long> idsInseridos = tarefas.stream().map(tarefa -> tarefa.getId()).toList();

		return listaTarefas.stream().filter(tarefa -> idsInseridos.contains(tarefa.getId())).toList();
	}

}
