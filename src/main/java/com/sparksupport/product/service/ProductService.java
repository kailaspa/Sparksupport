package com.sparksupport.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sparksupport.product.dto.ProductDto;
import com.sparksupport.product.dto.SaleDto;
import com.sparksupport.product.entity.Product;
import com.sparksupport.product.entity.Sale;
import com.sparksupport.product.exception.ProductNotFoundException;
import com.sparksupport.product.exception.ProductOperationException;
import com.sparksupport.product.repository.ProductRepository;
import com.sparksupport.product.repository.SaleRepository;

import jakarta.transaction.Transactional;

/**
 * Service class for managing Product entities and related operations.
 */
@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SaleRepository saleRepository;

	/**
	 * Retrieves all products with pagination support.
	 *
	 * @param pageable Pagination information.
	 * @return A Page of ProductDto objects.
	 */
	public Page<ProductDto> getAllProducts(Pageable pageable) {

		// Add sorting by product name in ascending order
//	    Pageable pageableWithSorting = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//	            Sort.by(Sort.Direction.ASC, "name"));

		Page<Product> productPage = productRepository.findAll(pageable);
		return productPage.map(this::mapProductToProductDto);
	}

	/**
	 * Retrieves a specific product by its ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return The ProductDto representation of the found product.
	 * @throws ProductNotFoundException If the product with the specified ID is not
	 *                                  found.
	 */
	public ProductDto getProductById(int id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

		return mapProductToProductDto(product);

	}

	/**
	 * Maps a Product entity to its DTO representation.
	 *
	 * @param product The Product entity to map.
	 * @return The corresponding ProductDto.
	 */
	private ProductDto mapProductToProductDto(Product product) {
		ProductDto productDto = new ProductDto();
		// Map basic properties
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setQuantity(product.getQuantity());

		if (product.getSales() != null) {
			List<SaleDto> saleDtos = product.getSales().stream().map(this::mapSaleToSaleDto)
					.collect(Collectors.toList());
			productDto.setSales(saleDtos);
		}

		return productDto;
	}

	/**
	 * Maps a Sale entity to its DTO representation.
	 *
	 * @param sale The Sale entity to map.
	 * @return The corresponding SaleDto.
	 */
	private SaleDto mapSaleToSaleDto(Sale sale) {
		SaleDto saleDto = new SaleDto();
		// Map properties
		saleDto.setId(sale.getId());
		saleDto.setQuantity(sale.getQuantity());
		saleDto.setSaleDate(sale.getSaleDate());
		return saleDto;
	}

	/**
	 * Adds a new product and associates any sales with it.
	 *
	 * @param product The product to be added.
	 * @return The ProductDto representation of the saved product.
	 * @throws ProductOperationException If an error occurs while adding the
	 *                                   product.
	 */
	public ProductDto addProduct(Product product) {
		try {
			Product savedProduct = productRepository.save(product);

			addSalesForProduct(savedProduct);
			if (product != null) {
				return mapProductToProductDto(savedProduct);
			}
			return null;
		} catch (Exception e) {
			throw new ProductOperationException("Error adding product", e);
		}
	}

	/**
	 * Adds sales to the specified product by setting the product reference for each
	 * sale and saving them to the database.
	 *
	 * @param product The product to which sales will be associated.
	 */
	private void addSalesForProduct(Product product) {
		if (product.getSales() != null) {
			for (Sale sale : product.getSales()) {
				sale.setProduct(product); // Set the reference to the Product
				saleRepository.save(sale);
			}
		}
	}

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
	public ProductDto updateProduct(int id, Product product) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

		existingProduct.setDescription(product.getDescription());
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());

		try {
			Product savedProduct = productRepository.save(existingProduct);

			return mapProductToProductDto(savedProduct);

		} catch (Exception e) {
			// Handle specific exceptions or log the error
			throw new ProductOperationException("Error updating product", e);
		}
	}

	/**
	 * Deletes a product with the specified ID.
	 *
	 * @param id The ID of the product to be deleted.
	 * @return A message indicating the success of the deletion.
	 * @throws ProductOperationException If an error occurs while deleting the
	 *                                   product.
	 */
	public String deleteProduct(int id) {
		try {
			productRepository.deleteById(id);
			return "Product removed: " + id;
		} catch (Exception e) {
			// Handle specific exceptions or log the error
			throw new ProductOperationException("Error deleting product", e);
		}
	}

	/**
	 * Calculates the total revenue generated by all sales.
	 *
	 * @return The total revenue.
	 */
	public double getTotalRevenue() {
		List<Sale> allSales = saleRepository.findAll();
		double totalRevenue = 0.0;

		for (Sale sale : allSales) {
			totalRevenue += sale.getQuantity() * sale.getProduct().getPrice();
		}

		return totalRevenue;
	}

	/**
	 * Calculates the total revenue generated by a specific product.
	 *
	 * @param productId The ID of the product.
	 * @return The total revenue for the specified product.
	 * @throws ProductNotFoundException If the product with the specified ID is not
	 *                                  found.
	 */
	public double getRevenueByProduct(int productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

		if (product != null && (product.getSales() != null && !product.getSales().isEmpty())) {
			List<Sale> productSales = product.getSales();
			double totalRevenue = 0.0;

			for (Sale sale : productSales) {
				totalRevenue += sale.getQuantity() * sale.getProduct().getPrice();
			}

			return totalRevenue;
		}

		return 0.0; // Product not found, return 0 revenue
	}

}
