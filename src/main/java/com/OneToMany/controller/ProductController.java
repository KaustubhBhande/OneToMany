package com.OneToMany.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OneToMany.model.Product;
import com.OneToMany.service.ProductService;
@RestController
@RequestMapping("/Product")
public class ProductController {
	@Autowired
	private ProductService ProductService;

	@GetMapping("")
	public Object GetAllCategories() {
		List<Product> students = ProductService.GetAllProduct();
		if (students.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		return ResponseEntity.ok("Found");
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> GetProduct(@PathVariable("id") Integer id) {
		Optional<Product> ProductById = ProductService.GetProduct(id);
		if (ProductById == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product by id not found");

		} else {
			Product Product = ProductById.get();

			return (ResponseEntity<String>) ResponseEntity.ok().body("Found");
		}
	}

	@PostMapping(" ")
	public ResponseEntity<?> CreateProduct(@RequestBody Product Product) {

		List<String> error = ProductService.validate(Product);
		if (error.size() != 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}

		ProductService.AddProduct(Product);
		return ResponseEntity.ok().body("Product added successfully.");
	}

	@DeleteMapping("/{id}")
	public Object DeleteProduct(@PathVariable("id") Integer id) {
		boolean deleted = ProductService.deleteProduct(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("Product with ID " + id + " Deleted successfully.");
		}

		else

		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " Not Found.");

		}
	}

	@PutMapping("/{id}")
	public Object UpdateProduct(@PathVariable("id") Integer id, @RequestBody Product Product) {

		List<String> errors = ProductService.validate(Product);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}
		// Check if Product exists
		Optional<Product> existingProduct = ProductService.GetProduct(id);
		if (!existingProduct.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id " + id + "Not Found.");
		}

		// Update the Product
		ProductService.UpdateProduct(id, Product);
		ResponseEntity.ok().body("Product with ID " + id + " Updated Successfully.");
		return this.ProductService.UpdateProduct(id, Product);

	}
}