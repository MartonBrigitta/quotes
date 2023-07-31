package org.fasttrackit.quotes.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.quotes.exception.ResourceNotFoundException;
import org.fasttrackit.quotes.model.Quote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteReader quoteReader;

    private final QuoteRepository quoteRepository;

    @PostConstruct
    public void init(){
        System.out.println("Post construct in quote service");
        List<Quote> quotes = quoteReader.readQuotes();
        quoteRepository.saveAll(quotes);
    }

    public List <Quote> getAllQuotes() {

        return StreamSupport.stream(quoteRepository.findAll().spliterator(),false).toList();
    }

    public List<Quote> getBySubject (String subject){
        return getAllQuotes().stream()
                .filter(quote ->quote.getSubject().equals(subject))
                .toList();
    }

    public Quote getById(long id){
        return getAllQuotes().stream()
                .filter(quote -> quote.getId()== id)
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException("Quote not found", id));
    }

    public Quote delete(long id) {
        Quote quote = getById(id);
        quoteRepository.deleteById(id);
        return quote;
    }

    public Quote add(Quote quote){
        quoteRepository.save(quote);
        return quote;
    }

    //la update am lasat sa se poata modifica doar textul si subiectul
    public Quote update(Quote quote, long id){
        Quote existingQuote = delete(id);
        return add(Quote.builder()
                .id(id)
                .title(existingQuote.getTitle())
                .author(existingQuote.getAuthor())
                .text(quote.getText())
                .subject(quote.getSubject())
                .build());
    }
}
