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


    public List<Movie> getAll(Integer page) throws MovieNotFoundException {
        try {
            Pageable tenPerPage = PageRequest.of(page, 10, Sort.by("year").descending());

            Page<Movie> newPage = repository.findAll(tenPerPage);

            if (newPage.hasContent())
                return newPage.getContent();
            else {
                throw new MovieNotFoundException("");
            }
        } catch (MovieNotFoundException ex) {
            throw new MovieNotFoundException("There are no movies!");
        }

    }


    public List<Movie> getByTitleLike(String title, Integer page) throws MovieNotFoundException {

        Pageable tenPerPage = PageRequest.of(page, 10);

        List<Movie> newList = repository.findByTitleLikeIgnoreCase(title, tenPerPage);

        try {

            if (newList.size() != 0) {
                return newList;
            } else {
                throw new MovieNotFoundException("");
            }

        } catch (MovieNotFoundException e) {
            throw new MovieNotFoundException("Movies with title " + title + " are not present!");
        }


    }

    public List<Movie> getByActors(String actors, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, 10);
        List<Movie> newList = repository.findByActorsLikeIgnoreCase(actors,tenPerPage);

        try {


            if (newList.size() != 0) {
                return newList;
            } else {
                throw new MovieNotFoundException("");
            }

        } catch (MovieNotFoundException ex) {
            throw new MovieNotFoundException("Movies, starring " + actors + " are not present!");
        }


    }

    public List<Movie> getByGenres(String genres, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, 10);
        List<Movie> newList = repository.findByGenresIgnoreCase(genres, tenPerPage);


        try {

            if (newList.size() != 0) {
                return newList;
            } else {
                throw new MovieNotFoundException("");
            }

        } catch (MovieNotFoundException ex) {
            throw new MovieNotFoundException("Movies with genre " + genres + " are not present1");
        }


    }

    public List<Movie> getByPlotLike(String plot, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, 10);

        try {
            List<Movie> newList = repository.findByPlotLikeIgnoreCase(plot, tenPerPage);

            if (newList.size() != 0) {
                return newList;
            } else {
                throw new MovieNotFoundException("");
            }

        } catch (MovieNotFoundException ex) {
            throw new MovieNotFoundException("Movies with plot " + plot + " are not present!");
        }

    }

    public List<Movie> getByImdbRating(Double imdbRating, Integer page) throws MovieNotFoundException {
        Pageable tenPerPage = PageRequest.of(page, 10,Sort.by("imdb.rating").descending());

        try {
            List<Movie> newList = repository.findByImdbRatingGreaterThan(imdbRating, tenPerPage);

            if (newList.size() != 0) {
                return newList;
            } else {
                throw new MovieNotFoundException("");
            }

        } catch (MovieNotFoundException ex) {
            throw new MovieNotFoundException("Movies with IMDB rating greather than, or equal " + imdbRating + " are not present!");
        }

    }

    public List<Movie> sortByRating(Integer page) throws MovieNotFoundException {

        try {
            Pageable tenPerPage = PageRequest.of(page, 10, Sort.by("imdb.rating").descending());

            Page<Movie> newPage = repository.findAll(tenPerPage);

            if (newPage.hasContent()) {
                return newPage.getContent();
            } else {
                throw new MovieNotFoundException("");
            }
        } catch (MovieNotFoundException ex) {
            throw new MovieNotFoundException("There are no movies at the catalog!");
        }


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
