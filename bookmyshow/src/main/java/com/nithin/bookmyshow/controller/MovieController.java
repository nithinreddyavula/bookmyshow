package com.nithin.bookmyshow.controller;

import com.nithin.bookmyshow.dto.MovieRequest;
import com.nithin.bookmyshow.model.Movie;
import com.nithin.bookmyshow.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nithin.bookmyshow.service.MovieServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.addMovie(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovieByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchMovieByTitle(title));
    }
}
