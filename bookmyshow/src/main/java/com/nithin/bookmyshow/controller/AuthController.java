package com.nithin.bookmyshow.controller;

import com.nithin.bookmyshow.dto.AuthResponse;
import com.nithin.bookmyshow.dto.LoginRequest;
import com.nithin.bookmyshow.dto.RegisterRequest;
import com.nithin.bookmyshow.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> newUser(@RequestBody @Valid RegisterRequest request) {
        String message = userService.registerUser(request);
        return ResponseEntity.status(201).body(message);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        AuthResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}