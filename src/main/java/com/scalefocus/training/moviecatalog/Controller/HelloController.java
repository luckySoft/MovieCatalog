package com.scalefocus.training.moviecatalog.Controller;

import com.scalefocus.training.moviecatalog.Models.Movie;
import com.scalefocus.training.moviecatalog.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author Evgeni Stoykov
 */
@RestController
public class HelloController {


    @Autowired
    private MovieRepository repository;

    @GetMapping("/")
    public List<Movie> getAllMovies() {
        return this.repository.findAll();
    }



}
