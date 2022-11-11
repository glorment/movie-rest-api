package com.example.movieapi.controller;


import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.movieapi.controller.dto.MovieDto;
import com.example.movieapi.entities.Movie;
import com.example.movieapi.repositories.ActorRepository;
import com.example.movieapi.repositories.DirectorRepository;
import com.example.movieapi.repositories.MovieRespository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {
  @Autowired
  MovieRespository movieRespository;

  @Autowired
  DirectorRepository directorRepository;

  @Autowired
  ActorRepository actorRepository;

  @PostMapping
  @Transactional
  public ResponseEntity<Movie> create(@RequestBody MovieDto movieDto){
    log.debug("dto={}" , movieDto);
    var movie = new Movie(movieDto);

    var director =directorRepository.findById(movieDto.getDirectorId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"directory not found")) ;
    
    movie.setDirector(director);

    var pmovie = movieRespository.save(movie);

    var actors= actorRepository.findAllById(movieDto.getCast().keySet());
    actors.forEach(a->{
      var m = movieDto.getCast().get(a.getId());
      if(m!=null){
        pmovie.addCast(a, m);
      }
    });
    return new ResponseEntity<>(pmovie, HttpStatus.CREATED);
  }



  @PatchMapping("/{id}")
  @Transactional
  public ResponseEntity<Movie> patch(@PathVariable  UUID id, @RequestBody MovieDto movieDto){
    var movie =movieRespository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie not found")) ;
    var director =directorRepository.findById(movieDto.getDirectorId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"directory not found")) ;

    movie.update(movieDto);
    if(!director.equals(movie.getDirector())){
      movie.setDirector(director);
    }

    movie.getMovieCast().clear();

    var actors= actorRepository.findAllById(movieDto.getCast().keySet());
    actors.forEach(a->{
      var m = movieDto.getCast().get(a.getId());
      if(m!=null){
        movie.addCast(a, m);
      }
    });
    return new ResponseEntity<>(movie, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> get(@PathVariable  UUID id) {
    var movie =movieRespository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie not found")) ;
    return new ResponseEntity<>(movie, HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable  UUID id){
    movieRespository.deleteById(id);
    return ResponseEntity.ok().build();
  }
  
}
