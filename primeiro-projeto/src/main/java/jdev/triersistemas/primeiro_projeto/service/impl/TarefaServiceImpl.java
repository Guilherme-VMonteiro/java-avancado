package jdev.triersistemas.primeiro_projeto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdev.triersistemas.primeiro_projeto.dto.TarefaDto;
import jdev.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jdev.triersistemas.primeiro_projeto.entity.TarefaEntity;
import jdev.triersistemas.primeiro_projeto.exceptions.EntidadeNaoEncontradaException;
import jdev.triersistemas.primeiro_projeto.repository.TarefaRepository;
import jdev.triersistemas.primeiro_projeto.service.TarefaService;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository repository;

	public List<TarefaDto> findAll() {
		return repository.findAll().stream().map(TarefaDto::new).toList();
	}

	public TarefaDto findById(Long id) throws EntidadeNaoEncontradaException {

		Optional<TarefaEntity> optionalEntity = repository.findById(id);

		return new TarefaDto(optionalEntity.orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Tarefa id: %s não encontrada", id))));
	}

	public TarefaDto create(TarefaDto tarefa) {
		TarefaEntity entityPersisted = repository.save(new TarefaEntity(tarefa));
		return new TarefaDto(entityPersisted);
	}

	public TarefaDto update(TarefaDto tarefaAtualizada) throws EntidadeNaoEncontradaException {
		Optional<TarefaEntity> entidadeOptional = repository.findById(tarefaAtualizada.getId());

		TarefaEntity entidade = entidadeOptional.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Tarefa id: %s não encontrada", tarefaAtualizada.getId())));

		entidade.atualizaEntity(tarefaAtualizada);

		return new TarefaDto(repository.save(entidade));
	}

	public void delete(Long id) {
		Optional<TarefaEntity> tarefaOptional = repository.findById(id);
		tarefaOptional.ifPresent(repository::delete);
	}

	public List<TarefaDto> findCompletas() {
		return repository.findCompleta().stream().map(TarefaDto::new).toList();
	}

	public List<TarefaDto> findIncompletas() {
		return repository.findIncompleta().stream().map(TarefaDto::new).toList();
	}

	public List<TarefaDto> createAll(List<TarefaDto> tarefas) {
		return tarefas.stream().map(dto -> repository.save(new TarefaEntity(dto))).map(TarefaDto::new).toList();
	}

	public List<TarefaDto> findAllByCategoria(CategoriaEntity dto) {
		return repository.findAllByCategoriaOrderByIdAsc(dto).stream().map(TarefaDto::new).toList();
	}
}
