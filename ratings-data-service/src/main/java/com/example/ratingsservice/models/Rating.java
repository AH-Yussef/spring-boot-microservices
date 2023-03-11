package com.example.ratingsservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.IdClass;

@Entity
@IdClass(RatingsKey.class)
@Table(name = "ratings")
public class Rating {
    @Id
    private String userId;
    @Id
    private String movieId;
    private int rating;

//
//    public String getUserId() {
//        return userId;
//    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}