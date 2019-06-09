package com.scalefocus.training.moviecatalog.controller;

import com.scalefocus.training.moviecatalog.exception.MovieNotFoundException;
import com.scalefocus.training.moviecatalog.repository.MovieRepository;
import com.scalefocus.training.moviecatalog.Мodels.Movie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Evgeni Stoykov
 */
@CrossOrigin(origins = "*")
@RestController
public class MovieController {


    @Autowired
    private MovieRepository repository;


    //GetMappings

    @GetMapping("/{id}")
    public Optional<Movie> getMovieById(@PathVariable String id) throws MovieNotFoundException {
        Optional<Movie> movie = repository.findById(id);

        try {
            if (movie.isPresent()) {
                return movie;
            } else {
                throw new MovieNotFoundException("");
            }
        } catch (MovieNotFoundException e) {
        throw new MovieNotFoundException("A movie with ID " + id + " is not present!");
        }



    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {

        return this.repository.findAll();

    }

    @GetMapping("/title={title}")
    public List<Movie> getByTitleLike(@PathVariable String title) throws MovieNotFoundException {

        List<Movie> newList = repository.findByTitleLike(title);

        try{

            if(newList.size()!=0){
                return newList;
            }else{
                throw new MovieNotFoundException("");
            }

        }catch (MovieNotFoundException e){
            throw new MovieNotFoundException("Movie with title " + title + " does not exist!");
        }



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
    public Movie newMovie(@RequestBody Movie newMovie) {


        return this.repository.save(newMovie);
    }

    //Put Mapping

    @PutMapping("/{id}")
    public void updateMovie(@RequestBody Movie newMovie, @PathVariable String id) {

        newMovie.setId(id);
        this.repository.save(newMovie);

    }

    //Delete Mappings

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMovie(@PathVariable ObjectId id) {
        this.repository.deleteById(id.toString());
    }


    //Get to Json

    @RequestMapping(value = "/toJSON", method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getMovieToJSON() {
        return this.repository.findAll();
    }

}
