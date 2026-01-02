package com.example.insurance.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.insurance.entity.Quote;
import com.example.insurance.service.QuoteService;

@RestController
@RequestMapping("/api")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/quote")
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        return new ResponseEntity<>(quoteService.createQuote(quote), HttpStatus.CREATED);
    }

    @PutMapping("/quote-update")
    public ResponseEntity<Quote> updateQuote(@RequestBody Quote quote) {
        return ResponseEntity.ok(quoteService.updateQuote(quote));
    }

    @GetMapping("/quote/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.getQuote(id));
    }

    @DeleteMapping("/quote/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}
