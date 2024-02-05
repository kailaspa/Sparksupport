package com.sparksupport.product.handler;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) for handling error messages.
 */
@Data
public class ErrorDto {

	/**
	 * List of error messages.
	 */
	@ToString.Include
	private List<String> messages = new ArrayList<>(0);

	/**
	 * Adds a new error message to the list.
	 *
	 * @param message the error message to be added.
	 */
	public void addMessage(String message) {
		this.messages.add(message);
	}

}
