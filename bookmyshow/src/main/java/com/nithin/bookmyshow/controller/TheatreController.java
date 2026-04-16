package com.nithin.bookmyshow.controller;

import com.nithin.bookmyshow.dto.TheatreRequest;
import com.nithin.bookmyshow.model.Theatre;
import com.nithin.bookmyshow.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;
    @PostMapping("/add")
    public ResponseEntity<Theatre> addTheatre(@RequestBody TheatreRequest request) {
        return ResponseEntity.ok(theatreService.addTheatre(request));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatrebyId(@PathVariable Long id) {
        return ResponseEntity.ok(theatreService.getTheatreById(id));
    }

}
