package com.nithin.bookmyshow.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "screen")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Screen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int screenNumber;
    @Column(nullable = false)
    private int totalSeats;
    @ManyToOne
    @JoinColumn(name="theatre_id")
    private Theatre theatre;
    @JsonIgnore
    @OneToMany(mappedBy = "theatre")
    private List<Screen> screens;
}
