package com.zakcorp.questions.bookmyshow_design.service;

import com.zakcorp.questions.bookmyshow_design.exceptions.NotFoundException;
import com.zakcorp.questions.bookmyshow_design.model.Movie;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private Map<String, Movie> movieStore;

    public MovieService() {
        movieStore = new HashMap<>();
    }

    public Movie getMovie(@NonNull final String movieId) {
        if ( !movieStore.containsKey(movieId) ) {
            throw new NotFoundException("The requested movie is not found in our database. " +
                    "Please try with a valid movie ID");
        }
        return movieStore.get(movieId);
    }

    public Movie createMovie(@NonNull final String movieName) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, movieName);
        movieStore.put(movieId, movie);
        return movie;
    }
}
