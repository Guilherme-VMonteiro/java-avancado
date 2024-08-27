package com.jovemdev.dados.dto;

import java.util.List;

import com.jovemdev.dados.entity.Jogada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JogadaDto {
	
	private Long id;
	private List<Integer> resultados;
	private Integer somaDados;
	private Double percentual;

	public JogadaDto(Jogada jogada) {
		this.id = jogada.getId();
		this.resultados = jogada.getResultados();
		this.somaDados = resultados.stream().reduce(0, (subtotal, elemento) -> subtotal + elemento);
		this.percentual = jogada.getPercentual();
	}

}
