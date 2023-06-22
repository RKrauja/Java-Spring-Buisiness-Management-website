package com.reiniskr.registrationloginspring.web.dto;

public class ProductDto {
    private String name;
    private int storedAmount;
    private double price;

    public ProductDto(){}

    public ProductDto(String name, int storedAmount, double price) {
        this.name = name;
        this.storedAmount = storedAmount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoredAmount() {
        return storedAmount;
    }

    public void setStoredAmount(int storedAmount) {
        this.storedAmount = storedAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
