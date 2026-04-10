package com.nithin.bookmyshow.repository;

import com.nithin.bookmyshow.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie , Long> {
}
