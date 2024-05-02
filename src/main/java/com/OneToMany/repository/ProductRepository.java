package com.OneToMany.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.OneToMany.model.Category;
import com.OneToMany.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	Optional<Product> findById(Integer id);
}
