package org.fasttrackit.quotes.service;

import org.fasttrackit.quotes.model.Quote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class QuoteReader {
    private final String pathName;

    public QuoteReader(@Value("${initial.data}")String pathName) {
        this.pathName = pathName;
    }

    public List<Quote> readQuotes(){
        List<Quote> quotes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(pathName));
            long id = 1;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                quotes.add(fromLine(line, id++));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return quotes;
    }

    private Quote fromLine(String line, long id){
        System.out.println(line);
        String[] tokens = line.split("\\|");
        Quote.QuoteBuilder quoteBuilder = Quote.builder()
                .id(id)
                .title(tokens[0])
                .author(tokens[1])
                .text(tokens[2])
                .subject(tokens[3]);
        return quoteBuilder.build();
    }
}
