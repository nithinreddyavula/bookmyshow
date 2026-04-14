package com.nithin.bookmyshow.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "theatre")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Theatre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String theatreName;
    @Column(nullable = false)
    private String theatreCity;
    @Column(nullable = false)
    private String theatreAddress;
    @Column(nullable = false)
    private String theatrePincode;
    @Column(nullable = false)
    private String contactNumber;
    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private List<Screen> screens;
}
