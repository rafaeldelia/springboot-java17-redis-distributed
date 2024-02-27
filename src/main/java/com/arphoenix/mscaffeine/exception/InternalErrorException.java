package com.arphoenix.mscaffeine.exception;

import java.io.Serial;
import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Diego Cunha
 * @email diego.cunha@arphoenix.com.br
 * @description Classe de excessão de erro interno
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends Exception implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public InternalErrorException(String message) {
		super(message);
	}

}
