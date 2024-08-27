package com.jovemdev.dados.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.jovemdev.dados.dto.JogadaDto;
import com.jovemdev.dados.entity.Jogada;
import com.jovemdev.dados.service.DadoService;

@Service
public class DadoServiceImpl implements DadoService {

	private final AtomicLong geradorDeId = new AtomicLong();
	private final List<Jogada> listaDeJogadas = new ArrayList<>();
	private final Random random = new Random();

	public JogadaDto realizarAposta(Integer qtdDados, Integer valorAposta) throws IllegalArgumentException {
		validaDados(qtdDados, valorAposta);

		Jogada novaJogada = new Jogada();
		novaJogada.setId(geradorDeId.incrementAndGet());
		novaJogada.setQtdDados(qtdDados);
		novaJogada.setValorApostado(valorAposta);
		lancarDados(qtdDados, novaJogada.getResultados());
		novaJogada.setPercentual(calcularPercentual(novaJogada.getResultados(), valorAposta));

		listaDeJogadas.add(novaJogada);

		return new JogadaDto(novaJogada);
	}

	public List<JogadaDto> listarJogadas() {
		return listaDeJogadas.stream().map(jogada -> new JogadaDto(jogada)).toList();
	}

	private void validaDados(Integer qtdDados, Integer valorAposta) throws IllegalArgumentException {
		if (qtdDados < 1 || qtdDados > 4) {
			throw new IllegalArgumentException("Quantidade de dados inválida! Esperado: 1 a 4");
		}
		if (valorAposta < qtdDados || valorAposta > qtdDados * 6) {
			throw new IllegalArgumentException(
					"Valor da aposta inválido! Esperado: " + qtdDados + " a " + qtdDados * 6);
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
		return total * 100 / (double) valorAposta;
	}
}
