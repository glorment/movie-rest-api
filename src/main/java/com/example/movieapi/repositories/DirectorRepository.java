package com.example.movieapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.movieapi.entities.Director;

public interface DirectorRepository extends CrudRepository<Director,UUID>{
  @Query(value ="select * from Director d order by rand() limit 1" , nativeQuery=true)
  public Director findAny();
}
