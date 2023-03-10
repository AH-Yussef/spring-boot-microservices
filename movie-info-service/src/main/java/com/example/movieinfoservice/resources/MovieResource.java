package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.cache.MovieRepository;
import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private RestTemplate restTemplate;

    @Autowired
    private final MovieRepository movieCache;

    public MovieResource(RestTemplate restTemplate, MovieRepository movieCache) {
        this.restTemplate = restTemplate;
        this.movieCache = movieCache;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        // Check if the movie exists in cache
        Optional<Movie> cachedMovie = movieCache.findById(movieId);
        if (cachedMovie.isPresent()) {
            return cachedMovie.get();
        }

        // Get the movie info from TMDB
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
        Movie fetchedMovie = new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
        movieCache.save(fetchedMovie);
        return fetchedMovie;
    }
}
