package com.scalefocus.training.moviecatalog.repository;


import com.scalefocus.training.moviecatalog.Мodels.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findByTitleLikeIgnoreCase(String title, Pageable pageable);

    List<Movie> findByTitleLikeIgnoreCase(String title);

    List<Movie> findByActorsLikeIgnoreCase(String actors, Pageable pageable);

    List<Movie> findByGenresIgnoreCase(String genres, Pageable pageable);

    List<Movie> findByPlotLikeIgnoreCase(String plot, Pageable pageable);

    @Query("{'imdb.rating' : {$gte : ?0}}")
    List<Movie> findByImdbRatingGreaterThan(Double imdbRating, Pageable pageable);




}