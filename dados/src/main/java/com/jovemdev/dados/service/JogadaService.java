package com.jovemdev.dados.service;

import java.util.List;

import com.jovemdev.dados.dto.JogadaDto;
import com.jovemdev.dados.exceptions.ExcessaoPersonalizada;

public interface JogadaService {
	
	JogadaDto realizarJogada(Integer qtdDados, Integer valorAposta, Long idUsuario) throws ExcessaoPersonalizada; 
	List<JogadaDto> listarJogadas();
}
