package com.store.model.dto;


public class ProductData {
    private Long id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private double weight;
    private String manufacturer;
    private String description;
    private String barcode;

    public ProductData(Long id, String name, String category, double price,
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

    // все геттеры
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getWeight() { return weight; }
    public String getManufacturer() { return manufacturer; }
    public String getDescription() { return description; }
    public String getBarcode() { return barcode; }
}