package com.trendingmoviesservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.trendingmoviesservice.models.Rating;
import com.trendingmoviesservice.models.TopRatings;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Service
public class TopRatingService {

    private final RestTemplate restTemplate;

    public TopRatingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackTopRatings", commandProperties = {
            // Time to cause timeout
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            // N, Hystrix looks at (analyzes) last N requests.
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            // if >= 50 percent of the last N requests fail, break the circuit
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            // Wait/Sleep for 5 seconds before sending another request to the failed service
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    public TopRatings getTopRating() {
        String ratingsUrl = "http://ratings-data-service/ratings/" + "11";
        return Objects.requireNonNull(restTemplate.getForObject(ratingsUrl, TopRatings.class));
    }

    public TopRatings getFallbackTopRatings() {
        TopRatings userRating = new TopRatings();
        userRating.setRatings(Collections.singletonList(
                new Rating("0", 0)));

        return userRating;
    }
}
