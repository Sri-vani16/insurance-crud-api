package com.example.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private String product;
    private double premium;
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public double getPremium() { return premium; }
    public void setPremium(double premium) { this.premium = premium; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
