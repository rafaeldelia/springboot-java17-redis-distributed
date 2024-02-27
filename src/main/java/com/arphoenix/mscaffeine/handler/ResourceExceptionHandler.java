package com.arphoenix.mscaffeine.handler;

import java.io.IOException;
import java.time.Instant;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arphoenix.mscaffeine.dto.ResponseMessageError;
import com.arphoenix.mscaffeine.exception.InternalErrorException;
import com.arphoenix.mscaffeine.exception.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Handle das exceptions capturadas e devolvidas para o Controller.
 *
 * @author rsdelia
 */
@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * Captura as Exceções do NotFoundException
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseMessageError> handleNotFound(NotFoundException e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.NOT_FOUND.value(), e.getMessage(), request);
	}

	/**
	 * Captura as Exceções do InternalErrorException
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(InternalErrorException.class)
	public ResponseEntity<ResponseMessageError> handleInternalError(InternalErrorException e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request);
	}

	/**
	 * Captura as Exceções do ValidationException
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseMessageError> handleValidation(ValidationException e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.BAD_REQUEST.value(), e.getMessage(), request);
	}

	/**
	 * Captura as Exceções do IOException
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResponseMessageError> handleIOException(IOException e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request);
	}

	/**
	 * Captura as Exceções do FileSizeLimitExceededException
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(FileSizeLimitExceededException.class)
	public ResponseEntity<ResponseMessageError> handleFileSizeLimitExceededException(FileSizeLimitExceededException e,
			HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request);
	}

	/**
	 * Captura as Exceções do Exception
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessageError> handleException(Exception e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request);
	}

	/**
	 * Método que popula o response para cada exceção.
	 *
	 * @param e
	 * @param status
	 * @param mensagemErro
	 * @param request
	 * @return ResponseEntity<ResponseMessageError>
	 */
	private static ResponseEntity<ResponseMessageError> popularResponseMessageError(Exception e, Integer status, String mensagemErro,
			HttpServletRequest request) {
		e.printStackTrace();
		log.error("ERROR: [" + mensagemErro + "]");
		ResponseMessageError err = new ResponseMessageError();
		err.setTimestamp(Instant.now());
		err.setStatus(status);
		err.setError(mensagemErro);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}