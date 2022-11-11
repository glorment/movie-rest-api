package com.example.movieapi.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.movieapi.entities.Movie;

public interface MovieRespository extends CrudRepository<Movie,UUID>{
  
  
}
