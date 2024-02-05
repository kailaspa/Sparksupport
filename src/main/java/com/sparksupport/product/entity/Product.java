package com.sparksupport.product.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a product.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

	/**
	 * The unique identifier of the product.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * The name of the product.
	 */
	@NotBlank(message = "Name cannot be blank")
	private String name;

	/**
	 * The description of the product.
	 */
	private String description;

	/**
	 * The price of the product.
	 */
	@NotNull(message = "Price cannot be null")
	@PositiveOrZero(message = "Price must be positive or zero")
	private double price;

	/**
	 * The available quantity of the product.
	 */
	@NotNull(message = "Quantity cannot be null")
	@PositiveOrZero(message = "Quantity must be positive or zero")
	private int quantity;

	/**
	 * List of Sale objects representing sales associated with the product.
	 */
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Sale> sales;

}
