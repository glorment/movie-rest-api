package com.example.movieapi.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@ToString
public class Actor {

  public enum Gender{
    MALE ,FEMALE;
  }

  public Actor(String firstName , String lastName , Gender gender){
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
  }



  @Id
  @Type(type = "uuid-char")
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String firstName;

  private String lastName;

  @Enumerated(EnumType.STRING)

  private Gender gender;

  @OneToMany(mappedBy = "actor",cascade = CascadeType.ALL,orphanRemoval = true)
  @JsonManagedReference
  List<MovieCast> movies = new ArrayList<>();
  
}
