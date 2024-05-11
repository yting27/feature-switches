package com.test.featureswitches.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.test.featureswitches.entity.ExceptionResponse;


@RestControllerAdvice
public class RestCustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Return missing request parameters to client
	 */
	@Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.badRequest().body(String.format("Missing parameter with name: %s", ex.getParameterName()));
    }
	
	/**
	 * Return fields containing error to client
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<FieldError> fieldsErr = ex.getFieldErrors();
		
		String errMsg = fieldsErr.stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(", "));
		
		ExceptionResponse excRes = new ExceptionResponse(errMsg);
		return this.createResponseEntity(excRes, headers, status, request);
	}
}
