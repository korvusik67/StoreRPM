package com.store.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * утилита для подключения к базе данных
 * автоматически создает бд и таблицы если их нет
 */
public class DatabaseConnection {
    // параметры подключения для openserver
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "catalog";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * инициализация базы данных при первом запуске
     */
    static {
        try {
            createDatabaseIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * создает базу данных и таблицы если их нет
     */
    private static void createDatabaseIfNotExists() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // создаем бд если нет
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);

            // переключаемся на нашу бд
            stmt.executeUpdate("USE " + DB_NAME);

            // создаем таблицу если нет
            String createTable = "CREATE TABLE IF NOT EXISTS products (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "category VARCHAR(100) NOT NULL, " +
                    "price DECIMAL(10,2) NOT NULL, " +
                    "quantity INT NOT NULL DEFAULT 0, " +
                    "weight DECIMAL(10,2) DEFAULT 0.0, " +
                    "manufacturer VARCHAR(100) DEFAULT 'Unknown', " +
                    "description TEXT, " +
                    "barcode VARCHAR(50) UNIQUE, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)";
            stmt.executeUpdate(createTable);

            // добавляем индексы
            stmt.executeUpdate("CREATE INDEX IF NOT EXISTS idx_category ON products(category)");
            stmt.executeUpdate("CREATE INDEX IF NOT EXISTS idx_name ON products(name)");
            stmt.executeUpdate("CREATE INDEX IF NOT EXISTS idx_price ON products(price)");
            stmt.executeUpdate("CREATE INDEX IF NOT EXISTS idx_barcode ON products(barcode)");

            // проверяем есть ли данные, если нет - добавляем тестовые
            checkAndInsertTestData(stmt);
        }
    }

    /**
     * добавляет тестовые данные если таблица пустая
     */
    private static void checkAndInsertTestData(Statement stmt) throws SQLException {
        // проверяем есть ли записи
        java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products");
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();

        // если записей нет, добавляем тестовые
        if (count == 0) {
            String insertData =
                    "INSERT INTO products (name, category, price, quantity, weight, manufacturer, description, barcode) VALUES " +
                            "('ноутбук asus rog', 'электроника', 89999.99, 15, 2.5, 'asus', 'игровой ноутбук с rtx 3070', 'ASUS-001'), " +
                            "('смартфон samsung galaxy', 'электроника', 49999.00, 30, 0.2, 'samsung', 'смартфон с 6.7\" экраном', 'SAMS-002'), " +
                            "('наушники sony wh-1000', 'аудио', 24999.00, 45, 0.25, 'sony', 'беспроводные наушники с шумоподавлением', 'SONY-003'), " +
                            "('клавиатура logitech mx', 'компьютеры', 8990.00, 20, 0.8, 'logitech', 'беспроводная клавиатура', 'LOG-004'), " +
                            "('мышь razer viper', 'компьютеры', 5990.00, 35, 0.1, 'razer', 'игровая мышь', 'RAZ-005'), " +
                            "('монитор dell ultra', 'компьютеры', 29999.00, 10, 5.5, 'dell', '27\" 4k монитор', 'DELL-006'), " +
                            "('колонки jbl charge', 'аудио', 8990.00, 25, 1.2, 'jbl', 'портативная колонка', 'JBL-007'), " +
                            "('планшет apple ipad', 'электроника', 45999.00, 18, 0.5, 'apple', '10.2\" планшет', 'APPL-008')";
            stmt.executeUpdate(insertData);
        }
    }

    /**
     * получение соединения с бд
     * @return connection объект подключения
     * @throws SQLException при ошибке подключения
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // подключаемся напрямую к нашей бд
        return DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
    }
}