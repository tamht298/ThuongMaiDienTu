package com.shopping4th.ecommerce.rest;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping4th.ecommerce.entity.Product;
import com.shopping4th.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductRest {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> getAllProduct(){
		return this.productService.findAll();
	}
	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		boolean isProduct = this.productService.existsById(id);
		if(!isProduct) {
			throw new RuntimeException("Product "+ id + " is not found");
		}
		
		return productService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id) {
		boolean isProduct = this.productService.existsById(id);
		if(!isProduct) {
			throw new RuntimeException("Product "+ id + " is not found");
		}
		
		productService.deletedById(id);
		return "Deleted Product id "+ id;
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		if(!productService.existsById(id)) {
			throw new EntityNotFoundException("Product "+ id + " is not found");
		}
		
		product.setId(id);
		productService.save(product);
		return this.productService.findById(id);
	}
	
	@PostMapping
	public Product createProduct(@Valid @RequestBody Product product) {
		product.setCreatedAt(new Date());
		productService.save(product);
		return productService.findById(product.getId());
	}
	
}
