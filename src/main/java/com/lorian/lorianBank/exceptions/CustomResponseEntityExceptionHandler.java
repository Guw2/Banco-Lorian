package com.lorian.lorianBank.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lorian.lorianBank.exceptions.custom.EmailNotFoundException;
import com.lorian.lorianBank.exceptions.custom.IdNotFoundException;
import com.lorian.lorianBank.exceptions.custom.NumeroNotFoundException;
import com.lorian.lorianBank.exceptions.custom.TransacaoException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<ExceptionTemplate> IdNotFoundExceptionHandler(
			IdNotFoundException e, WebRequest req
			){
		ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
		exceptionTemplate.setTimestamp(Instant.now());
		exceptionTemplate.setStatus(HttpStatus.NOT_FOUND.value());
		exceptionTemplate.setError(e.getMessage());
		exceptionTemplate.setPath(req.getDescription(false).replace("uri=", ""));
		
		return new ResponseEntity<ExceptionTemplate>(exceptionTemplate, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmailNotFoundException.class)
	public ResponseEntity<ExceptionTemplate> emailNotFoundExceptionHandler(
			EmailNotFoundException e, WebRequest req
			){
		ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
		exceptionTemplate.setTimestamp(Instant.now());
		exceptionTemplate.setStatus(HttpStatus.NOT_FOUND.value());
		exceptionTemplate.setError(e.getMessage());
		exceptionTemplate.setPath(req.getDescription(false).replace("uri=", ""));
		
		return new ResponseEntity<ExceptionTemplate>(exceptionTemplate, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NumeroNotFoundException.class)
	public ResponseEntity<ExceptionTemplate> numeroNotFoundExceptionHandler(
			NumeroNotFoundException e, WebRequest req
			){
		ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
		exceptionTemplate.setTimestamp(Instant.now());
		exceptionTemplate.setStatus(HttpStatus.NOT_FOUND.value());
		exceptionTemplate.setError(e.getMessage());
		exceptionTemplate.setPath(req.getDescription(false).replace("uri=", ""));
		
		return new ResponseEntity<ExceptionTemplate>(exceptionTemplate, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = TransacaoException.class)
	public ResponseEntity<ExceptionTemplate> transacaoExceptionHandler(
			TransacaoException e, WebRequest req
			){
		ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
		exceptionTemplate.setTimestamp(Instant.now());
		exceptionTemplate.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		exceptionTemplate.setError(e.getMessage());
		exceptionTemplate.setPath(req.getDescription(false).replace("uri=", ""));
		
		return new ResponseEntity<ExceptionTemplate>(exceptionTemplate, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionTemplate> genericExceptionHandler(
			Exception e, WebRequest req
			){
		ExceptionTemplate exceptionTemplate = new ExceptionTemplate();
		exceptionTemplate.setTimestamp(Instant.now());
		exceptionTemplate.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionTemplate.setError(e.getMessage());
		exceptionTemplate.setPath(req.getDescription(false).replace("uri=", ""));
		
		return new ResponseEntity<ExceptionTemplate>(exceptionTemplate, HttpStatus.BAD_REQUEST);
	}
	
}
