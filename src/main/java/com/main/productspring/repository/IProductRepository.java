package com.main.productspring.repository;

import com.main.productspring.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void update(int id, Product product);

    void remove(int id);

    List<String> findNameProduct();

    List<Product> search(String name);
}