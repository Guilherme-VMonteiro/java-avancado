package jdev.triersistemas.primeiro_projeto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class AcaoInvalidaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private ExceptionMessage exceptionMessage;
	
	public AcaoInvalidaException(String mensagem) {
		this.exceptionMessage = new ExceptionMessage(mensagem);
	}
}
