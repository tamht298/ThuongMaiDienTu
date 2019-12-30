package com.shopping4th.ecommerce.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping4th.ecommerce.entity.Accounts;
import com.shopping4th.ecommerce.entity.CartItems;
import com.shopping4th.ecommerce.service.AccountService;
import com.shopping4th.ecommerce.service.CartService;
import com.shopping4th.ecommerce.utilities.CartStatus;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class CartRest {

	public static final Logger logger = LoggerFactory.getLogger(CartRest.class);
	

	private CartService cartService;
	@Autowired
	public CartRest(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Autowired
	private AccountService accountService;

	@GetMapping(value="/carts")
	public List<CartItems> getAllCarts(){
		
		return this.cartService.findAll();
	}
	
	@GetMapping(value = "/carts/{id}")
	public CartItems getCart(@PathVariable Long id) {
		logger.info("Fetching with id {}", id);
		boolean isCart = this.cartService.existsById(id);
		if(!isCart) {
			logger.error("Cart with id {} not found.", id);
			throw new RuntimeException("Cart "+ id + " is not found");
		}
		
		return cartService.findById(id);
	}
	
	@DeleteMapping(value="/carts/{id}")
	public void removeCartItem(@PathVariable Long id) {
		boolean isCartItem = this.cartService.existsById(id);
		if(!isCartItem) {
			logger.error("Cart with id {} not found.", id);
			throw new RuntimeException("Cart item "+ id + " is not found");
		}
		
		this.cartService.deletedById(id);
	}
	
	@DeleteMapping(value="/accounts/{accountId}/carts")
	public void removeCart(@PathVariable Long accountId) {
		Accounts acc = this.accountService.findById(accountId);
		if(!acc.equals(null)) {
			this.cartService.deleteAll(accountId);
			logger.info("Deleted cart accountId {} successfully", accountId);
			
		}
		logger.error("Failed for deleting cart accountId {}", accountId);
	
	}
	
	@PostMapping(value = "/carts")
	public ResponseEntity<CartItems> addItem(@Valid @RequestBody CartItems carts) {
		CartItems existsCart = this.cartService.findByAccountIdAndProductId(carts.getAccount().getId(), carts.getProduct().getId());
		if(!existsCart.equals(null)) {
			
			existsCart.setQuantity(carts.getQuantity()+existsCart.getQuantity());
			
			this.cartService.save(existsCart);
			return ResponseEntity.status(HttpStatus.OK).build();
			//return this.cartService.findById(existsCart.getId());
		}
		else {
			carts.setStatus(CartStatus.NOT_PURCHASED.toString());
			this.cartService.save(carts);
			return ResponseEntity.status(HttpStatus.OK).build();
			//return this.cartService.findById(carts.getId());
		}

	}
	
	@PutMapping(value="/carts/{id}")
	public ResponseEntity<CartItems> updateCart(@PathVariable Long id,@Valid @RequestBody CartItems carts) {
		CartItems existsCart = this.cartService.findById(id);
		if(!existsCart.equals(null)) {
			
			existsCart.setQuantity(carts.getQuantity());
			existsCart.setId(id);
			this.cartService.save(existsCart);
			return ResponseEntity.status(HttpStatus.OK).build();
			//return this.cartService.findById(existsCart.getId());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}