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

    List<Movie> findByActors(String actors);

    List<Movie> findByYear(double year);

    List<Movie> findByCountries(String countries);

    List<Movie> findByGenres(String genres);

    List<Movie> findByDirector(String director);

    List<Movie> findByWriters(String writers);

    List<Movie> findByPlotLike(String plot);

    @Query("{'imdb.rating' : {$gte : ?0}}")
    List<Movie> findByImdbRatingGreaterThan(double imdbRating);

    @Query("{'tomato' : {$ne : null}}")
    List<Movie> findByTomatoIsNotNullQuery();
    @Query("{'awards.wins' : {$gte : ?0}}")
    List<Movie> findByAwardsWon(double awards);
}