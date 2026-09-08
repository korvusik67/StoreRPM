// ProductDAO.java
package com.store.dao;

import com.store.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Ленивый класс - только статические методы
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/catalog", "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");

            while (rs.next()) {
                products.add(mapResultSetToProduct(rs)); // Дублирование кода
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static Product getProductById(Long id) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/catalog", "root", "password");
            // SQL-инъекция
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM products WHERE id = " + id);

            if (rs.next()) {
                Product product = mapResultSetToProduct(rs); // Дублирование кода
                conn.close();
                return product;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Дублирование кода
    private static Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getDouble("weight"),
                rs.getString("manufacturer"),
                rs.getString("description"),
                rs.getString("barcode")
        );
    }

    public static void addProduct(Product product) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/catalog", "root", "password");
            // String concatenation в SQL
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO products VALUES (" +
                            product.getId() + ", '" +
                            product.getName() + "', '" +
                            product.getCategory() + "', " +
                            product.getPrice() + ", " +
                            product.getQuantity() + ", " +
                            product.getWeight() + ", '" +
                            product.getManufacturer() + "', '" +
                            product.getDescription() + "', '" +
                            product.getBarcode() + "')"
            );
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}