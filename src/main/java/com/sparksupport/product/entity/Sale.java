package com.sparksupport.product.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a sale.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")
public class Sale {

	/**
	 * The unique identifier of the sale.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * The product associated with the sale.
	 */
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	/**
	 * The quantity of the product sold in this sale.
	 */
	@NotNull(message = "Quantity cannot be NULL")
	@Positive(message = "Quantity must be positive")
	private int quantity;

	/**
	 * The date when the sale occurred.
	 */
	@NotNull(message = "Sale Date cannot be NULL")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate saleDate;
}
