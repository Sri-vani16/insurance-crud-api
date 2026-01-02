package com.example.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, String> {}
