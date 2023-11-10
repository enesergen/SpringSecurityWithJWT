package com.enesergen.demoproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity{

    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "TOTAL_PRICE")
    private float totalPrice;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ORDERS_PRODUCTS",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "objId")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID")})
    @JsonManagedReference
    private Set<Product> productList;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    public Order() {
    }

    public Order(String userId, float totalPrice, Set<Product> productList) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.productList = productList;
    }


}
