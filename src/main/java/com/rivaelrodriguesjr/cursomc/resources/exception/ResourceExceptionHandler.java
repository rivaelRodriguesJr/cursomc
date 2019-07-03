package com.rivaelrodriguesjr.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rivaelrodriguesjr.cursomc.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){		
		StandartError err = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
