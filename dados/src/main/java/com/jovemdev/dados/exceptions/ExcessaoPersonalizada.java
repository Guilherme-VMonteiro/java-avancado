package com.jovemdev.dados.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ExcessaoPersonalizada extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private MensagemExcessao mensagem;

	public ExcessaoPersonalizada(String mensagem) {
		super();
		this.mensagem = new MensagemExcessao(mensagem);
	}
	
	
}
