package com.scalefocus.training.moviecatalog.controller;

import com.scalefocus.training.moviecatalog.exception.MovieNotFoundException;
import com.scalefocus.training.moviecatalog.services.MovieServices;
import com.scalefocus.training.moviecatalog.Мodels.Movie;
import com.scalefocus.training.moviecatalog.Мodels.MoviePages;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller class.
 * @author Mariyan Topalov
 * @author Zdravko Karamanolov
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movies")
public class MovieController {


    private final MovieServices services;


    /**
     * Constructor
     * @param services the services to be injected to MovieController
     */
    public MovieController(MovieServices services) {
        this.services = services;
    }

    //GetMappings

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param id
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getById(String)
     */
    @GetMapping(params = {"id"})
    public Movie getMovieById(@RequestParam("id") String id) throws MovieNotFoundException {
        return services.getById(id);
    }

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getAll(Integer)
     */
    @GetMapping(params = {"page"})
    public MoviePages getAllMovies(@RequestParam("page") Integer page) throws MovieNotFoundException {

        return services.getAll(page);
    }


    /**
     * Method used for GetMapping. Communicates with the database.
     * @param title
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getByTitleLikeIgnoreCase(String, Integer)
     */
    @GetMapping(params = {"title", "page"})
    public MoviePages getByTitleLike(@RequestParam("title") String title, @RequestParam("page") Integer page) throws MovieNotFoundException {
        return services.getByTitleLikeIgnoreCase(title, page);
    }

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param title
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getByTitleLikeAndSortByImdbRating(String, Ingerer)
     */
    @GetMapping(params = {"title", "sorted=1", "page"})
    public MoviePages getByTitleLikeAndSortByImdbRating(@RequestParam("title") String title, @RequestParam("page") Integer page) throws MovieNotFoundException {
        return services.getByTitleLikeAndSortByImdbRating(title, page);
    }

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param genre
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getByGenreAndSortByImdbRating(String, Ingerer)
     */
    @GetMapping(params = {"genre", "sorted=1", "page"})
    public MoviePages getByGenreLikeAndSortByImdbRating(@RequestParam("genre") String genre, @RequestParam("page") Integer page) throws MovieNotFoundException {
        return services.getByGenreAndSortByImdbRating(genre, page);
    }

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param actors
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getByActor(String, Integer)
     */
    @GetMapping(params = {"actors", "page"})
    public MoviePages getByActors(@RequestParam String actors, @RequestParam Integer page) throws MovieNotFoundException {
        return services.getByActor(actors, page);
    }

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param genre
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getByGenre(String, Integer)
     */
    @GetMapping(params = {"genre", "page"})
    public MoviePages getByGenres(@RequestParam String genre, @RequestParam Integer page) throws MovieNotFoundException {
        return services.getByGenre(genre, page);
    }

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param plot
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getByPlotLike(String, Integer)
     */
    @GetMapping(params = {"plot", "page"})
    public MoviePages getByPlotLike(@RequestParam String plot, @RequestParam Integer page) throws MovieNotFoundException {
        return services.getByPlotLike(plot, page);
    }

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param imdbRating
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices getByImdbRating(Double, Integer)
     */
    @GetMapping(params = {"imdbRating", "page"})
    public MoviePages getByImdbRating(@RequestParam Double imdbRating, @RequestParam Integer page) throws MovieNotFoundException {
        return services.getByImdbRating(imdbRating, page);
    }

    /**
     * Method used for GetMapping. Communicates with the database.
     * @param page
     * @return
     * @throws MovieNotFoundException
     * @see MovieServices sortByRating(Integer)
     */
    @GetMapping(params = {"sorted=1", "page"})
    public MoviePages sortByRating(@RequestParam Integer page) throws MovieNotFoundException {
        return services.sortByRating(page);
    }
    //Post Mapping

    /**
     * Method used for PostMapping. Communicates with the database.
     * @param newMovie Requires body of type ****.json
     * @see MovieServices postMovie(Movie)
     */
    @PostMapping("/new")
    public void newMovie(@RequestBody Movie newMovie) {

        services.postMovie(newMovie);

    }

    //Put Mapping

    /**
     * Method used for PutMapping. Communicates with the database.
     * @param newMovie Requires body of type ****.json
     * @param id stores
     * @see MovieServices updateMovie(Movie,String)
     */
    @PutMapping(params = "id")
    public void updateMovie(@RequestBody Movie newMovie, @RequestParam String id) {

        services.updateMovie(newMovie, id);

    }

    //Delete Mappings

    /**
     * Method used for DeleteMapping. Communicates with the database.
     * @param id
     * @see MovieServices deleteMovie(String)
     */
    @DeleteMapping(params = "id")
    public void deleteMovie(@RequestParam String id) {
        services.deleteMovie(id);
    }
}
