package com.jovemdev.dados.dto;

import com.jovemdev.dados.entity.JogadaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JogadaDto {

	private Long id;
	private String resultadoDados;
	private Integer valorAposta;
	private Integer somaResultado;
	private Double percentual;
	private UsuarioDto usuario;	
	
	public JogadaDto(JogadaEntity entity) {
		this.id = entity.getId();
		this.resultadoDados = entity.getResultadoDados();
		this.valorAposta = entity.getValorAposta();
		this.somaResultado = entity.getSomaResultado();
		this.percentual = entity.getPercentual();
		this.usuario = new UsuarioDto(entity.getUsuario());
	}
}
