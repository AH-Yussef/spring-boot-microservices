package com.trendingmoviesservice.models;

import java.util.List;

public class UserRatings {
    private String userId;
    private List<Rating> ratings;

    public UserRatings() {
    }

    public UserRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
    

}
