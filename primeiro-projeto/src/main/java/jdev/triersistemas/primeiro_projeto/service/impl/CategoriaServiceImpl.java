package jdev.triersistemas.primeiro_projeto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdev.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jdev.triersistemas.primeiro_projeto.dto.TarefaDto;
import jdev.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jdev.triersistemas.primeiro_projeto.exceptions.AcaoInvalidaException;
import jdev.triersistemas.primeiro_projeto.exceptions.EntidadeNaoEncontradaException;
import jdev.triersistemas.primeiro_projeto.repository.CategoriaRepository;
import jdev.triersistemas.primeiro_projeto.service.CategoriaService;
import jdev.triersistemas.primeiro_projeto.service.TarefaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private TarefaService tarefaServce;

	public List<CategoriaDto> findAll() {
		return repository.findAll().stream().map(CategoriaDto::new).toList();
	}

	public CategoriaDto findById(Long id) throws EntidadeNaoEncontradaException{
		Optional<CategoriaEntity> categoriaOptional = repository.findById(id);

		return new CategoriaDto(categoriaOptional.orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Categoria id: %s não encontrada", id))));
	}

	public CategoriaDto create(CategoriaDto dto) {
		return new CategoriaDto(repository.save(new CategoriaEntity(dto)));
	}

	public CategoriaDto update(CategoriaDto dtoAtualizado) throws EntidadeNaoEncontradaException{
		Optional<CategoriaEntity> categoriaOptional = repository.findById(dtoAtualizado.getId());

		CategoriaEntity categoriaEntity = categoriaOptional.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Categoria id: %s não encontrada", dtoAtualizado.getId())));

		categoriaOptional.get().atualizaCategoria(dtoAtualizado);

		return new CategoriaDto(repository.save(categoriaEntity));
	}

	public void delete(Long id) throws EntidadeNaoEncontradaException, AcaoInvalidaException{
		Optional<CategoriaEntity> categoriaOptional = repository.findById(id);

		List<TarefaDto> tarefas = tarefaServce.findAllByCategoria(categoriaOptional.orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Categoria id: %s não encontrada", id))));

		if (!tarefas.isEmpty()) {
			throw new AcaoInvalidaException(String.format(
					"Não é possivel deletar a categoria id: %s pelo fato de possuir tarefas com esta atribuição", id));
		}

		repository.deleteById(id);
	}
}
