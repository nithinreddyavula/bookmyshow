package com.nithin.bookmyshow.repository;

import com.nithin.bookmyshow.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre , Long> {
    boolean existsByTheatreName(String theatreName);
}
