package com.nithin.bookmyshow.dto;


import lombok.Data;

@Data
public class MovieRequest {
    private String movieName;
    private String movieDescription;
    private String movieLanguage;
    private String movieCast;
    private Integer movieDuration;
}
