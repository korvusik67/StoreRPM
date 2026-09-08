
package com.store.model;

public class Product {
    // Константы для значений по умолчанию
    public static final double DEFAULT_WEIGHT = 0.0;
    public static final String DEFAULT_MANUFACTURER = "Unknown";
    public static final int DEFAULT_QUANTITY = 0;
    public static final double DEFAULT_PRICE = 0.0;

    private Long id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private double weight;
    private String manufacturer;
    private String description;
    private String barcode;


    public Product(Long id, String name, String category, double price,
                   int quantity, double weight, String manufacturer,
                   String description, String barcode) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight != 0.0 ? weight : DEFAULT_WEIGHT;
        this.manufacturer = manufacturer != null ? manufacturer : DEFAULT_MANUFACTURER;
        this.description = description;
        this.barcode = barcode;
    }

    // Только необходимые геттеры (убраны избыточные сеттеры)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getWeight() { return weight; }
    public String getManufacturer() { return manufacturer; }
    public String getDescription() { return description; }
    public String getBarcode() { return barcode; }

    // Сеттеры только для изменяемых полей
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setDescription(String description) { this.description = description; }
}