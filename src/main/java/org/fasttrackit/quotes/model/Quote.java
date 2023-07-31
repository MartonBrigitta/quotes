package org.fasttrackit.quotes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Quote {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String text;
    @Column
    private String subject;
}
