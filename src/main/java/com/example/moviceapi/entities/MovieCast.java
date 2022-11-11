package com.example.moviceapi.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;

@Entity
@Data
public class MovieCast {

  @EmbeddedId
  MovieCastId id;

  String role;

  @ManyToOne
  @MapsId("movieId")
  Movie movie;

  @ManyToOne
  @MapsId("actorId")
  Actor actor;


  public MovieCast(Movie movie , Actor actor , String role){
    this.movie = movie;
    this.actor = actor;
    this.role = role;

    this.id= new MovieCastId(movie.id , actor.id);
  }
}
