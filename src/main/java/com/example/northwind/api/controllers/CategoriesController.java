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

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.entities.concretes.Category;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

	@Autowired
	ICategoryService categoryService;

	@GetMapping("/categories")
	public Map<String, Boolean> getAll() throws Exception {
		List<Category> categoryGetAll = categoryService.getAll();
		Map<String, Boolean> response = new HashMap<>();

		response.put(categoryGetAll + " Kategoriler listelendi.", Boolean.TRUE);
		return response;
	}

	@PostMapping("/categories")
	public Map<String, Boolean> add(@RequestBody Category category) throws Exception {
		Category categoryAdd = categoryService.add(category);
		Map<String, Boolean> response = new HashMap<>();

		response.put(categoryAdd + "Kategori eklendi.", Boolean.TRUE);
		return response;

	}

	@PutMapping("/categories")
	public Map<String, Boolean> update(@RequestBody Category category) throws Exception {
		Category categoryToUpdateCategory = categoryService.findById(category.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip kategori yok : " + category.getId()));

		categoryToUpdateCategory.setCategoryName(category.getCategoryName());
		categoryToUpdateCategory.setCategoryDescription(category.getCategoryDescription());
		categoryToUpdateCategory.setCategoryPicture(category.getCategoryPicture());

		categoryService.add(categoryToUpdateCategory);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Kategori bilgileri güncellendi", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/categories")
	public Map<String, Boolean> delete(@RequestBody Category category) throws Exception {

		Category categoryDelete = categoryService.findById(category.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip kategori yok : " + category.getId()));

		categoryService.delete(categoryDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Kategori silindi", Boolean.TRUE);
		return response;
	}

	@GetMapping("/categories/{id}")
	public Map<String, Boolean> getById(@PathVariable(value = "id") Integer categoryId) throws Exception {
		Category categoryGetById = categoryService.findById(categoryId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip kategori yok : " + categoryId));

		Map<String, Boolean> response = new HashMap<>();
		response.put(categoryGetById + "  ID'si girilen kategori bilgileri getirildi.", Boolean.TRUE);
		return response;
	}

}
