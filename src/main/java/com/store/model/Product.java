
package com.store.model;

public class Product {
    private Long id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private double weight;
    private String manufacturer;
    private String description;
    private String barcode;

    // Конструктор с 9 параметрами (будет исправлен позже)
    public Product(Long id, String name, String category, double price,
                   int quantity, double weight, String manufacturer,
                   String description, String barcode) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.description = description;
        this.barcode = barcode;
    }

    // Все геттеры и сеттеры (будут оптимизированы)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
}