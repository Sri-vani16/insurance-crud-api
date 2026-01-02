package com.example.insurance.service;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.insurance.dto.BillingInfo;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.Quote;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.QuoteRepository;


@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepo;

    @Autowired
    private QuoteRepository quoteRepo;

    public Policy issuePolicy(Long quoteId) {
        Quote quote = quoteRepo.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        String policyNumber = "POL" + System.currentTimeMillis();
        Policy policy = new Policy();
        policy.setPolicyNumber(policyNumber);
        policy.setQuoteId(quoteId);
        policy.setCustomerId(quote.getCustomerId());
        policy.setIssueDate(LocalDate.now());
        policy.setExpiryDate(LocalDate.now().plusYears(1));
        policy.setStatus("ACTIVE");

        // Update quote status
        quote.setStatus("ISSUED");
        quoteRepo.save(quote);

        return policyRepo.save(policy);
    }

    public Policy getPolicy(String policyNumber) {
        return policyRepo.findById(policyNumber)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
    }

    public Policy endorsePolicy(String policyNumber, Policy updatedData) {
        Policy existing = policyRepo.findById(policyNumber)
                .orElseThrow(() -> new RuntimeException("Policy not found for endorsement"));

        // Update only allowed fields (add more fields as needed)
        if (updatedData.getExpiryDate() != null) {
            existing.setExpiryDate(updatedData.getExpiryDate());
        }

        if (updatedData.getStatus() != null) {
            existing.setStatus(updatedData.getStatus());
        }

        // Example: update sumInsured if provided
        if (updatedData.getSumInsured() != null) {
            existing.setSumInsured(updatedData.getSumInsured());
        }

        // Example: update premium if provided
        if (updatedData.getPremium() != null) {
            existing.setPremium(updatedData.getPremium());
        }

        // Add more fields as per your business rules

        return policyRepo.save(existing);
    }

    public Policy renewPolicy(String policyNumber) {
        Policy policy = policyRepo.findById(policyNumber)
            .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setExpiryDate(policy.getExpiryDate().plusYears(1));
        policy.setStatus("RENEWED");

        return policyRepo.save(policy);
    }

    public Policy cancelPolicy(String policyNumber) {
        Policy policy = policyRepo.findById(policyNumber)
            .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setStatus("CANCELED");
        policy.setExpiryDate(LocalDate.now()); // Optionally set expiry to now

        return policyRepo.save(policy);
    }
    public BillingInfo getBillingInfo(String policyNumber) {
    Policy policy = policyRepo.findById(policyNumber)
        .orElseThrow(() -> new RuntimeException("Policy not found"));

    LocalDate dueDate = policy.getIssueDate().plusMonths(1); // Dummy logic
    String billingStatus = policy.getStatus().equals("CANCELED") ? "N/A" : "PENDING";

    return new BillingInfo(
        policy.getPolicyNumber(),
        policy.getPremium(),
        billingStatus,
        dueDate
    );
}
    public ResponseEntity<byte[]> getPolicyDocument(String policyNumber) {
        Policy policy = policyRepo.findById(policyNumber)
            .orElseThrow(() -> new RuntimeException("Policy not found"));

        // Dummy content as PDF bytes
        String content = "Policy Document for: " + policyNumber + "\nStatus: " + policy.getStatus();
        byte[] documentBytes = content.getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(policyNumber + "_Policy.txt")
                .build());

        return new ResponseEntity<>(documentBytes, headers, HttpStatus.OK);
    }
    public String getPolicyStatus(String policyNumber) {
    Policy policy = policyRepo.findById(policyNumber)
        .orElseThrow(() -> new RuntimeException("Policy not found"));

    return policy.getStatus();
}
public List<Policy> searchPolicies(String status, String product, LocalDate issueDate) {
    List<Policy> allPolicies = policyRepo.findAll();

    return allPolicies.stream()
        .filter(p -> status == null || status.equalsIgnoreCase(p.getStatus()))
        .filter(p -> {
            if (product == null) return true;
            return quoteRepo.findById(p.getQuoteId())
                    .map(q -> product.equalsIgnoreCase(q.getProduct()))
                    .orElse(false);
        })
        .filter(p -> issueDate == null || issueDate.equals(p.getIssueDate()))
        .collect(Collectors.toList());
}

public void deletePolicy(String policyNumber) {
    if (!policyRepo.existsById(policyNumber)) {
        throw new RuntimeException("Policy not found");
    }
    policyRepo.deleteById(policyNumber);
}


}
