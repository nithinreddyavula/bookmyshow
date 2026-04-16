package com.nithin.bookmyshow.controller;

import com.nithin.bookmyshow.dto.ShowRequest;
import com.nithin.bookmyshow.model.Show;
import com.nithin.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    @Autowired
    private ShowService showService;
    @PostMapping("/add")
    public ResponseEntity<Show> addShow(@RequestBody ShowRequest request) {
        return ResponseEntity.ok(showService.addShow(request));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Show>> getAllShows()
    {
        return ResponseEntity.ok(showService.getAllShows());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowbyId(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }

}
