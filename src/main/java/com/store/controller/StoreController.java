package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.Product;
import java.util.ArrayList;
import java.util.List;


public class StoreController {
    private static final int DEFAULT_PAGE_SIZE = 20;


    public List<Product> getProducts(int page) {
        return ProductDAO.getProductsPaginated(page, DEFAULT_PAGE_SIZE);
    }

    public List<Product> getProductsByCategoryAndSearch(String category, String keyword) {
        List<Product> result = new ArrayList<>();
        int page = 0;
        boolean hasMore = true;

        // постраничная загрузка с фильтрацией
        while (hasMore) {
            List<Product> pageProducts = ProductDAO.getProductsPaginated(page, DEFAULT_PAGE_SIZE);
            if (pageProducts.isEmpty()) {
                hasMore = false;
            } else {
                // одна фильтрация вместо двойной
                for (Product product : pageProducts) {
                    boolean categoryMatch = category == null || category.isEmpty() ||
                            product.getCategory().equals(category);
                    boolean keywordMatch = keyword == null || keyword.isEmpty() ||
                            product.getName().toLowerCase()
                                    .contains(keyword.toLowerCase());

                    if (categoryMatch && keywordMatch) {
                        result.add(product);
                    }
                }
                page++;
            }
        }
        return result;
    }

    public List<Product> searchProducts(String keyword) {
        return getProductsByCategoryAndSearch(null, keyword);
    }


    public List<Product> getProductsByCategory(String category) {
        return getProductsByCategoryAndSearch(category, null);
    }

    public void addProduct(Product product) {
        ProductDAO.addProduct(product);
    }


    public void updateProduct(Product product) {
        ProductDAO.updateProduct(product);
    }


    public void deleteProduct(Long id) {
        ProductDAO.deleteProduct(id);
    }


    public int getTotalProductCount() {
        return ProductDAO.getTotalCount();
    }
}