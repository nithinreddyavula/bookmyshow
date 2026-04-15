package com.nithin.bookmyshow.service;


import com.nithin.bookmyshow.model.Theatre;
import com.nithin.bookmyshow.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    public Theatre addTheatre(TheatreRequest request) {
        if (theatreRepository.existsByTheatreName(request.getTheatreName())) {
            throw new RuntimeException("Theatre already exists");
        }
        Theatre theatre = new Theatre();
        theatre.setTheatreName(request.getTheatreName());
        theatre.setTheatreAddress(request.getTheatreAddress());
        theatre.setTheatreCity(request.getTheatreCity());
        theatre.setTheatrePincode(request.getTheatrePincode());
        theatre.setContactNumber(request.getContactNumber());
        theatre.setScreens(request.getScreens());
        return theatreRepository.save(theatre);
    }
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }
    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

}
