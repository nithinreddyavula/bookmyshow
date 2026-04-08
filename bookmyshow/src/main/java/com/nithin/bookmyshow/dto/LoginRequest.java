package com.nithin.bookmyshow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email is required")
    private String userEmail;
    @NotBlank(message = "password is required")
    private String password;
}
