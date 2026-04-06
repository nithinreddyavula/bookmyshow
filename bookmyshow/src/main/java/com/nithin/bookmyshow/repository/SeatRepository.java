package com.nithin.bookmyshow.repository;

import com.nithin.bookmyshow.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
