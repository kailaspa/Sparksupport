package com.sparksupport.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sparksupport.product.dto.ProductDto;
import com.sparksupport.product.entity.Product;
import com.sparksupport.product.exception.ProductNotFoundException;
import com.sparksupport.product.exception.ProductOperationException;

public interface IProductService {

	/**
	 * Retrieves all products with pagination support.
	 *
	 * @param pageable Pagination information.
	 * @return A Page of ProductDto objects.
	 */
	public Page<ProductDto> getAllProducts(Pageable pageable);

	/**
	 * Retrieves a specific product by its ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return The ProductDto representation of the found product.
	 * @throws ProductNotFoundException If the product with the specified ID is not
	 *                                  found.
	 */
	public ProductDto getProductById(int id);

	/**
	 * Adds a new product and associates any sales with it.
	 *
	 * @param product The product to be added.
	 * @return The ProductDto representation of the saved product.
	 * @throws ProductOperationException If an error occurs while adding the
	 *                                   product.
	 */
	public ProductDto addProduct(Product product);

	/**
	 * Updates an existing product with the specified ID.
	 *
	 * @param id      The ID of the product to be updated.
	 * @param product The updated product data.
	 * @return The ProductDto representation of the updated product.
	 * @throws ProductNotFoundException  If the product with the specified ID is not
	 *                                   found.
	 * @throws ProductOperationException If an error occurs while updating the
	 *                                   product.
	 */
	public ProductDto updateProduct(int id, Product product);

	/**
	 * Deletes a product with the specified ID.
	 *
	 * @param id The ID of the product to be deleted.
	 * @return A message indicating the success of the deletion.
	 * @throws ProductOperationException If an error occurs while deleting the
	 *                                   product.
	 */
	public String deleteProduct(int id);

	/**
	 * Calculates the total revenue generated by all sales.
	 *
	 * @return The total revenue.
	 */
	public double getTotalRevenue();

	/**
	 * Calculates the total revenue generated by a specific product.
	 *
	 * @param productId The ID of the product.
	 * @return The total revenue for the specified product.
	 * @throws ProductNotFoundException If the product with the specified ID is not
	 *                                  found.
	 */
	public double getRevenueByProduct(int productId);

}
