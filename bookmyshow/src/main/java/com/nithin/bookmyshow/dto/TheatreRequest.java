package com.nithin.bookmyshow.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TheatreRequest {
    private String theatreName;
    private String theatreAddress;
    private String theatreCity;
    private String theatrePincode;
    private String contactNumber;
}
