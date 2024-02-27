package com.arphoenix.mscaffeine.exception;

import java.io.Serial;
import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Diego Cunha
 * @email diego.cunha@arphoenix.com.br
 * @description Classe de excessão de validação
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
		super(message);
	}
}
