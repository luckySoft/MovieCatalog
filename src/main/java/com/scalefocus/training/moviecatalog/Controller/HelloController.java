package com.scalefocus.training.moviecatalog.Controller;

import com.scalefocus.training.moviecatalog.Models.Movie;
import com.scalefocus.training.moviecatalog.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Evgeni Stoykov
 */
@RestController
public class HelloController {


    @Autowired
    private MovieRepository repository;


    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return this.repository.findAll();
    }

    @GetMapping("/movies/title={title}")
    public List<Movie> getByTitleLike(@PathVariable String title){
        return this.repository.findByTitleLike(title);
    }

    @GetMapping("/movies/actor={actors}")
    public List<Movie> getByActors(@PathVariable String actors) {

        return this.repository.findByActors(actors);

    }
    @GetMapping("/movies/year={year}")
    public List<Movie> getByYear(@PathVariable double year){
        return this.repository.findByYear(year);
    }

    @GetMapping("/movies/genres={genres}")
    public List<Movie> getByGenres(@PathVariable String genres){
        return this.repository.findByGenres(genres);
    }

    @GetMapping("/movies/countries={countries}")
    public List<Movie> getByCountries(@PathVariable String countries){
        return this.repository.findByCountries(countries);
    }

    @GetMapping("/movies/director={director}")
    public List<Movie> getByDirector(@PathVariable String director){
        return this.repository.findByDirector(director);
    }

    @GetMapping("/movies/plot={plot}")
    public List<Movie> getByPlotLike(@PathVariable String plot){
        return this.repository.findByPlotLike(plot);
    }
    @GetMapping("/movies/writer={writer}")
    public List<Movie> getByWriters(@PathVariable String writer){
        return this.repository.findByWriters(writer);
    }

    @GetMapping("/movies/imdbRating>={imdbRating}")
    public List<Movie> getByImdbRating(@PathVariable double imdbRating){
        return this.repository.findByImdbRatingGreaterThan(imdbRating);
    }

    @GetMapping("/movies/tomato")
    public List<Movie> getByTomatoNotNull(){
        return this.repository.findByTomatoIsNotNullQuery();
    }
    @GetMapping("/movies/awardsWon>={awardsWon}")
    public List<Movie> getByAwardsWon(@PathVariable double awardsWon){
        return this.repository.findByAwardsWon(awardsWon);
    }
}
