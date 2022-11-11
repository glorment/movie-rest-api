package com.example.movieapi.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class MovieCastId implements Serializable{
  @Type(type = "uuid-char")
  UUID movieId;

  @Type(type = "uuid-char")
  UUID actorId;



}
