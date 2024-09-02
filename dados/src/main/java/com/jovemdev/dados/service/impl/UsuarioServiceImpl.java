package com.jovemdev.dados.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jovemdev.dados.dto.JogadaDto;
import com.jovemdev.dados.dto.UsuarioDto;
import com.jovemdev.dados.entity.UsuarioEntity;
import com.jovemdev.dados.exceptions.ExcessaoPersonalizada;
import com.jovemdev.dados.repository.UsuarioRepository;
import com.jovemdev.dados.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioDto findById(Long id) throws ExcessaoPersonalizada {
		Optional<UsuarioEntity> optionalUsuario = repository.findById(id);

		return new UsuarioDto(optionalUsuario
				.orElseThrow(() -> new ExcessaoPersonalizada(String.format("Usuário id: %s não encontrado", id))));
	}

	public List<UsuarioDto> findAll() {
		return repository.findAll().stream().map(UsuarioDto::new).toList();
	}

	public UsuarioDto create(UsuarioDto usuarioDto) throws ExcessaoPersonalizada {
		validaCampos(usuarioDto);

		usuarioDto.setPercentualAcerto(0D);
		usuarioDto.setQtdAcerto(0);

		return new UsuarioDto(repository.save(new UsuarioEntity(usuarioDto)));
	}

	public List<UsuarioDto> findBests() {
		return repository.findBests().stream().map(UsuarioDto::new).toList();
	}

	public UsuarioDto atualizaEstatisticas(UsuarioDto usuarioDto, List<JogadaDto> listaJogadas)
			throws ExcessaoPersonalizada {
		usuarioDto = findById(usuarioDto.getId());

		Map<Integer, Integer> resultados = coletaResultados(listaJogadas);

		usuarioDto.setNumeroDaSorte(coletaNumeroDaSorte(resultados));

		usuarioDto.setQtdAcerto(
				listaJogadas.stream().filter(jogada -> jogada.getPercentual().equals(100D)).toList().size());

		usuarioDto.setPercentualAcerto(
				(double) Math.round((usuarioDto.getQtdAcerto() / (double) listaJogadas.size()) * 100));

		return new UsuarioDto(repository.save(new UsuarioEntity(usuarioDto)));
	}

	private void validaCampos(UsuarioDto dto) throws ExcessaoPersonalizada {
		if (dto.getNome().isBlank()) {
			throw new ExcessaoPersonalizada("Não é possível criar um usuário sem nome.");
		}
		if (dto.getSobrenome().isBlank()) {
			throw new ExcessaoPersonalizada("Não é possível criar um usuário sem sobrenome.");
		}
	}

	private Integer coletaNumeroDaSorte(Map<Integer, Integer> resultados) {

		Integer maiorChave = 0;
		Integer maior = 0;

		for (Integer chave : resultados.keySet()) {
			Integer resultado = resultados.get(chave);
			if (resultado > maior) {
				maior = resultado;
				maiorChave = chave;
			}
		}

		return maiorChave;
	}

	private Map<Integer, Integer> coletaResultados(List<JogadaDto> listaJogadas) {
		Map<Integer, Integer> mapaResultados = new HashMap<>();

		for (JogadaDto jogadaDto : listaJogadas) {
			serializaResultado(jogadaDto.getResultadoDados())
					.forEach(resultado -> mapaResultados.put(resultado, mapaResultados.getOrDefault(resultado, 0) + 1));
		}

		return mapaResultados;
	}

	private List<Integer> serializaResultado(String resultados) {
		List<Integer> resultadosList = new ArrayList<>();

		for (String resultado : resultados.replace("[", "").replace("]", "").replace(" ", "").split(",")) {
			resultadosList.add(Integer.parseInt(resultado));
		}

		return resultadosList;
	}
}
