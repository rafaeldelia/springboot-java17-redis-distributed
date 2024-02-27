package com.arphoenix.mscaffeine.exception;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description Classe de excessão para objeto não emcontrado
 */
public class NotFoundException extends Exception implements Serializable {

	@Serial
	private static final long serialVersionUID = -6762391678258643789L;

	public NotFoundException(String message) {
		super(message);
	}
}
