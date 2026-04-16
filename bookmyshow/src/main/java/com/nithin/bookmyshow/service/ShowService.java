package com.nithin.bookmyshow.service;

import com.nithin.bookmyshow.dto.ShowRequest;
import com.nithin.bookmyshow.model.Movie;
import com.nithin.bookmyshow.model.Screen;
import com.nithin.bookmyshow.model.Show;
import com.nithin.bookmyshow.repository.MovieRepository;
import com.nithin.bookmyshow.repository.ScreenRepository;
import com.nithin.bookmyshow.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreenRepository screenRepository;
    public Show addShow(ShowRequest request) {

        if(!movieRepository.existsById(request.getMovieId())) {
            throw new RuntimeException("Movie not found");
        }

        if(!screenRepository.existsById(request.getScreenId())) {
            throw new RuntimeException("Screen not found");
        }

        if(showRepository.existsByScreenIdAndStartTime(
                request.getScreenId(), request.getStartTime())) {
            throw new RuntimeException("Show already exists for this screen and time");
        }

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setPrice(request.getPrice());
        show.setEndTime(request.getEndTime());
        show.setStartTime(request.getStartTime());
        show.setAvailableSeats(request.getAvailableSeats());

        return showRepository.save(show);
    }
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }
    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("show not found by this id"));
    }
}
