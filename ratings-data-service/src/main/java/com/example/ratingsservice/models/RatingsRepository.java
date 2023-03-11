package com.example.ratingsservice.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, RatingsKey> {
    @Query(value = "SELECT r FROM Rating r WHERE r.userId = ?1")
    List<Rating> findAllRatings(String userId);

    @Query(value = "SELECT movie_id AS movieId, AVG(rating) AS rating"
            +" FROM ratings GROUP BY movie_id ORDER BY rating DESC LIMIT 10", nativeQuery = true)
    List<IMovieRating> findTopRatings();
}
