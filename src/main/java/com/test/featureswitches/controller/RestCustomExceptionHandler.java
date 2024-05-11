package com.test.featureswitches.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.test.featureswitches.entity.RestResponse;

@RestControllerAdvice
public class RestCustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Return missing request parameters to client
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		RestResponse excRes = new RestResponse(HttpStatus.BAD_REQUEST, true, null,
				String.format("Missing parameter with name: %s", ex.getParameterName()));
		return ResponseEntity.badRequest().body(excRes);
	}

	/**
	 * Return fields containing error to client
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<FieldError> fieldsErr = ex.getFieldErrors();

		String errMsg = fieldsErr.stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(", "));

		RestResponse excRes = new RestResponse(HttpStatus.BAD_REQUEST, true, null, errMsg);
		return this.createResponseEntity(excRes, headers, status, request);
	}
}
