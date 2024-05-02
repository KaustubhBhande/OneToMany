package com.OneToMany.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.OneToMany.model.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	 Optional<Category> findById(Integer id);
}
