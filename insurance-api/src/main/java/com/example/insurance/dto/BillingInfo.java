package com.example.insurance.dto;

import java.time.LocalDate;

public class BillingInfo {

    private final String policyNumber;
    private final double premium;
    private final String billingStatus;
    private final LocalDate dueDate;
    
    // Constructor
    public BillingInfo(String policyNumber, double premium, String billingStatus, LocalDate dueDate) {
        this.policyNumber = policyNumber;
        this.premium = premium;
        this.billingStatus = billingStatus;
        this.dueDate = dueDate;
    }

    // Getters only (no setters for immutability)
    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getPremium() {
        return premium;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
