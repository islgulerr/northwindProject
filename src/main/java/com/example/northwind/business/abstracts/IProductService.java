package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.northwind.entities.concretes.Product;

public interface IProductService {

	List<Product> getAll();

	Product add(Product product);

	Optional<Product> findById(Integer productId);

	Map<String, Boolean> update(Product product) throws Exception;

	Map<String, Boolean> delete(Product product) throws Exception;

	Map<String, Boolean> getById(Integer productId) throws Exception;

}
