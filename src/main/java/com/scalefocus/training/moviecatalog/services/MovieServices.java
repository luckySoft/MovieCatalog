package com.scalefocus.training.moviecatalog.services;

import com.scalefocus.training.moviecatalog.exception.MovieNotFoundException;
import com.scalefocus.training.moviecatalog.repository.MovieRepository;
import com.scalefocus.training.moviecatalog.Мodels.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServices {


    private final MovieRepository repository;


    public MovieServices(MovieRepository repository) {
        this.repository = repository;
    }


    public Integer getAllPagesCount() {

        Pageable pages = PageRequest.of(1, 10);

        Page<Movie> newPage = repository.findAll(pages);

        return newPage.getTotalPages();

    }


    public Movie getById(String id) throws MovieNotFoundException {
        return repository.findById(id).orElseThrow(() -> new MovieNotFoundException("A movie with ID " + id + " is not present!"));
    }


    public List<Movie> getAll(Integer page) {
        Pageable tenPerPage = PageRequest.of(page, 10, Sort.by("year").descending());

        Page<Movie> newPage = repository.findAll(tenPerPage);

        return repository.findAll(tenPerPage).getContent();


    }


    public List<Movie> getByTitleLike(String title) throws MovieNotFoundException {

        List<Movie> newList = repository.findByTitleLikeIgnoreCase(title);


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

    public List<Movie> getByActors(String actors) {

        return this.repository.findByActors(actors);

    }

    public List<Movie> getByGenres(String genres) {

        return repository.findByGenres(genres);
    }

    public List<Movie> getByPlotLike(String plot) {

        return repository.findByPlotLike(plot);
    }

    public List<Movie> getByImdbRating(Double imdbRating) {
        return repository.findByImdbRatingGreaterThan(imdbRating);
    }

    public List<Movie> sortByRating(Integer page) {
        Pageable tenPerPage = PageRequest.of(page, 10, Sort.by("imdb.rating").descending());

        Page<Movie> newPage = repository.findAll(tenPerPage);

        return newPage.getContent();
    }

    //Update Movie

    public void updateMovie(Movie newMovie, String id) {

        newMovie.setId(id);

        repository.save(newMovie);

    }

    //Delete Movie

    public void deleteMovie(String id) throws MovieNotFoundException {
        try {
            if (repository.findById(id) != null) {
                repository.deleteById(id);
            } else {
                throw new MovieNotFoundException("");
            }
        } catch (MovieNotFoundException exc) {
            throw new MovieNotFoundException("Cant delete! A movie with ID " + id + " is not present!");
        }
    }

    //Post Mappings

    public void postMovie(Movie newMovie) {


        repository.save(newMovie);
    }

}
