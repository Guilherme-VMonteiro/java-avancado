package jdev.triersistemas.primeiro_projeto.dto;

import java.time.LocalDate;

import jdev.triersistemas.primeiro_projeto.entity.TarefaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarefaDto {

	private Long id;
	private String titulo;
	private String descricao;
	private Boolean completa;
	private LocalDate dataCriacao;
	private LocalDate dataExpiracao;
	private CategoriaDto categoria;
	
	public TarefaDto(TarefaEntity entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.descricao = entity.getDescricao();
		this.completa = entity.getCompleta();
		this.dataCriacao = entity.getDataCriacao();
		this.dataExpiracao = entity.getDataExpiracao();
		this.categoria = new CategoriaDto(entity.getCategoria());
	}

	public TarefaDto(Long id, String titulo, String descricao, Boolean completa, LocalDate dataExpiracao,
			CategoriaDto categoria) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.completa = completa;
		this.dataExpiracao = dataExpiracao;
		this.categoria = categoria;
	}
}
