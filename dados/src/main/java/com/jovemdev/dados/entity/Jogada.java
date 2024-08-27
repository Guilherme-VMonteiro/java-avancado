package com.jovemdev.dados.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Jogada {
	
	private Long id;
	private Integer qtdDados;
	private List<Integer> resultados = new ArrayList<>();
	private Integer valorApostado;
	private Double percentual;
}
