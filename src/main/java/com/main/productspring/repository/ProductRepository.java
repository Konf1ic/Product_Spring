package com.main.productspring.repository;

import com.main.productspring.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository implements IProductRepository {
    private static Map<Integer, Product> productMap;
    private static List<String> nameProductList = new ArrayList<>();

    static {
        productMap = new HashMap<>();
        productMap.put(1, new Product(1, "TV", 100.0, "Sony 4K", "Sony"));
        productMap.put(2, new Product(2, "Phone", 90.0, "Iphone 13", "Apple"));
        productMap.put(3, new Product(3, "Fridge", 120.0, "SamSung Bespoke", "SamSung"));

        nameProductList.add("TV");
        nameProductList.add("Phone");
        nameProductList.add("Fridge");
        nameProductList.add("Washing machine");
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void save(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return productMap.get(id);
    }

    @Override
    public void update(int id, Product product) {
        productMap.put(id, product);
    }

    @Override
    public void remove(int id) {
        productMap.remove(id);
    }

    @Override
    public List<String> findNameProduct() {
        return nameProductList;
    }

    @Override
    public List<Product> search(String name) {
        List<Product> productList = new ArrayList<>(productMap.values());
//        List rỗng để lấy name khi search
        List<Product> products = new ArrayList<>();
        for (Product product: productList) {
            if (product.getNameProduct().contains(name)) {
                products.add(product);
            }
        }
        return products;
    }
}