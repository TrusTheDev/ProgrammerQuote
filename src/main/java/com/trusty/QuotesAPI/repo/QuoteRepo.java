package com.trusty.QuotesAPI.repo;

import com.trusty.QuotesAPI.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepo extends JpaRepository<Quote, Integer> {
}
