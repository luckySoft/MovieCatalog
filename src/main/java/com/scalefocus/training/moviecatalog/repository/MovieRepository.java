package com.scalefocus.training.moviecatalog.repository;


import com.scalefocus.training.moviecatalog.Мodels.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findByTitleLike(String title);

    List<Movie> findByActors(String actors);

    List<Movie> findByGenres(String genres);

    List<Movie> findByPlotLike(String plot);

    @Query("{'imdb.rating' : {$gte : ?0}}")
    List<Movie> findByImdbRatingGreaterThan(Double imdbRating);




}