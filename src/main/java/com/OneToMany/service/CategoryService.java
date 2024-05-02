package com.OneToMany.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OneToMany.model.Category;
import com.OneToMany.repository.CategoryRepository;


@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<String> validate(Category category) {

		List<String> error = new ArrayList<>();


		if (category.getName() == null) {
			error.add("name can not be empty");
		}
		
		
		return error;
	}

	public List<Category> GetAllCategory() {
		List<Category> list = (List<Category>) this.categoryRepository.findAll();
		return list;
	}

	public Optional<Category> GetCategory(Integer id) {
		return this.categoryRepository.findById(id);
	}

	public Category AddCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	public boolean deleteCategory(Integer id) {

		boolean exists = categoryRepository.existsById(id);
		if (exists) {
			categoryRepository.deleteById(id);
			return true;
		} else {

			return false;
		}

	}

	public Category UpdateCategory(Integer id, Category category) {
		
		Category existingCategory = categoryRepository.findById(id).orElse(null);
		existingCategory.setId(category.getId());
		existingCategory.setName(category.getName());

		return this.categoryRepository.save(existingCategory);
	}
}
