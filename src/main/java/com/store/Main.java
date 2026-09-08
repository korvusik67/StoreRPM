package com.store;

import com.store.controller.StoreController;
import com.store.model.Product;
import com.store.model.dto.ProductData;
import com.store.util.DatabaseMigration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //применяем миграии эшкере67
        DatabaseMigration.migrate();

        System.out.println("Управление каталогом товаров\n");

        StoreController controller = new StoreController();

        // получаем общее количество товаров
        int total = controller.getTotalProductCount();
        System.out.println("всего товаров: " + total + "\n");

        // получаем товары с пагинацией (первая страница)
        List<Product> products = controller.getProducts(0);
        System.out.println("товары на первой странице:");
        for (Product p : products) {
            System.out.println("  " + p);
        }

        // поиск по категории
        System.out.println("\nтовары в категории 'электроника':");
        List<Product> electronics = controller.getProductsByCategory("электроника");
        for (Product p : electronics) {
            System.out.println("  " + p);
        }

        // поиск по ключевому слову
        System.out.println("\nпоиск по слову 'игровой':");
        List<Product> searchResults = controller.searchProducts("игровой");
        for (Product p : searchResults) {
            System.out.println("  " + p);
        }

        // комбинированный поиск
        System.out.println("\nкомпьютеры с ценой до 10000:");
        List<Product> filtered = controller.getProductsByCategoryAndSearch("компьютеры", null);
        for (Product p : filtered) {
            if (p.getPrice() < 10000) {
                System.out.println("  " + p);
            }
        }
    }
}