package com.jovemdev.dados.entity;

import java.util.List;

import com.jovemdev.dados.dto.UsuarioDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_aposta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sobrenome;

	@Column(name = "numero_da_sorte", length = 6)
	private Integer numeroDaSorte;

	@Column(name = "qtd_acerto")
	private Integer qtdAcerto;

	@Column(name = "percentual_acerto")
	private Double percentualAcerto;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.DETACH)
	private List<JogadaEntity> listaDeJogadas;

	public UsuarioEntity(UsuarioDto dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.sobrenome = dto.getSobrenome();
		this.numeroDaSorte = dto.getNumeroDaSorte();
		this.qtdAcerto = dto.getQtdAcerto();
		this.percentualAcerto = dto.getPercentualAcerto();
	}
}
