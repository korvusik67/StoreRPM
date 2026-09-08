package com.store.controller;

import com.store.dao.ProductDAO;
import com.store.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * контроллер для управления каталогом товаров
 * обрабатывает бизнес-логику и фильтрацию
 */
public class StoreController {
    private static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * получает товары с пагинацией
     * @param page номер страницы
     * @return список товаров
     */
    public List<Product> getProducts(int page) {
        return ProductDAO.getProductsPaginated(page, DEFAULT_PAGE_SIZE);
    }

    /**
     * единый метод фильтрации - однопроходная фильтрация
     * решает проблему двойной фильтрации
     * @param category категория (может быть null)
     * @param keyword ключевое слово (может быть null)
     * @return отфильтрованный список товаров
     */
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

    /**
     * поиск товаров по ключевому слову
     * @param keyword ключевое слово для поиска
     * @return список найденных товаров
     */
    public List<Product> searchProducts(String keyword) {
        return getProductsByCategoryAndSearch(null, keyword);
    }

    /**
     * получает товары по категории
     * @param category название категории
     * @return список товаров в категории
     */
    public List<Product> getProductsByCategory(String category) {
        return getProductsByCategoryAndSearch(category, null);
    }

    /**
     * добавляет товар в каталог
     * @param product товар для добавления
     */
    public void addProduct(Product product) {
        ProductDAO.addProduct(product);
    }

    /**
     * обновляет информацию о товаре
     * @param product товар с обновленными данными
     */
    public void updateProduct(Product product) {
        ProductDAO.updateProduct(product);
    }

    /**
     * удаляет товар из каталога
     * @param id идентификатор товара
     */
    public void deleteProduct(Long id) {
        ProductDAO.deleteProduct(id);
    }

    /**
     * получает общее количество товаров
     * @return количество товаров в каталоге
     */
    public int getTotalProductCount() {
        return ProductDAO.getTotalCount();
    }
}