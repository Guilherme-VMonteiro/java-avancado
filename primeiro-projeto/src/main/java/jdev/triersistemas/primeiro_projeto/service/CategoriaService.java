package jdev.triersistemas.primeiro_projeto.service;

import java.util.List;

import jdev.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jdev.triersistemas.primeiro_projeto.exceptions.AcaoInvalidaException;
import jdev.triersistemas.primeiro_projeto.exceptions.EntidadeNaoEncontradaException;

public interface CategoriaService {

	List<CategoriaDto> findAll();

	CategoriaDto findById(Long id) throws EntidadeNaoEncontradaException;

	CategoriaDto create(CategoriaDto dto);

	CategoriaDto update(CategoriaDto dtoAtualizado) throws EntidadeNaoEncontradaException;

	void delete(Long id) throws EntidadeNaoEncontradaException, AcaoInvalidaException;
}
