package com.nithin.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "movie")
@Data
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String movieName;
    @Column(nullable = false)
    private String movieCast;
    @Column(nullable = false)
    private String movieDescription;
    @Column(nullable = false)
    private Integer movieDuration;
    @Column(nullable = false)
    private String movieLanguage;

}
