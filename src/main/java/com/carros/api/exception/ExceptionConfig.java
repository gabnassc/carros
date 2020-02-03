package com.carros.api.exception;

import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@RestControllerAdvice
public class ExceptionConfig extends ResponseStatusExceptionHandler{
	
	@ExceptionHandler({
		EmptyResultDataAccessException.class
	})
	public ResponseEntity<?> errorNotFound(Exception e) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({
		IllegalArgumentException.class
	})
	public ResponseEntity<?> errorBadRequest(Exception e) {
		return ResponseEntity.badRequest().build();		
	}
	
	
	@ExceptionHandler
	({
		AccessDeniedException.class
	})
	
	public ResponseEntity AccessDeniedException() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso negado"));
	}
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>("Operacao nao permitida", HttpStatus.METHOD_NOT_ALLOWED);
	}

	
	
}


class ExceptionError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String error;
	public ExceptionError(String error) {
		
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
}