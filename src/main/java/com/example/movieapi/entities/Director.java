package com.example.movieapi.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@JsonInclude(Include.NON_NULL)
@ToString
public class Director {

  public Director(){
    
    
  }
  public Director(String firstName , String lastName){
    this.firstName = firstName;
    this.lastName = lastName;
    
  }

  @Id
  @GeneratedValue(generator = "UUID")
  @Type(type = "uuid-char")
  private UUID id;

  private String firstName;

  private String lastName;


}
