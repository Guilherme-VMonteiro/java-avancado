package com.jovemdev.dados.entity;

import com.jovemdev.dados.dto.JogadaDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jogada")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JogadaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "resultado_dados", nullable = false)
	private String resultadoDados;
	
	@Column(name = "valor_aposta", nullable = false)
	private Integer valorAposta;
	
	@Column(name = "soma_resultado", nullable = false)
	private Integer somaResultado;
	
	@Column(nullable = false)
	private Double percentual;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "usuario_id", nullable = false)
	private UsuarioEntity usuario;
	
	public JogadaEntity(JogadaDto dto) {
		this.id = dto.getId();
		this.resultadoDados = dto.getResultadoDados();
		this.valorAposta = dto.getValorAposta();
		this.somaResultado = dto.getSomaResultado();
		this.percentual = dto.getPercentual();
		this.usuario = new UsuarioEntity(dto.getUsuario());
	}
}
