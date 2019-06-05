package com.scalefocus.training.moviecatalog.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "movieDetails")
public class Movie {

    @Id
    private String id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 1000)
    private String desc;

    @NotNull
    @Size(min = 1, max = 5)
    private String rated;


}

