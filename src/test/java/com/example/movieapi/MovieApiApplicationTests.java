package com.example.movieapi;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.movieapi.entities.Actor;
import com.example.movieapi.entities.Director;
import com.example.movieapi.repositories.ActorRepository;
import com.example.movieapi.repositories.DirectorRepository;
import com.github.javafaker.Faker;

@SpringBootTest
class MovieApiApplicationTests {

	@Autowired
	DirectorRepository directorRepository;


	@Autowired
	ActorRepository actorRepository;
	
	

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

}
