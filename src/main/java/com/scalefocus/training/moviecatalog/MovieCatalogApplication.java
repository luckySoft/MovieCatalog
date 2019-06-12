package com.scalefocus.training.moviecatalog;

import com.scalefocus.training.moviecatalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieCatalogApplication{

	@Autowired
	private MovieRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogApplication.class, args);
	}


}
