package com.scalefocus.training.moviecatalog.controller;

import com.scalefocus.training.moviecatalog.exception.MovieNotFoundException;
import com.scalefocus.training.moviecatalog.services.MovieServices;
import com.scalefocus.training.moviecatalog.Мodels.Movie;
import com.scalefocus.training.moviecatalog.Мodels.MoviePages;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(params = {"id"})
    public Movie getMovieById(@RequestParam("id") String id) throws MovieNotFoundException {
        return services.getById(id);
    }

    @GetMapping(params = {"page"})
    public MoviePages getAllMovies(@RequestParam("page") Integer page) throws MovieNotFoundException {
        return services.getAll(page);
    }

    @GetMapping(params = {"title","page"})
    public MoviePages getByTitleLike(@RequestParam("title") String title, @RequestParam("page") Integer page) throws MovieNotFoundException {
        return services.getByTitleLike(title, page);
    }

    @GetMapping(params = {"actors", "page"})
    public MoviePages getByActors(@RequestParam String actors, @RequestParam Integer page) throws MovieNotFoundException {
        return services.getByActors(actors,page);
    }


    @GetMapping(params = {"genres", "page"})
    public MoviePages getByGenres(@RequestParam String genres, @RequestParam Integer page) throws  MovieNotFoundException{
        return services.getByGenres(genres,page);
    }

    @GetMapping(params = {"plot","page"})
    public MoviePages getByPlotLike(@RequestParam String plot,@RequestParam Integer page) throws MovieNotFoundException {
        return services.getByPlotLike(plot,page);
    }

    @GetMapping(params = {"imdbRating","page"})
    public MoviePages getByImdbRating(@RequestParam Double imdbRating, @RequestParam Integer page) throws MovieNotFoundException{
        return services.getByImdbRating(imdbRating,page);
    }

    @GetMapping(params = {"sorted=1","page"})
    public MoviePages sortByRating(@RequestParam Integer page) throws MovieNotFoundException {
        return services.sortByRating(page);
    }
    //Post Mapping

    @PostMapping("/new")
    public void newMovie(@RequestBody Movie newMovie) {

        services.postMovie(newMovie);

    }

    //Put Mapping

    @PutMapping(params ="id")
    public void updateMovie(@RequestBody Movie newMovie, @RequestParam String id) {

        services.updateMovie(newMovie, id);

    }

    //Delete Mappings

    @DeleteMapping(params = "id")
    public void deleteMovie(@RequestParam String id) throws MovieNotFoundException{
        services.deleteMovie(id);
    }
}
