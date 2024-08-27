package com.jovemdev.dados.service;

import java.util.List;

import com.jovemdev.dados.dto.JogadaDto;

public interface DadoService {
	
	JogadaDto realizarAposta(Integer qtdDados, Integer valorAposta);
	List<JogadaDto> listarJogadas();
}
