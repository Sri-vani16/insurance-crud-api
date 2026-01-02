package com.example.insurance.controller;
import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.insurance.dto.BillingInfo;
import com.example.insurance.entity.Policy;
import com.example.insurance.service.PolicyService;

@RestController
@RequestMapping("/api")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/policy/issue")
    public ResponseEntity<Policy> issuePolicy(@RequestParam Long quoteId) {
        return new ResponseEntity<>(policyService.issuePolicy(quoteId), HttpStatus.CREATED);
    }

    @GetMapping("/policy/{policyNumber}")
    public ResponseEntity<Policy> getPolicy(@PathVariable String policyNumber) {
        return ResponseEntity.ok(policyService.getPolicy(policyNumber));
    }

    @PutMapping("/policy/{policyNumber}/endorse")
    public ResponseEntity<Policy> endorsePolicy(
            @PathVariable String policyNumber,
            @RequestBody Policy updatedData) {
        return ResponseEntity.ok(policyService.endorsePolicy(policyNumber, updatedData));
    }

    @PutMapping("/policy/{policyNumber}/renew")
    public ResponseEntity<Policy> renewPolicy(@PathVariable String policyNumber) {
        return ResponseEntity.ok(policyService.renewPolicy(policyNumber));
    }

    @PutMapping("/policy/{policyNumber}/cancel")
    public ResponseEntity<Policy> cancelPolicy(@PathVariable String policyNumber) {
        return ResponseEntity.ok(policyService.cancelPolicy(policyNumber));
    }

    @GetMapping("/policy/{policyNumber}/billing")
    public ResponseEntity<BillingInfo> getBillingInfo(@PathVariable String policyNumber) {
        BillingInfo billingInfo = policyService.getBillingInfo(policyNumber);
        return ResponseEntity.ok(billingInfo);
    }

    @GetMapping("/policy/{policyNumber}/document")
    public ResponseEntity<byte[]> downloadPolicyDocument(@PathVariable String policyNumber) {
        return policyService.getPolicyDocument(policyNumber);
    }
    @GetMapping("/policy/{policyNumber}/status")
public ResponseEntity<String> getPolicyStatus(@PathVariable String policyNumber) {
    return ResponseEntity.ok(policyService.getPolicyStatus(policyNumber));
}
@GetMapping("/policy/search")
public ResponseEntity<List<Policy>> searchPolicies(
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String product,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate issueDate
) {
    return ResponseEntity.ok(policyService.searchPolicies(status, product, issueDate));
}

@DeleteMapping("/policy/{policyNumber}")
public ResponseEntity<Void> deletePolicy(@PathVariable String policyNumber) {
    policyService.deletePolicy(policyNumber);
    return ResponseEntity.noContent().build();
}


}
