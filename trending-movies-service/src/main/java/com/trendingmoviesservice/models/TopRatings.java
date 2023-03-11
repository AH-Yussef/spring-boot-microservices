package com.trendingmoviesservice.models;

import java.util.List;

public class TopRatings {
    private List<Rating> movieRatings;

    public TopRatings() {
    }

    public TopRatings(List<Rating> ratings) {
        this.movieRatings = ratings;
    }

    public List<Rating> getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(List<Rating> ratings) {
        this.movieRatings = ratings;
    }

}
