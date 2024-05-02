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
import com.OneToMany.model.Category;
import com.OneToMany.service.CategoryService;

@RestController
@RequestMapping("/Category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public Object GetAllCategories() {
		List<Category> students = categoryService.GetAllCategory();
		if (students.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		}
		return ResponseEntity.ok("Found");
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> GetCategory(@PathVariable("id") Integer id) {
		Optional<Category> CategoryById = categoryService.GetCategory(id);
		if (CategoryById == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category by id not found");

		} else {
			Category category = CategoryById.get();

			return (ResponseEntity<String>) ResponseEntity.ok().body("Found");
		}
	}

	@PostMapping(" ")
	public ResponseEntity<?> CreateCategory(@RequestBody Category category) {

		List<String> error = categoryService.validate(category);
		if (error.size() != 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}

		categoryService.AddCategory(category);
		return ResponseEntity.ok().body("Category added successfully.");
	}

	@DeleteMapping("/{id}")
	public Object DeleteCategory(@PathVariable("id") Integer id) {
		boolean deleted = categoryService.deleteCategory(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("Category with ID " + id + " Deleted successfully.");
		}

		else

		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with ID " + id + " Not Found.");

		}
	}

	@PutMapping("/{id}")
	public Object UpdateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {

		List<String> errors = categoryService.validate(category);
		if (!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}
		// Check if Category exists
		Optional<Category> existingCategory = categoryService.GetCategory(id);
		if (!existingCategory.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id " + id + "Not Found.");
		}

		// Update the Category
		categoryService.UpdateCategory(id, category);
		ResponseEntity.ok().body("Category with ID " + id + " Updated Successfully.");
		return this.categoryService.UpdateCategory(id, category);

	}
}