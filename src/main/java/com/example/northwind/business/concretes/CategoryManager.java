package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.dataaccess.concretes.CategoryRepository;
import com.example.northwind.entities.concretes.Category;

@Service
public class CategoryManager implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAll() {

		return categoryRepository.findAll();
	}

	@Override
	public Category add(Category category) {

		return categoryRepository.save(category);
	}

	@Override
	public Map<String, Boolean> update(Category category) throws Exception {
		Category categoryToUpdateCategory = categoryRepository.findById(category.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip kategori yok : " + category.getId()));

		categoryToUpdateCategory.setCategoryName(category.getCategoryName());
		categoryToUpdateCategory.setCategoryDescription(category.getCategoryDescription());

		categoryRepository.save(categoryToUpdateCategory);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Kategori bilgileri güncellendi", Boolean.TRUE);
		return response;
	}

	@Override
	public Map<String, Boolean> delete(Category category) throws Exception {

		Category categoryDelete = categoryRepository.findById(category.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip kategori yok : " + category.getId()));

		categoryRepository.delete(categoryDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Kategori silindi", Boolean.TRUE);
		return response;
	}

	@Override
	public Map<String, Boolean> getById(@PathVariable(value = "id") Integer categoryId) throws Exception {
		Category categoryGetById = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip kategori yok : " + categoryId));

		Map<String, Boolean> response = new HashMap<>();
		response.put(categoryGetById + "  ID'si girilen kategorinin bilgileri getirildi.", Boolean.TRUE);
		return response;
	}

	@Override
	public Optional<Category> findById(Integer categoryId) {
		return categoryRepository.findById(categoryId);
	}

}
