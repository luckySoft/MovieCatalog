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


/**
 * MovieServices.java - a class which contains the implementation of the Business Logic needed for the API
 * @author Mariyan Topalov
 */
@Service
public class MovieServices {


    private final MovieRepository repository;

    private final int sizeOfPage = 10;


    /**
     * Constructor.
     * @param repository injecting MovieRepository into MovieServices.
     */
    public MovieServices(MovieRepository repository) {
        this.repository = repository;
    }



    //Search

    /**
     * Method which gets a Movie using id parameter.
     * @param id id of the movie.
     * @return an Object of type Movie.
     * @throws MovieNotFoundException thrown if a movie with the id given is not present.
     */
    public Movie getById(String id) throws MovieNotFoundException {
        return repository.findById(id).orElseThrow(() -> new MovieNotFoundException("A movie with ID " + id + " is not present!"));
    }


    /**
     * Method which finds a list of all movies available in the database and also the number of all pages.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException  thrown if the list of movies is empty.
     */
    public MoviePages getAll(Integer page) throws MovieNotFoundException {

        Pageable tenPerPage = PageRequest.of(page, sizeOfPage, Sort.by("year").descending());

        Page<Movie> moviePage = repository.findAll(tenPerPage);

        if (!moviePage.hasContent()) throw new MovieNotFoundException("The catalog does not contain any movies!");


        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }


    /**
     * Method which finds a list of movies by title, using the full title or just a sub string of it. Also case insensitive.
     * @param title the title of the movie.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException thrown if the movie catalog does not contain movies with the exact title or a sub string of it.
     */
    public MoviePages getByTitleLikeIgnoreCase(String title, Integer page) throws MovieNotFoundException {

        Pageable tenPerPage = PageRequest.of(page, sizeOfPage);

        Page<Movie> moviePage = repository.findByTitleLikeIgnoreCase(title, tenPerPage);


        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies with title " + title + " are not present!");

        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }


    /**
     * Method which finds a list of movies by title, using the full title or just a sub string of it. Case insensitive and sorted by imdb.rating.
     * @param title the title of the movie.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException thrown if the movie catalog does not contain movies with the exact title or a sub string of it.
     */
    public MoviePages getByTitleLikeAndSortByImdbRating(String title, Integer page) throws MovieNotFoundException {

        Pageable tenPerPage = PageRequest.of(page, sizeOfPage, Sort.by("imdb.rating").descending());

        Page<Movie> moviePage = repository.findByTitleLikeIgnoreCase(title, tenPerPage);


        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies with title " + title + " are not present!");

        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }


    /**
     * Method which finds a list of movies by genre. Case insensitive and sorted by imdb.rating.
     * @param genre the genre of the movie.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException thrown if the movie catalog does not contain movies with the given genre.
     */
    public MoviePages getByGenreAndSortByImdbRating(String genre, Integer page) throws MovieNotFoundException {

        Pageable tenPerPage = PageRequest.of(page, sizeOfPage, Sort.by("imdb.rating").descending());

        Page<Movie> moviePage = repository.findByGenresIgnoreCase(genre, tenPerPage);


        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies with genre " + genre + " are not present!");

        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }

    /**
     * Method which finds a list of movies by actor name, using the full name or just a sub string of it. Case insensitive.
     * @param actor the actor who acts in the movie.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException thrown if the movie catalog does not contain movies with the exact actor name or a sub string of it.
     */
    public MoviePages getByActor(String actor, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, sizeOfPage);

        Page<Movie> moviePage = repository.findByActorsRegex(actor, tenPerPage);

        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies, starring " + actor + " are not present!");


        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }
    /**
     * Method which finds a list of movies by genre. Case insensitive.
     * @param genre the genre of the movie.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException thrown if the movie catalog does not contain movies with the given genre.
     */
    public MoviePages getByGenre(String genre, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, sizeOfPage);
        Page<Movie> moviePage = repository.findByGenresIgnoreCase(genre, tenPerPage);


        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies with genre " + genre + " are not present!");


        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());

    }

    /**
     * Method which finds a list of movies by a piece of the plot. Case insensitive.
     * @param plot the plot of the movie.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException thrown if the movie catalog does not contain movies with the given plot.
     */
    public MoviePages getByPlotLike(String plot, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, sizeOfPage);


        Page<Movie> moviePage = repository.findByPlotLikeIgnoreCase(plot, tenPerPage);

        if (!moviePage.hasContent())
            throw new MovieNotFoundException("Movies with plot " + plot + " are not present!");

        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());


    }

    /**
     * Method which finds a list of movies with imdb rating greather than or equal to the param given.
     * @param imdbRating the imdb rating given.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException thrown if the movie catalog does not contain movies with imdb rating equal or greather than the parameter.
     */
    public MoviePages getByImdbRating(Double imdbRating, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, sizeOfPage, Sort.by("imdb.rating").descending());


        Page<Movie> moviePage = repository.findByImdbRatingGreaterThan(imdbRating, tenPerPage);

        if (!moviePage.hasContent()) {

            throw new MovieNotFoundException("Movies with IMDB rating greather than, or equal " + imdbRating + " are not present!");

        }
        return new MoviePages((long) moviePage.getTotalPages(), moviePage.getContent());
    }

    /**
     * Method which finds a list of all movies present, sorted by imdb rating.
     * @param page page to be visualized.
     * @return an Object of type MoviePages.
     * @throws MovieNotFoundException thrown if the catalog is empty.
     */
    public MoviePages sortByRating(Integer page) throws MovieNotFoundException {


        Pageable tenPerPage = PageRequest.of(page, sizeOfPage, Sort.by("imdb.rating").descending());

        Page<Movie> movieList = repository.findAll(tenPerPage);

        if (!movieList.hasContent()) {

            throw new MovieNotFoundException("There are no movies at the catalog!");
        }


        return new MoviePages((long) movieList.getTotalPages(), movieList.getContent());
    }


    //Update Movie

    /**
     * Method used to update a movie.
     * @param newMovie the movie to be saved.
     * @param id the id of the movie which will be updated.
     */
    public void updateMovie(Movie newMovie, String id) {

        newMovie.setId(id);

        repository.save(newMovie);

    }

    //Delete Movie

    /**
     * Method used to delete a movie with id given.
     * @param id the id of the movie which will be deleted.
     */
    public void deleteMovie(String id) {
        repository.deleteById(id);
    }

    //Post Mappings

    /**
     * Method used to create a new Movie
     * @param newMovie the new movie to be stored.
     */
    public void postMovie(Movie newMovie) {
        repository.save(newMovie);
    }
}

