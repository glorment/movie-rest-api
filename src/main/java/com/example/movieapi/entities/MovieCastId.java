package com.example.movieapi.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieCastId implements Serializable{
  @Type(type = "uuid-char")
  private UUID movieId;

  @Type(type = "uuid-char")
  private UUID actorId;



}
