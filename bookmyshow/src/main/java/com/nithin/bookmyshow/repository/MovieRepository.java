package com.nithin.bookmyshow.repository;

import com.nithin.bookmyshow.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie , Long> {
    List<Movie> findByMovieNameContaining(String movieName);
}
