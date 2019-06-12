package com.scalefocus.training.moviecatalog.services;

import com.scalefocus.training.moviecatalog.exception.MovieNotFoundException;
import com.scalefocus.training.moviecatalog.repository.MovieRepository;
import com.scalefocus.training.moviecatalog.Мodels.Movie;
import com.scalefocus.training.moviecatalog.Мodels.MoviePages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MovieServices {


    private final MovieRepository repository;

    private final int size = 10;


    public MovieServices(MovieRepository repository) {
        this.repository = repository;
    }


    public Movie getById(String id) throws MovieNotFoundException {
        return repository.findById(id).orElseThrow(() -> new MovieNotFoundException("A movie with ID " + id + " is not present!"));
    }


    public MoviePages getAll(Integer page) throws MovieNotFoundException {

        Pageable tenPerPage = PageRequest.of(page, size, Sort.by("year").descending());

        Page<Movie> moviePage = repository.findAll(tenPerPage);

        if (!moviePage.hasContent()) throw new MovieNotFoundException("There are no movies!");



        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }




    public MoviePages getByTitleLike(String title, Integer page) throws MovieNotFoundException {


        Pageable tenPerPage = PageRequest.of(page, size);

        Page<Movie> moviePage = repository.findByTitleLikeIgnoreCase(title, tenPerPage);


        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies with title " + title + " are not present!");

        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }

    public MoviePages getByActors(String actors, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, size);

        Page<Movie> moviePage = repository.findByActorsRegex(actors, tenPerPage);

        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies, starring " + actors + " are not present!");


        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }

    public MoviePages getByGenres(String genres, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, size);
        Page<Movie> moviePage = repository.findByGenresIgnoreCase(genres, tenPerPage);


        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies with genre " + genres + " are not present!");


        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());

    }

    public MoviePages getByPlotLike(String plot, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, size);


        Page<Movie> moviePage = repository.findByPlotLikeIgnoreCase(plot, tenPerPage);

        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies with plot " + plot + " are not present!");

        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());


    }

    public MoviePages getByImdbRating(Double imdbRating, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, size, Sort.by("imdb.rating").descending());


        Page<Movie> moviePage = repository.findByImdbRatingGreaterThan(imdbRating, tenPerPage);

        if (!moviePage.hasContent()) {

            throw new MovieNotFoundException("Movies with IMDB rating greather than, or equal " + imdbRating + " are not present!");

        }
        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }

    public MoviePages sortByRating(Integer page) throws MovieNotFoundException {


        Pageable tenPerPage = PageRequest.of(page, size, Sort.by("imdb.rating").descending());

        Page<Movie> movieList = repository.findAll(tenPerPage);

        if (!movieList.hasContent()) {

            throw new MovieNotFoundException("There are no movies at the catalog!");
        }


        return new MoviePages((long) movieList.getTotalPages(), movieList.getContent());
    }


    //Update Movie

    public void updateMovie(Movie newMovie, String id) {

        newMovie.setId(id);

        repository.save(newMovie);

    }

    //Delete Movie

    public void deleteMovie(String id) throws MovieNotFoundException {
        repository.deleteById(id);
    }

    //Post Mappings

    public void postMovie(Movie newMovie) {
        repository.save(newMovie);
    }
}

