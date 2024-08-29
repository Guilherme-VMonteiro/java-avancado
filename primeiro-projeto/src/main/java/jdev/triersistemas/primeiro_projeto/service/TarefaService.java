package jdev.triersistemas.primeiro_projeto.service;

import java.util.List;

import jdev.triersistemas.primeiro_projeto.dto.TarefaDto;
import jdev.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jdev.triersistemas.primeiro_projeto.exceptions.AcaoInvalidaException;
import jdev.triersistemas.primeiro_projeto.exceptions.EntidadeNaoEncontradaException;

public interface TarefaService {

	List<TarefaDto> findAll();

	TarefaDto findById(Long id) throws EntidadeNaoEncontradaException;

	TarefaDto create(TarefaDto tarefa);

	TarefaDto update(TarefaDto tarefaAtualizada) throws EntidadeNaoEncontradaException;

	void delete(Long id) throws EntidadeNaoEncontradaException, AcaoInvalidaException;

	List<TarefaDto> findCompletas();

	List<TarefaDto> findIncompletas();

	List<TarefaDto> createAll(List<TarefaDto> tarefas);
	
	List<TarefaDto> findAllByCategoria(CategoriaEntity dto);
}
