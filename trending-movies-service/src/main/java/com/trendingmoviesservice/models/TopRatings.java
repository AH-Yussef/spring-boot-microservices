package com.trendingmoviesservice.models;

import java.util.List;

public class TopRatings {
    private List<Rating> ratings;

    public TopRatings() {
    }

    public TopRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

}
