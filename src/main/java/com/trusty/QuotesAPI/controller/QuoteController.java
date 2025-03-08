package com.trusty.QuotesAPI.controller;

import com.trusty.QuotesAPI.model.Quote;
import com.trusty.QuotesAPI.repo.QuoteRepo;
import com.trusty.QuotesAPI.repo.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuoteController {

    @Autowired
    private QuoteRepo repository;

    @GetMapping("/getAllQuotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        if(repository.findAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.FOUND);
        }
    }

    @GetMapping("/getQuote/{ID}")
    public ResponseEntity<Optional> getQuoteById(@PathVariable int ID) {
        if(repository.findById(ID).isPresent()) {
            return new ResponseEntity<>(repository.findById(ID), HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addQuote")
    public ResponseEntity<Quote> saveQuote(@RequestBody Quote quote) {
        if(repository.findById(quote.getId()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else {
            return new ResponseEntity<>(repository.save(quote),HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteMappingById/{ID}")
    public ResponseEntity<Optional> deleteQuoteById(@PathVariable int ID) {
        if(repository.findById(ID).isPresent()) {
            return new ResponseEntity<>(repository.deleteById(ID), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateQuoteById/{Id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable int Id, @RequestBody Quote quote) {
        if(repository.findById(Id).isPresent()) {
            return new ResponseEntity<>(repository.save(quote), HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
