package com.trendingmoviesservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.trendingmoviesservice.models.Rating;
import com.trendingmoviesservice.models.TopRatings;
import com.trendingmoviesservice.services.TopRatingService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trending")
public class TrendingMoviesResource {

    private final RestTemplate restTemplate;

    private final TopRatingService topRatingService;

    public TrendingMoviesResource(RestTemplate restTemplate,
            TopRatingService topRatingService) {

        this.restTemplate = restTemplate;
        this.topRatingService = topRatingService;
    }

    /**
     * Makes a call to MovieInfoService to get movieId, name and description,
     * Makes a call to RatingsService to get ratings
     * Accumulates both data to create a MovieCatalog
     * 
     * @param
     * @return CatalogItem that contains name, description and rating
     */
    @RequestMapping("/ratings")
    public TopRatings getTopMovies() {
        return topRatingService.getTopRating();
    }

    @RequestMapping("/top-by-ratings")
    public TopRatings getTopMoviesByRatings() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating("10", 5));
        ratings.add(new Rating("10", 5));
        ratings.add(new Rating("10", 5));
        ratings.add(new Rating("10", 5));
        TopRatings topRatings = new TopRatings(ratings);
        return topRatings;
    }
}
