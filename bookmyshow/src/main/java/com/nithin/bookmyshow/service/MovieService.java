package com.nithin.bookmyshow.service;

import com.nithin.bookmyshow.dto.MovieRequest;
import com.nithin.bookmyshow.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> searchMovieByTitle(String title);
    Movie addMovie(MovieRequest request);
    Movie updateMovie(Long id, MovieRequest request);
    void deleteMovie(Long id);
}
