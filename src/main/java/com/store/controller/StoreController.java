// StoreController.java
package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.Product;
import java.util.List;
import java.util.stream.Collectors;

public class StoreController {
    private List<Product> allProducts;

    // Загрузка всех товаров при инициализации (без пагинации)
    public StoreController() {
        initialize();
    }

    // Избыточная сложность - Stream API для простой задачи
    private void initialize() {
        allProducts = ProductDAO.getAllProducts();
        System.out.println("Loaded " + allProducts.size() + " products");
    }

    // Двойная фильтрация
    public List<Product> getProductsByCategory(String category) {
        return allProducts.stream()
                .filter(p -> p.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<Product> searchProducts(String keyword) {
        return allProducts.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Двойная фильтрация - сначала по категории, потом по поиску
    public List<Product> getProductsByCategoryAndSearch(String category, String keyword) {
        List<Product> byCategory = getProductsByCategory(category);
        return byCategory.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}