package com.example.movieapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

import org.hamcrest.collection.IsIn;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.movieapi.controller.dto.SearchCriteria;
import com.example.movieapi.entities.Actor;
import com.example.movieapi.entities.Director;
import com.example.movieapi.entities.Movie;
import com.example.movieapi.repositories.ActorRepository;
import com.example.movieapi.repositories.DirectorRepository;
import com.example.movieapi.repositories.MovieRespository;
import com.example.movieapi.repositories.MovieSpecification;
import com.github.javafaker.Faker;
import static org.hamcrest.CoreMatchers.*;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MovieApiApplicationTests {

	@Autowired
	DirectorRepository directorRepository;


	@Autowired
	ActorRepository actorRepository;
	
	@Autowired
	MovieRespository movieRespository;
	
	private Movie movieStarWar;

	private Movie movieIronman;

	@BeforeEach
	public void init(){
		LocalDate localDate = LocalDate.of( 1977 , 5 , 25 );
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


		movieStarWar = new Movie();
		movieStarWar.setLang("English");
		movieStarWar.setReleaseDate(date);
		movieStarWar.setReleaseCountry("UK");
		movieStarWar.setTime(121);
		movieStarWar.setTitle("Star Wars");
		movieStarWar.setYear(1977);
		movieRespository.save(movieStarWar);

	}


	@Test
	public void importDirector(){
		Faker faker = new Faker();
		var firstName= faker.name().firstName();
		var lastName= faker.name().lastName();
		Director director = new Director(firstName , lastName);
		assertAll(()-> directorRepository.save(director)); 
	}

	@Test
	public void importActor(){
		Faker faker = new Faker();
		var firstName= faker.name().firstName();
		var lastName= faker.name().lastName();
		int pick = new Random().nextInt(Actor.Gender.values().length);
		var actor = new Actor(firstName , lastName , Actor.Gender.values()[pick]);
		assertAll(()-> actorRepository.save(actor));
	}

	@Test
	public void search_movie_Title(){
		MovieSpecification spec = new MovieSpecification(new SearchCriteria("title" , ":" ,"Star"));
		
		var results = movieRespository.findAll(spec);
log.debug("list ={} ",results.stream().map(Movie::getTitle).collect(Collectors.toList()) );
		assertTrue( results.stream().map(Movie::getTitle).collect(Collectors.toList()) .contains("Star Wars")) ;
	}

}
