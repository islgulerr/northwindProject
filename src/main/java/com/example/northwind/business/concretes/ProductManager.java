package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.dataaccess.concretes.ProductRepository;
import com.example.northwind.entities.concretes.Product;

@Service
public class ProductManager implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {

		return productRepository.findAll();
	}

	@Override
	public Product add(Product product) {
		int size = 0;
		for (Product instant : getAll()) {
			if (size > 10) {
				System.out.println("Ürün sayısı 10'u geçti.");
				return null;
			}

			if (product.getCategoryId() == instant.getCategoryId()) {
				size += 1;
			}
		}

		if (product.getProductName().length() < 2) {
			System.out.println("Ürün ismi en az 2 karakterli olmalıdır.!");
			return null;
		} else {
			return productRepository.save(product);

		}
	}

	@Override
	public Map<String, Boolean> update(Product product) throws Exception {
		Product productToUpdateProduct = productRepository.findById(product.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip ürün yok : " + product.getId()));

		productToUpdateProduct.setProductName(product.getProductName());
		productToUpdateProduct.setUnitPrice(product.getUnitPrice());
		productToUpdateProduct.setQuantityPerUnit(product.getQuantityPerUnit());
		productToUpdateProduct.setUnitOnOrder(product.getUnitOnOrder());
		productToUpdateProduct.setReorderLevel(product.getReorderLevel());
		productToUpdateProduct.setDiscontinued(product.getDiscontinued());
		productToUpdateProduct.setSupplierId(product.getSupplierId());
		productToUpdateProduct.setUnitInStock(product.getUnitInStock());

		productRepository.save(productToUpdateProduct);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Ürün bilgileri güncellendi", Boolean.TRUE);
		return response;
	}

	@Override
	public Map<String, Boolean> delete(Product product) throws Exception {
		Product productDelete = productRepository.findById(product.getId())
				.orElseThrow(() -> new Exception("Bu kimliğe sahip ürün yok : " + product.getId()));

		productRepository.delete(productDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Ürün silindi", Boolean.TRUE);
		return response;
	}

	@Override
	public Map<String, Boolean> getById(@PathVariable(value = "id") Integer productId) throws Exception {
		Product productGetById = productRepository.findById(productId)
				.orElseThrow(() -> new Exception("Bu kimliğe sahip ürün yok : " + productId));

		Map<String, Boolean> response = new HashMap<>();
		response.put(productGetById + "  ID'si girilen ürünün bilgileri getirildi.", Boolean.TRUE);
		return response;
	}

	@Override
	public Optional<Product> findById(Integer productId) {
		return productRepository.findById(productId);
	}

}
