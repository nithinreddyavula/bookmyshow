package com.nithin.bookmyshow.repository;

import com.nithin.bookmyshow.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen , Long> {
    List<Screen> findByTheatre_id(Long theatreId);
}
