package com.jovemdev.dados.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jovemdev.dados.dto.JogadaDto;
import com.jovemdev.dados.dto.UsuarioDto;
import com.jovemdev.dados.entity.JogadaEntity;
import com.jovemdev.dados.entity.UsuarioEntity;
import com.jovemdev.dados.exceptions.ExcessaoPersonalizada;
import com.jovemdev.dados.repository.JogadaRepository;
import com.jovemdev.dados.service.JogadaService;
import com.jovemdev.dados.service.UsuarioService;

@Service
public class JogadaServiceImpl implements JogadaService {

	@Autowired
	private JogadaRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	private final Random random = new Random();

	public JogadaDto realizarJogada(Integer qtdDados, Integer valorAposta, Long idUsuario)
			throws ExcessaoPersonalizada {
		validaAposta(qtdDados, valorAposta);

		UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioService.findById(idUsuario));

		List<Integer> listaDeResultados = new ArrayList<>();

		lancarDados(qtdDados, listaDeResultados);

		JogadaEntity jogadaEntity = new JogadaEntity(null, listaDeResultados.toString(), valorAposta,
				listaDeResultados.stream().mapToInt(Integer::intValue).sum(),
				calcularPercentual(listaDeResultados, valorAposta), usuarioEntity);

		JogadaDto response = new JogadaDto(repository.save(jogadaEntity));

		response.setUsuario(
				usuarioService.atualizaEstatisticas(new UsuarioDto(usuarioEntity), listarJogadasDoUsuario(idUsuario)));

		return response;
	}

	public List<JogadaDto> listarJogadas() {
		return repository.findAll().stream().map(JogadaDto::new).toList();
	}

	private List<JogadaDto> listarJogadasDoUsuario(Long id) {
		return repository.findAllJogadaByUsuarioId(id).stream().map(JogadaDto::new).toList();
	}

	private void validaAposta(Integer qtdDados, Integer valorAposta) throws ExcessaoPersonalizada {
		if (qtdDados < 1 || qtdDados > 4) {
			throw new ExcessaoPersonalizada("Quantidade de dados inválida! Esperado: 1 a 4");
		}
		if (valorAposta < qtdDados || valorAposta > qtdDados * 6) {
			throw new ExcessaoPersonalizada("Valor da aposta inválido! Esperado: " + qtdDados + " a " + qtdDados * 6);
		}
	}

	private int lancarDado() {
		return 1 + random.nextInt(6);
	}

	private void lancarDados(int qtdDados, List<Integer> listaDeResultados) {
		for (int i = 1; i <= qtdDados; i++) {
			listaDeResultados.add(lancarDado());
		}
	}

	private Double calcularPercentual(List<Integer> listaDeResultados, Integer valorAposta) {
		Integer total = listaDeResultados.stream().reduce(0, (subtotal, elemento) -> subtotal + elemento);
		return (double) Math.round(total * 100 / (double) valorAposta);
	}

}
