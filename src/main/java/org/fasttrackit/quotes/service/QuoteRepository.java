package org.fasttrackit.quotes.service;

import org.fasttrackit.quotes.model.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<Quote,Long> {
}
