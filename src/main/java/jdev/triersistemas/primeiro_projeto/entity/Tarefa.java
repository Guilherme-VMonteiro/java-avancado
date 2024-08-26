package jdev.triersistemas.primeiro_projeto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Tarefa {
	
	private Long id;
	private String titulo;
	private String descricao;
	private boolean completa;
}
