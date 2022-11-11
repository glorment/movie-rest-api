package com.example.movieapi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Entity
@Data
@JsonInclude(Include.NON_NULL)
public class Movie {
  @Id
  @Type(type = "uuid-char")
  UUID id;

  String title;

  Integer year;

  Integer time;

  String lang;

  Date releaseDate;

  String releaseCountry;

  @ManyToOne
  @JoinColumn(name="director_id" )
  Director director;

  @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
  List<MovieCast> movieCast = new ArrayList<>();
  

  public void addCast(Actor actor, String role){
    MovieCast cast = new MovieCast(this,actor,role);
    movieCast.add(cast);
    actor.getMovies().add(cast);
  }

  public void removeCast(Actor actor){
    for(var iter = movieCast.iterator() ;  iter.hasNext();){
      var cast = iter.next();
      if(cast.getId().getMovieId().equals(this.id) && 
            cast.getId().getActorId().equals(actor.getId())){
          iter.remove();
          cast.getActor().getMovies().remove(cast);
          cast.setActor(null);
          cast.setMovie(null);

        
      }
    }
  }
}
