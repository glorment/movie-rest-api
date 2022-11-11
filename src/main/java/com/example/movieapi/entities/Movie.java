package com.example.movieapi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.example.movieapi.controller.dto.MovieDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@JsonInclude(Include.NON_NULL)
@Slf4j
@NoArgsConstructor
@ToString
public class Movie {
  @Id
  @Type(type = "uuid-char")
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String title;

  private Integer year;

  private Integer time;

  private String lang;

  private Date releaseDate;

  private String releaseCountry;

  @ManyToOne
  @JoinColumn(name="director_id" )
  private Director director;

  @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,orphanRemoval = true)
  @JsonManagedReference
  private List<MovieCast> movieCast = new ArrayList<>();
  

  public void addCast(Actor actor, String role){

    MovieCast cast = new MovieCast(this,actor,role);
    // log.debug("cast = {}",cast);
    movieCast.add(cast);
    
    
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

  public Movie(MovieDto dto){    
    update(dto);

  }

  public void update(MovieDto dto){
    this.time= dto.getTime();
    this.year = dto.getYear();
    this.lang = dto.getLang();
    this.releaseCountry=dto.getReleaseCountry();
    this.releaseDate = dto.getReleaseDate();
    this.title = dto.getTitle();
  }
}
