package com.jovemdev.dados.dto;

import com.jovemdev.dados.entity.UsuarioEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

	private Long id;
	private String nome;
	private String sobrenome;
	private Integer numeroDaSorte;
	private Integer qtdAcerto;
	private Double percentualAcerto;
	
	public UsuarioDto(UsuarioEntity entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.sobrenome = entity.getSobrenome();
		this.numeroDaSorte = entity.getNumeroDaSorte();
		this.qtdAcerto = entity.getQtdAcerto();
		this.percentualAcerto = entity.getPercentualAcerto();
	}
	
}
