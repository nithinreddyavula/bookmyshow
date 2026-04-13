package com.nithin.bookmyshow.service;

import com.nithin.bookmyshow.dto.MovieRequest;
import com.nithin.bookmyshow.model.Movie;
import com.nithin.bookmyshow.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Override
    @Cacheable(value = "movies", key = "'allMovies'")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }



    @CacheEvict(value = "movies", allEntries = true)
    public Movie addMovie(MovieRequest request) {
        Movie movie = new Movie();
        movie.setMovieName(request.getMovieName());
        movie.setMovieCast(request.getMovieCast());
        movie.setMovieDescription(request.getMovieDescription());
        movie.setMovieLanguage(request.getMovieLanguage());
        movie.setMovieDuration(request.getMovieDuration());
        return movieRepository.save(movie);
    }
    @CacheEvict(value = "movies", key = "'allMovies'")
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    @CacheEvict(value = "movies", allEntries = true)
    public Movie updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setMovieName(request.getMovieName());
        movie.setMovieCast(request.getMovieCast());
        movie.setMovieDescription(request.getMovieDescription());
        movie.setMovieLanguage(request.getMovieLanguage());
        movie.setMovieDuration(request.getMovieDuration());
        return movieRepository.save(movie);
    }
    @Cacheable(value = "movies", key = "#title")
    public List<Movie> searchMovieByTitle(String title) {
        return movieRepository.findByMovieNameContaining(title);
    }


}
