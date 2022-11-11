package com.example.movieapi.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Entity
@Data
@JsonInclude(Include.NON_NULL)
public class Actor {

  public enum Gender{
    MALE ,FEMALE;
  }

  @Id
  @Type(type = "uuid-char")
  UUID id;

  String firstName;

  String lastName;

  @Enumerated(EnumType.STRING)

  Gender gender;

  @OneToMany(mappedBy = "actor",cascade = CascadeType.ALL,orphanRemoval = true)
  List<MovieCast> movies = new ArrayList<>();
  
}
