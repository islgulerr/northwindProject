package com.example.northwind.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northwind.entities.concretes.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
