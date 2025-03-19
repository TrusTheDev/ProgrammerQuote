package com.trusty.QuotesAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trusty.QuotesAPI.model.Quote;
import com.trusty.QuotesAPI.repo.QuoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//Acordate de cambiarlo al url donde tenes montado la página web (protocolo CORS)
@CrossOrigin(origins = "http://localhost:56161/")
public class QuoteController {

    @Autowired
    private QuoteRepo repository;

    @GetMapping("/getAllQuotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        if(repository.findAll().isEmpty()) {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            System.out.println("Exito");
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/getQuote/{Id}")
    public ResponseEntity<Optional> getQuoteById(@PathVariable int Id) {
        if(repository.findById(Id).isPresent()) {
            System.out.println("Exito");
            return new ResponseEntity<>(repository.findById(Id), HttpStatus.OK);
        }
        else {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getLastQuote")
    public ResponseEntity<Quote> getQuoteLast() {
        System.out.println("Exito");
        return new ResponseEntity<>(repository.findFirstByOrderByIdDesc(), HttpStatus.OK);
    }

    @PostMapping("/postQuote")
    public ResponseEntity<Quote> saveQuote(@RequestBody Quote quote) {
        if(repository.findById(quote.getId()).isPresent()) {
            System.out.println("Error");
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        else {
            System.out.println("Exito");
            return new ResponseEntity<>(repository.save(quote), HttpStatus.CREATED);
        }
    }

    @PutMapping("/putQuote")
    public ResponseEntity<Quote> updateQuote(@RequestBody Quote quote) {
        if (repository.findById(quote.getId()).isPresent()) {
            System.out.println("Exito");
            return new ResponseEntity<>(repository.save(quote), HttpStatus.ACCEPTED);
        }
        else {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteQuoteById/{Id}")
    public ResponseEntity<Optional> deleteQuoteById(@PathVariable int Id) {
        if(repository.findById(Id).isPresent()) {
            repository.deleteById(Id);
            System.out.println("Exito");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addLike/{Id}")
    public ResponseEntity<Quote> addLike(@PathVariable int Id) {
        if(repository.findById(Id).isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Quote quote = objectMapper.convertValue(repository.findById(Id).get(), Quote.class);
            quote.setLikes(quote.getLikes() + 1);
            System.out.println("Exito");
            return new ResponseEntity<>(repository.save(quote), HttpStatus.OK);
        }
        else {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/removeLike/{Id}")
    public ResponseEntity<Quote> removeLike(@PathVariable int Id) {
        if(repository.findById(Id).isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Quote quote = objectMapper.convertValue(repository.findById(Id).get(), Quote.class);
            quote.setLikes(quote.getLikes() - 1);
            System.out.println("Exito");
            return new ResponseEntity<>(repository.save(quote), HttpStatus.OK);
        }
        else {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addDislike/{Id}")
    public ResponseEntity<Quote> addDislike(@PathVariable int Id) {
        if(repository.findById(Id).isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Quote quote = objectMapper.convertValue(repository.findById(Id).get(), Quote.class);
            quote.setDislikes(quote.getDislikes() + 1);
            System.out.println("Exito");
            return new ResponseEntity<>(repository.save(quote), HttpStatus.OK);
        }
        else {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/removeDislike/{Id}")
    public ResponseEntity<Quote> removeDislike(@PathVariable int Id) {
        if(repository.findById(Id).isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Quote quote = objectMapper.convertValue(repository.findById(Id).get(), Quote.class);
            quote.setDislikes(quote.getDislikes() - 1);
            System.out.println("Exito");
            return new ResponseEntity<>(repository.save(quote), HttpStatus.OK);
        }
        else {
            System.out.println("Error");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
