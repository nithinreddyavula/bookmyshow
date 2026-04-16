package com.nithin.bookmyshow.controller;

import com.nithin.bookmyshow.dto.BookingResponseDto;
import com.nithin.bookmyshow.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestParam Long userId,
            @RequestParam Long seatId,
            @RequestParam Long showId
    ) {
        BookingResponseDto booking = bookingService.createBooking(userId, seatId, showId);
        return ResponseEntity.ok(booking);
    }
}