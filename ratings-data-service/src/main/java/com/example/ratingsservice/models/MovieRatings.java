package com.example.ratingsservice.models;

import java.util.List;

public class MovieRatings {
    private List<MovieRating> movieRatings;

    public MovieRatings(List<MovieRating> movieRatings) {
        this.movieRatings = movieRatings;
    }

    public List<MovieRating> getMovieRatings() {
        return movieRatings;
    }
}
