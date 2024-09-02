package com.jovemdev.dados.service;

import java.util.List;

import com.jovemdev.dados.dto.JogadaDto;
import com.jovemdev.dados.dto.UsuarioDto;
import com.jovemdev.dados.exceptions.ExcessaoPersonalizada;

public interface UsuarioService {

	UsuarioDto findById(Long id) throws ExcessaoPersonalizada;
	
	List<UsuarioDto> findAll();

	UsuarioDto create(UsuarioDto usuarioDto) throws ExcessaoPersonalizada;
	
	List<UsuarioDto> findBests();
	
	UsuarioDto atualizaEstatisticas(UsuarioDto usuarioDto, List<JogadaDto> listaJogadas) throws ExcessaoPersonalizada;
}
