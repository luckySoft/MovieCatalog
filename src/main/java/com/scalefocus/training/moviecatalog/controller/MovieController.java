package com.scalefocus.training.moviecatalog.controller;

import com.scalefocus.training.moviecatalog.exception.MovieNotFoundException;
import com.scalefocus.training.moviecatalog.repository.MovieRepository;
import com.scalefocus.training.moviecatalog.Мodels.Movie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Evgeni Stoykov
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movies")
public class MovieController {


    @Autowired
    private MovieRepository repository;


    //GetMappings

    @GetMapping("/getById={id}")
    public Movie getMovieById(@PathVariable String id) throws MovieNotFoundException {
        return repository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie with ID " + id + " does not exist"));
    }


    public Integer getAllPagesCount() {

        Pageable pages = PageRequest.of(1, 10);

        Page<Movie> newPage = repository.findAll(pages);

        return newPage.getTotalPages();

    }

    @GetMapping("/movies=all+page={page}")
    public List<Movie> getAllMovies(@PathVariable Integer page) {

        Pageable pages = PageRequest.of(page, 10, Sort.by("title").descending());

        Page<Movie> newPage = repository.findAll(pages);

        return repository.findAll(pages).getContent();

    }

    @GetMapping("/title={title}")
    public List<Movie> getByTitleLike(@PathVariable String title) throws MovieNotFoundException {

       List<Movie> newList=repository.findByTitleLikeIgnoreCase(title);


        try {

            if (newList.size() != 0) {
                return newList;
            } else {
                throw new MovieNotFoundException("");
            }

        } catch (MovieNotFoundException e) {
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


    @GetMapping("/sorted=1+page={page}")
    public List<Movie> sortByRating(@PathVariable Integer page) {
        Pageable tenPerPage = PageRequest.of(page, 10, Sort.by("imdb.rating").descending());

        Page<Movie> newPage = repository.findAll(tenPerPage);

        return newPage.getContent();
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

    public List<Movie> getMovieToJSON() {
        return this.repository.findAll();
    }


    @GetMapping("/page={page}")
    public List<Movie> getMoviesToPage(@PathVariable Integer page) {

        Pageable pages = PageRequest.of(page, 10, Sort.by("year").descending());

        Page<Movie> newPage = repository.findAll(pages);

        return newPage.getContent();
    }

}
