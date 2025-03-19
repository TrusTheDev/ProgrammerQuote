package com.trusty.QuotesAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

@Entity
@Table(name="quote")
@Getter
@Setter
@ToString

public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String quote;

    @Column
    int likes;

    @Column
    int dislikes;
}
