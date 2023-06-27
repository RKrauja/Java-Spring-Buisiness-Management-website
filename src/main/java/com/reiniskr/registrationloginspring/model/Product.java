package com.reiniskr.registrationloginspring.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "ammount")
    private int storedAmount;
    @Column(name = "price")
    private double price;

    public Product( String name, int storedAmount, double price) {;
        this.name = name;
        this.storedAmount = storedAmount;
        this.price = price;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storedAmount=" + storedAmount +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
