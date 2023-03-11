package com.trendingmoviesservice.resources;

import com.trendingmoviesservice.models.TopRatings;
import com.trendingmoviesservice.services.TopRatingService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    @RequestMapping("/top-10-ratings")
    public TopRatings getTop10MoviesByRating() {
        return topRatingService.getTop10Ratings();
    }
}
