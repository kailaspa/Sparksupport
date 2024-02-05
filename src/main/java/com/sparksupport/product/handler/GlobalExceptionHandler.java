package com.sparksupport.product.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparksupport.product.exception.ProductNotFoundException;
import com.sparksupport.product.exception.ProductOperationException;

import lombok.extern.log4j.Log4j2;

/**
 * Global exception handler for handling various exceptions in the application.
 */
@RestControllerAdvice
@Component
@Log4j2
public class GlobalExceptionHandler {

	/**
	 * Handles validation errors during method argument validation.
	 *
	 * @param e the MethodArgumentNotValidException
	 * @return ResponseEntity containing error details and HTTP status code.
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorDto> handleValidationError(MethodArgumentNotValidException e) {

		log.error("Method Argument Not valid!", e);

		List<FieldError> errors = e.getBindingResult().getFieldErrors();

		ErrorDto errorDto = new ErrorDto();
		for (FieldError error : errors) {
			errorDto.addMessage(error.getDefaultMessage());
			// errorDto.addMessage(error.getField() + " " + error.getDefaultMessage());
		}
		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles internal server errors.
	 *
	 * @param e the Exception
	 * @return ResponseEntity containing error details and HTTP status code.
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorDto> handleInternalServerError(Exception e) {

		log.error(e.getMessage(), e);

		ErrorDto errorDto = new ErrorDto();
		errorDto.addMessage("Internal Server Error.");
		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles ProductNotFoundException and returns a ResponseEntity with NOT_FOUND
	 * status.
	 *
	 * @param e the ProductNotFoundException
	 * @return ResponseEntity containing error message and HTTP status code.
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {

		log.error("Product with specific id not present", e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles ProductOperationException and returns a ResponseEntity with
	 * INTERNAL_SERVER_ERROR status.
	 *
	 * @param e the ProductOperationException
	 * @return ResponseEntity containing error message and HTTP status code.
	 */
	@ExceptionHandler(ProductOperationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleProductOperationException(ProductOperationException e) {

		log.error("Product operation not possible", e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
