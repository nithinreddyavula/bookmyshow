package com.nithin.bookmyshow.service;

import com.nithin.bookmyshow.dto.BookingResponseDto;
import com.nithin.bookmyshow.exception.ResourceNotFoundException;
import com.nithin.bookmyshow.model.*;
import com.nithin.bookmyshow.repository.BookingRepository;
import com.nithin.bookmyshow.repository.SeatRepository;
import com.nithin.bookmyshow.repository.ShowRepository;
import com.nithin.bookmyshow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    private final RedisTemplate<String, String> redisTemplate;

    private static final long LOCK_TTL_MINUTES = 10;

    public BookingResponseDto createBooking(Long userId, Long seatId, Long showId) {

        String lockKey = "seat:lock:" + seatId;

        Boolean lockAcquired = redisTemplate.opsForValue()
                .setIfAbsent(lockKey, String.valueOf(userId), Duration.ofMinutes(LOCK_TTL_MINUTES));

        if (lockAcquired == null || !lockAcquired) {
            throw new RuntimeException("Seat is already locked by another user. Please select a different seat.");
        }
        try {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));

            Show show = showRepository.findById(showId)
                    .orElseThrow(() -> new ResourceNotFoundException("Show not found"));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            if (seat.isBooked()) {
                redisTemplate.delete(lockKey);
                throw new RuntimeException("Seat is already booked.");
            }

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setSeat(seat);
            booking.setShow(show);
            booking.setTotalAmount(show.getPrice());
            booking.setBookingTime(LocalDateTime.now());
            booking.setBookingStatus(BookingStatus.PENDING);

            bookingRepository.save(booking);

            return new BookingResponseDto(booking.getId(), booking.getBookingStatus(), booking.getTotalAmount());

        } catch (Exception e) {
            redisTemplate.delete(lockKey);
            throw e;
        }
    }
    public void confirmBooking(Long bookingId, boolean paymentSuccess) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        Long seatId = booking.getSeat().getId();
        if (paymentSuccess) {
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);
        }
        redisTemplate.delete("seat_lock:" + seatId);
    }

    }
