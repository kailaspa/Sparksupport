package com.sparksupport.product.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a product.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	/**
	 * The unique identifier of the product.
	 */
	private int id;

	/**
	 * The name of the product.
	 */
	private String name;

	/**
	 * The description of the product.
	 */
	private String description;

	/**
	 * The price of the product.
	 */
	private double price;

	/**
	 * The available quantity of the product.
	 */
	private int quantity;

	/**
	 * List of SaleDto objects representing sales associated with the product.
	 */
	private List<SaleDto> sales;

}
