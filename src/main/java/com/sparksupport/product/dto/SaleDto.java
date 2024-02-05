package com.sparksupport.product.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a sale.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {

	/**
	 * The unique identifier of the sale.
	 */
	private int id;

	/**
	 * The quantity of the product sold in this sale.
	 */
	private int quantity;

	/**
	 * The date when the sale occurred.
	 */
	private LocalDate saleDate;

}
