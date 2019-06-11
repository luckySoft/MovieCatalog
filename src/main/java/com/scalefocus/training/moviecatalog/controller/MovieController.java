package com.scalefocus.training.moviecatalog.controller;

import com.scalefocus.training.moviecatalog.exception.MovieNotFoundException;
import com.scalefocus.training.moviecatalog.services.MovieServices;
import com.scalefocus.training.moviecatalog.Мodels.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Evgeni Stoykov
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movies")
public class MovieController {


    private final MovieServices services;

    public MovieController(MovieServices services) {
        this.services = services;
    }

    //GetMappings
    @GetMapping("/id={id}")
    public Movie getMovieById(@PathVariable String id) throws MovieNotFoundException {
        return services.getById(id);
    }

    @GetMapping("/movies=all+page={page}")
    public List<Movie> getAllMovies(@PathVariable Integer page) throws MovieNotFoundException {
        return services.getAll(page);
    }

    @GetMapping("/title={title}+page={page}")
    public List<Movie> getByTitleLike(@PathVariable String title, @PathVariable Integer page) throws MovieNotFoundException {
        return services.getByTitleLike(title, page);
    }

    @GetMapping("/actors={actors}+page={page}")
    public List<Movie> getByActors(@PathVariable String actors, @PathVariable Integer page) throws MovieNotFoundException {
        return services.getByActors(actors,page);
    }


    @GetMapping("/genre={genre}+page={page}")
    public List<Movie> getByGenres(@PathVariable String genre, @PathVariable Integer page) throws  MovieNotFoundException{
        return services.getByGenres(genre,page);
    }

    @GetMapping("/plot={plot}+page={page}")
    public List<Movie> getByPlotLike(@PathVariable String plot,@PathVariable Integer page) throws MovieNotFoundException {
        return services.getByPlotLike(plot,page);
    }

    @GetMapping("/imdbRating>={imdbRating}+page={page}")
    public List<Movie> getByImdbRating(@PathVariable Double imdbRating, @PathVariable Integer page) throws MovieNotFoundException{
        return services.getByImdbRating(imdbRating,page);
    }

    @GetMapping("/sorted=1?page={page}")
    public List<Movie> sortByRating(@PathVariable Integer page) throws MovieNotFoundException {
        return services.sortByRating(page);
    }
    //Post Mapping

    @PostMapping("/new")
    public void newMovie(@RequestBody Movie newMovie) {

        services.postMovie(newMovie);

    }

    //Put Mapping

    @PutMapping("/{id}")
    public void updateMovie(@RequestBody Movie newMovie, @PathVariable String id) {

        services.updateMovie(newMovie, id);

    }

    //Delete Mappings

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable String id) throws MovieNotFoundException{
        services.deleteMovie(id);
    }
}
