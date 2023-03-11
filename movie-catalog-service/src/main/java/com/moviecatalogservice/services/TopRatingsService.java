package com.moviecatalogservice.services;

import com.example.MovieRating;
import com.example.MovieRatingsRequest;
import com.example.MovieRatingsServiceGrpc;
import com.google.protobuf.Descriptors;
import com.moviecatalogservice.models.Rating;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@ConfigurationProperties
public class TopRatingsService {
    @GrpcClient("grpc_client")
    MovieRatingsServiceGrpc.MovieRatingsServiceBlockingStub synchronousClient;

    @GrpcClient("grpc_client")
    MovieRatingsServiceGrpc.MovieRatingsServiceStub asynchronousClient;

    public List<Rating> getTop10MoviesByRating() throws InterruptedException {
        MovieRatingsRequest movieRatingsRequest = MovieRatingsRequest.newBuilder().build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<Rating> response = new ArrayList<>();
        asynchronousClient.getTop10MoviesByRating(movieRatingsRequest, new StreamObserver<MovieRating>() {
            @Override
            public void onNext(MovieRating movieRating) {
                response.add(new Rating(movieRating.getMovieId(), movieRating.getRating()));
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }
}
