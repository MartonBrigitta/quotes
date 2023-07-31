package org.fasttrackit.quotes.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.quotes.model.Quote;
import org.fasttrackit.quotes.service.QuoteService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("quotes") //http://host:port/quotes
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping // GET http://host:port/quotes
    public List<Quote> getAll(@RequestParam(required = false) String subject){
        if(subject != null){
            return quoteService.getBySubject(subject);
        }
        return quoteService.getAllQuotes();
    }

    @GetMapping("/{id}") // GET http://host:port/quotes/3
    public Quote getById(@PathVariable long id){
        return quoteService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Quote deleteById(@PathVariable long id){
        return quoteService.delete(id);
    }

    @PostMapping
    public Quote addNewQuote (@RequestBody Quote quote){
        return quoteService.add(quote);
    }

    @PutMapping ("/{id}")
    public Quote updateQuote(@RequestBody Quote quote, @PathVariable long id){
        return quoteService.update(quote, id);

    }

}
