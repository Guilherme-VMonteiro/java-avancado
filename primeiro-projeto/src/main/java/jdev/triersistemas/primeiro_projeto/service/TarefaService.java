package jdev.triersistemas.primeiro_projeto.service;

import java.util.List;
import java.util.Optional;

import jdev.triersistemas.primeiro_projeto.dto.TarefaDto;

public interface TarefaService {

	List<TarefaDto> findAll();

	Optional<TarefaDto> findById(Long id);

	TarefaDto create(TarefaDto tarefa);

	TarefaDto update(TarefaDto tarefaAtualizada);

	void delete(Long id);

	List<TarefaDto> findCompletas();

	List<TarefaDto> findIncompletas();

	List<TarefaDto> createAll(List<TarefaDto> tarefas);
}
