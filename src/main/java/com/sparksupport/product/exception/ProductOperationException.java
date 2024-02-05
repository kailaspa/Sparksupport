package com.sparksupport.product.exception;

/**
 * Exception thrown when an operation on a product encounters an error.
 */
public class ProductOperationException extends RuntimeException{
	
	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new ProductOperationException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
	public ProductOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
