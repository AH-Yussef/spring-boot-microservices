package com.example.ratingsservice.models;

import java.util.List;

public class MovieRatings {
    private List<IMovieRating> movieRatings;

    public MovieRatings(List<IMovieRating> movieRatings) {
        this.movieRatings = movieRatings;
    }

    public List<IMovieRating> getMovieRatings() {
        return movieRatings;
    }
}
