package com.jovemdev.dados.service;

import java.util.List;
import java.util.Optional;

import com.jovemdev.dados.dto.JogadaDto;

public interface DadoService {

	Optional<JogadaDto> realizarAposta(Integer qtdDados, Integer valorAposta);
	List<JogadaDto> listarJogadas();
}
