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

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id) throws MovieNotFoundException {
        return services.getById(id);
    }


    @GetMapping("/movies=all?page={page}")
    public List<Movie> getAllMovies(@PathVariable Integer page) {
        return services.getAll(page);
    }

    @GetMapping("/title={title}")
    public List<Movie> getByTitleLike(@PathVariable String title) throws MovieNotFoundException {
        return services.getByTitleLike(title);
    }

    @GetMapping("/actor={actors}")
    public List<Movie> getByActors(@PathVariable String actors) {
        return services.getByActors(actors);
    }


    @GetMapping("/genres={genres}")
    public List<Movie> getByGenres(@PathVariable String genres) {
        return services.getByGenres(genres);
    }


    @GetMapping("/plot={plot}")
    public List<Movie> getByPlotLike(@PathVariable String plot) {
        return services.getByPlotLike(plot);
    }


    @GetMapping("/imdbRating>={imdbRating}")
    public List<Movie> getByImdbRating(@PathVariable Double imdbRating) {
        return services.getByImdbRating(imdbRating);
    }


    @GetMapping("/sorted=1?page={page}")
    public List<Movie> sortByRating(@PathVariable Integer page) {
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMovie(@PathVariable String id) throws MovieNotFoundException{
        services.deleteMovie(id);
    }


}
