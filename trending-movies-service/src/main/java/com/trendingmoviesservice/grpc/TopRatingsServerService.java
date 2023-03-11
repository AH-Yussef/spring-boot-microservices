package com.trendingmoviesservice.grpc;

import com.example.MovieRating;
import com.example.MovieRatingsRequest;
import com.example.MovieRatingsServiceGrpc;
import com.trendingmoviesservice.models.Rating;
import com.trendingmoviesservice.models.TopRatings;
import com.trendingmoviesservice.services.TopRatingService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TopRatingsServerService extends MovieRatingsServiceGrpc.MovieRatingsServiceImplBase {
    private final TopRatingService topRatingService;

    public TopRatingsServerService(TopRatingService topRatingService) {
        this.topRatingService = topRatingService;
    }

    @Override
    public void getTop10MoviesByRating(MovieRatingsRequest request, StreamObserver<MovieRating> responseObserver) {
        TopRatings topRatings = topRatingService.getTop10Ratings();

        for(Rating rating: topRatings.getMovieRatings()) {
            responseObserver.onNext(MovieRating.newBuilder().setRating(rating.getRating()).setMovieId(rating.getMovieId()).build());
        }

        System.out.println("responded");
        responseObserver.onCompleted();
    }

}
