package com.scalefocus.training.moviecatalog.repositories;

import com.scalefocus.training.moviecatalog.models.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findBy_id(ObjectId _id);
}