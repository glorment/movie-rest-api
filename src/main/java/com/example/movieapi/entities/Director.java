package com.example.movieapi.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Entity
@Data
@JsonInclude(Include.NON_NULL)
public class Director {

  @Id
  @Type(type = "uuid-char")
  UUID id;

  String firstName;

  String lastName;



}
