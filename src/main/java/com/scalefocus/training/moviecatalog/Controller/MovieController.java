package com.scalefocus.training.moviecatalog.Controller;

import com.scalefocus.training.moviecatalog.Models.Movie;
import com.scalefocus.training.moviecatalog.Repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Evgeni Stoykov
 */
@RestController
@RequestMapping("/movies")
public class MovieController {


    @Autowired
    private MovieRepository repository;


    //GetMappings

    @GetMapping("/{id}")
    public Movie getMovieById( @PathVariable ObjectId id){
        return this.repository.findById(id);
    }

    @GetMapping("/")
    public List<Movie> getAllMovies() {

        return this.repository.findAll();
    }

    @GetMapping("/title={title}")
    public List<Movie> getByTitleLike(@PathVariable String title) {

        return this.repository.findByTitleLike(title);

    }

    @GetMapping("/title={title}=exact")
    public Movie getByTitle(@PathVariable String title) {

        return this.repository.findByTitle(title);

    }

    @GetMapping("/actor={actors}")
    public List<Movie> getByActors(@PathVariable String actors) {

        return this.repository.findByActors(actors);

    }


    @GetMapping("/genres={genres}")
    public List<Movie> getByGenres(@PathVariable String genres) {

        return this.repository.findByGenres(genres);
    }


    @GetMapping("/plot={plot}")
    public List<Movie> getByPlotLike(@PathVariable String plot) {

        return this.repository.findByPlotLike(plot);
    }


    @GetMapping("/imdbRating>={imdbRating}")
    public List<Movie> getByImdbRating(@PathVariable double imdbRating) {
        return this.repository.findByImdbRatingGreaterThan(imdbRating);
    }


    @GetMapping("/sorted=1")
    public List<Movie> sortByRating() {
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "imdb.rating"));
    }


    //Post Mappings

    @PostMapping("/new")
    public Movie newMovie(@RequestBody Movie newMovie){
        return this.repository.save(newMovie);
    }

    //Put Mapping

    @PutMapping("/{id}")
    public void updateMovie(@Valid @RequestBody Movie newMovie, @PathVariable String id){

    newMovie.setId(id);
    this.repository.save(newMovie);

    }

    //Delete Mappings

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMovie(@PathVariable ObjectId id){
        this.repository.deleteById(id.toString());
    }


}