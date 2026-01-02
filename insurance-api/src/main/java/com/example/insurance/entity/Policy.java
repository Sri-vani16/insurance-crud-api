package com.example.insurance.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Policy {

    @Id
    private String policyNumber;

    private Long quoteId;
    private Long customerId;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String status;

    private Double sumInsured = 100000.0;  // Optional default value
    private Double premium = 0.0;          // Optional default value

    // Getters and Setters
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public Long getQuoteId() { return quoteId; }
    public void setQuoteId(Long quoteId) { this.quoteId = quoteId; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getSumInsured() {
        return sumInsured != null ? sumInsured : 100000.0;
    }

    public void setSumInsured(Double sumInsured) {
        this.sumInsured = sumInsured != null ? sumInsured : 100000.0;
    }

    public Double getPremium() {
    return premium != null ? premium : 0.0;
}

    public void setPremium(Double premium) {
        this.premium = premium != null ? premium : 0.0;
    }
    

}

