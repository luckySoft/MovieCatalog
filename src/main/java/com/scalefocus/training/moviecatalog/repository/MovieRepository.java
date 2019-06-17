package com.scalefocus.training.moviecatalog.repository;


import com.scalefocus.training.moviecatalog.Мodels.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository which extends MongoRepository
 * @author Mariyan Topalov
 * @author Zdravko Karamanolov
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Page<Movie> findByTitleLikeIgnoreCase(String title, Pageable pageable);

    @Query(value = "{'actors': {$regex : ?0, $options: 'i'}}")
    Page<Movie> findByActorsRegex(String actor, Pageable pageable);
    
    Page<Movie> findByGenresIgnoreCase(String genres, Pageable pageable);

    Page<Movie> findByPlotLikeIgnoreCase(String plot, Pageable pageable);

    @Query("{'imdb.rating' : {$gte : ?0}}")
    Page<Movie> findByImdbRatingGreaterThan(Double imdbRating, Pageable pageable);


}