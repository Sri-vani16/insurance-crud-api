package com.example.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.entity.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {}
