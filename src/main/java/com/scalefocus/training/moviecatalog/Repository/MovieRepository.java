package com.scalefocus.training.moviecatalog.Repository;


import com.scalefocus.training.moviecatalog.Models.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Movie findById(ObjectId _id);

    List<Movie> findByTitleLike(String title);

    Movie findByTitle(String title);

    List<Movie> findByActors(String actors);

    List<Movie> findByGenres(String genres);

    List<Movie> findByPlotLike(String plot);

    @Query("{'imdb.rating' : {$gte : ?0}}")
    List<Movie> findByImdbRatingGreaterThan(double imdbRating);




}