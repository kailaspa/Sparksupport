package com.sparksupport.product.exception;

/**
 * Exception thrown when a product is not found.
 */
public class ProductNotFoundException extends RuntimeException {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ProductNotFoundException with the specified detail message.
	 *
	 * @param message the detail message.
	 */
	public ProductNotFoundException(String message) {
		super(message);
	}

}
