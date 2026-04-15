package com.nithin.bookmyshow.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "seats")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Seat implements Serializable {
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
    private boolean isBooked;
    @Version
    private int version;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

}
