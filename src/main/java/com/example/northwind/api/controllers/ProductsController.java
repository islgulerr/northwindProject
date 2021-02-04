package com.example.northwind.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.entities.concretes.Product;

@RestController
@RequestMapping("/api/v1")
public class ProductsController {

	@Autowired
	IProductService productService;

	@GetMapping("/products")
	public Map<String, Boolean> getAll() throws Exception {
		List<Product> productGetAll = productService.getAll();
		Map<String, Boolean> response = new HashMap<>();

		response.put(productGetAll + "Ürünler listelendi.", Boolean.TRUE);
		return response;
	}

	@PostMapping("/products")
	public Map<String, Boolean> add(@RequestBody Product product) throws Exception {
		Product productAdd = productService.add(product);
		Map<String, Boolean> response = new HashMap<>();
		if (productAdd == null) {
			response.put("Ürün eklenemedi.", Boolean.FALSE);
			return response;
		} else {
			response.put(productAdd + "Ürün eklendi.", Boolean.TRUE);
			return response;
		}
	}

	@PutMapping("/products")
	public Map<String, Boolean> update(@RequestBody Product product) throws Exception {
		Product productToUpdateProduct = productService.findById(product.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip ürün yok : " + product.getId()));

		productToUpdateProduct.setProductName(product.getProductName());
		productToUpdateProduct.setUnitPrice(product.getUnitPrice());
		productToUpdateProduct.setQuantityPerUnit(product.getQuantityPerUnit());
		productToUpdateProduct.setUnitOnOrder(product.getUnitOnOrder());
		productToUpdateProduct.setReorderLevel(product.getReorderLevel());
		productToUpdateProduct.setDiscontinued(product.getDiscontinued());
		productToUpdateProduct.setSupplierId(product.getSupplierId());
		productToUpdateProduct.setUnitInStock(product.getUnitInStock());

		productService.add(productToUpdateProduct);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Ürün bilgileri güncellendi", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/products")
	public Map<String, Boolean> delete(@RequestBody Product product) throws Exception {
		Product productDelete = productService.findById(product.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip ürün yok : " + product.getId()));

		productService.delete(productDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Ürün silindi", Boolean.TRUE);
		return response;
	}

	@GetMapping("/products/{id}")
	public Map<String, Boolean> getById(@PathVariable(value = "id") Integer productId) throws Exception {
		Product productGetById = productService.findById(productId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip ürün yok : " + productId));

		Map<String, Boolean> response = new HashMap<>();
		response.put(productGetById + "  ID'si girilen ürünün bilgileri getirildi.", Boolean.TRUE);
		return response;
	}

}
