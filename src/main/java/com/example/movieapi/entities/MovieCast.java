package com.example.movieapi.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
public class MovieCast {

  @EmbeddedId
  private MovieCastId id;

  private String role;

  @ManyToOne
  @MapsId("movieId")
  @ToString.Exclude
  @JsonBackReference
  private Movie movie;

  @ManyToOne
  @MapsId("actorId")
  @ToString.Exclude
  @JsonBackReference
  private Actor actor;


  public MovieCast(Movie movie, Actor actor , String role){
    this.movie = movie;
    this.actor = actor;
    this.role = role;

    this.id= new MovieCastId(movie.getId() , actor.getId());
  }
}
