package com.OneToMany.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OneToMany.model.Product;
import com.OneToMany.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	private ProductRepository ProductRepository;
	
	public List<String> validate(Product Product) {

		List<String> error = new ArrayList<>();


		if (Product.getName() == null) {
			error.add("Name can not be empty");
		}
		
		
		return error;
	}

	public List<Product> GetAllProduct() {
		List<Product> list = (List<Product>) this.ProductRepository.findAll();
		return list;
	}

	public Optional<Product> GetProduct(Integer id) {
		return this.ProductRepository.findById(id);
	}

	public Product AddProduct(Product Product) {
		return this.ProductRepository.save(Product);
	}

	public boolean deleteProduct(Integer id) {

		boolean exists = ProductRepository.existsById(id);
		if (exists) {
			ProductRepository.deleteById(id);
			return true;
		} else {

			return false;
		}

	}

	public Product UpdateProduct(Integer id, Product Product) {
		
		Product existingProduct = ProductRepository.findById(id).orElse(null);
		existingProduct.setId(Product.getId());
		existingProduct.setName(Product.getName());

		return this.ProductRepository.save(existingProduct);
	}
}
