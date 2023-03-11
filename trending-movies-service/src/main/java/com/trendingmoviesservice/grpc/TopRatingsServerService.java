package com.trendingmoviesservice.grpc;

import com.example.MovieRating;
import com.example.MovieRatingsRequest;
import com.example.MovieRatingsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class TopRatingsServerService extends MovieRatingsServiceGrpc.MovieRatingsServiceImplBase {

    @Override
    public void getTop10MoviesByRating(MovieRatingsRequest request, StreamObserver<MovieRating> responseObserver) {
        List<MovieRating> movieRatings = new ArrayList<>();
        movieRatings.add(MovieRating.newBuilder().setRating(4).setMovieId("315162").build());
        movieRatings.add(MovieRating.newBuilder().setRating(5).setMovieId("76600").build());

        movieRatings.forEach(responseObserver::onNext);

        System.out.println("responded");
        responseObserver.onCompleted();
    }

}
