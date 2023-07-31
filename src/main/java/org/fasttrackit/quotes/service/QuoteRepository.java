package org.fasttrackit.quotes.service;

import org.fasttrackit.quotes.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {

    //select * from quote where subject='x'
    List<Quote> findBySubject(String subject);
}
