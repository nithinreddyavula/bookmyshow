package com.nithin.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "theatre")
@Data
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String theatreName;
    @Column(nullable = false)
    private String theatreLocation;
    @Column(nullable = false)
    private String theatreCity;
}
