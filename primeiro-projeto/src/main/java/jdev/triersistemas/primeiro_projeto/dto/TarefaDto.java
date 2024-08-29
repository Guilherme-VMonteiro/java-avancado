package jdev.triersistemas.primeiro_projeto.dto;

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
	private CategoriaDto categoria;

	public TarefaDto(TarefaEntity entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.descricao = entity.getDescricao();
		this.completa = entity.getCompleta();
		this.categoria = new CategoriaDto(entity.getCategoria());
	}
}
