package com.example.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.insurance.entity.Quote;
import com.example.insurance.repository.QuoteRepository;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepo;

    public Quote createQuote(Quote quote) {
        quote.setStatus("CREATED");
        return quoteRepo.save(quote);
    }

    public Quote updateQuote(Quote quote) {
        return quoteRepo.save(quote);
    }

    public Quote getQuote(Long id) {
        return quoteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
    }

    public void deleteQuote(Long id) {
        if (!quoteRepo.existsById(id)) {
            throw new RuntimeException("Quote not found");
        }
        quoteRepo.deleteById(id);
    }
}
