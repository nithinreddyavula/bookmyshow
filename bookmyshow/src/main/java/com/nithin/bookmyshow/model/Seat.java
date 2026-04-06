package com.nithin.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seats")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String seatNumber;
    @Column(nullable = false)
    private String seatRow;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;
    private boolean isSeated;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

}
