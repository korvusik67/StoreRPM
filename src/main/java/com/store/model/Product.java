package com.store.model;

import com.store.model.dto.ProductData;


public class Product {
    // значения по умолчанию
    public static final double DEFAULT_WEIGHT = 0.0;
    public static final String DEFAULT_MANUFACTURER = "Unknown";

    private final Long id;          // id не изменяется
    private final String name;      // название не изменяется
    private final String category;  // категория не изменяется
    private final double weight;    // вес не изменяется
    private final String manufacturer; // производитель не изменяется
    private final String barcode;   // штрих-код не изменяется

    private double price;           // цена может меняться
    private int quantity;           // количество может меняться
    private String description;     // описание может обновляться


    public Product(ProductData data) {
        this.id = data.getId();
        this.name = data.getName();
        this.category = data.getCategory();
        this.price = data.getPrice();
        this.quantity = data.getQuantity();
        this.weight = data.getWeight() != 0.0 ? data.getWeight() : DEFAULT_WEIGHT;
        this.manufacturer = data.getManufacturer() != null ? data.getManufacturer() : DEFAULT_MANUFACTURER;
        this.description = data.getDescription();
        this.barcode = data.getBarcode();
    }

    // только необходимые геттеры
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getWeight() { return weight; }
    public String getManufacturer() { return manufacturer; }
    public String getDescription() { return description; }
    public String getBarcode() { return barcode; }

    // сеттеры только для изменяемых полей
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format(" id=%d, название='%s', категория='%s', цена=%.2f, количество=%d",
                id, name, category, price, quantity);
    }
}