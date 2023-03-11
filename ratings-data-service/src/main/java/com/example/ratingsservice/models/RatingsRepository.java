package com.example.ratingsservice.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Integer> {
    @Query(value = "SELECT r FROM Rating r WHERE r.userId = ?1")
    List<Rating> findAllRatings(String userId);

    @Query(value = "SELECT new com.example.ratingsservice.models.MovieRating(movieId, AVG(rating) as avg_rating)"
     +" FROM Rating GROUP BY movieId ORDER BY avg_rating DESC LIMIT 10")
    List<MovieRating> findTopRatings();
    
}
