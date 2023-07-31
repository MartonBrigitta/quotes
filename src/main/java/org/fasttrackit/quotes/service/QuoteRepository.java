package org.fasttrackit.quotes.service;

import org.fasttrackit.quotes.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {
    //select * from quote where subject='x'
    List<Quote> findBySubject(String subject);

@Query("select q from Quote q where (:subject=null or lower(q.subject) like lower(concat('%',:subject,'%'))) " +
        "and (:text=null or lower (q.text) like lower(concat('%',:text,'%')))")
    List<Quote> filterQuotes(@Param("subject") String subject,
                             @Param("text") String text);
}

