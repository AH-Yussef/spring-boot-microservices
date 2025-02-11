package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @Autowired
    RatingsRepository ratingsRepository;

    @RequestMapping("/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) {
        List<Rating> ls = ratingsRepository.findAllRatings(userId);
        return new UserRating(ls);
    }

    @RequestMapping("/top")
    public MovieRatings getTopRatings() {
        List<IMovieRating> ls = ratingsRepository.findTopRatings();
        return new MovieRatings(ls);
    }
}
 