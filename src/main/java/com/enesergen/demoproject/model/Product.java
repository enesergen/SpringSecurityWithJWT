package com.enesergen.demoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


import java.util.Set;

@Entity
@Table(name = "PRODUCTS")
public class Product extends BaseEntity {


    @Column(name = "NAME")
    private String name;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private float price;

    @ManyToMany(mappedBy = "productList")
    @JsonBackReference
    private Set<Order> orderList;

    public Set<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Order> orderList) {
        this.orderList = orderList;
    }

    public Product() {
    }

    public Product(String name, String brand, String description, float price) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
