package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.northwind.entities.concretes.Category;

public interface ICategoryService {

	List<Category> getAll();

	Category add(Category category);

	Optional<Category> findById(Integer categoryId);

	Map<String, Boolean> update(Category category) throws Exception;

	Map<String, Boolean> delete(Category category) throws Exception;

	Map<String, Boolean> getById(Integer categoryId) throws Exception;

}
