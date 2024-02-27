package com.arphoenix.mscaffeine.exception;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Diego Cunha
 * @email diego.cunha@arphoenix.com.br
 * @description Classe de excessão de erro interno
 */
public class IOException extends Exception implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public IOException(String message) {
		super(message);
	}

}
