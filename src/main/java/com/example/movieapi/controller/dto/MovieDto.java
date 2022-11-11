package com.example.movieapi.controller.dto;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@Data
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
  String title;
  
  Integer year;

  Integer time;

  String lang;

  Date releaseDate;

  String releaseCountry;

  UUID directorId;

  Map<UUID,String> cast;
  
}
