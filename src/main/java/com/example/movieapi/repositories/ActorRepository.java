package com.example.movieapi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.movieapi.entities.Actor;

public interface ActorRepository extends CrudRepository<Actor,UUID>{
  @Query(value ="select * from Actor d order by rand() limit 5" , nativeQuery=true)
  public List<Actor> findAny();
}
