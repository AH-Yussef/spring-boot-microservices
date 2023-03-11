package com.example.ratingsservice.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Integer> {
    @Query(value = "SELECT r FROM Rating r WHERE r.userId = ?1")
    List<Rating> findAllRatings(String userId);

}
