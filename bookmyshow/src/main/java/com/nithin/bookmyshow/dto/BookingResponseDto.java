package com.nithin.bookmyshow.dto;

import com.nithin.bookmyshow.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {
    private Long bookingId;
    private BookingStatus bookingStatus;
    private BigDecimal totalAmount;
}
