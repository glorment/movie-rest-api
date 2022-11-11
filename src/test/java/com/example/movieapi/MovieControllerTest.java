package com.example.movieapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.movieapi.controller.dto.MovieDto;
import com.example.movieapi.entities.Actor;
import com.example.movieapi.repositories.ActorRepository;
import com.example.movieapi.repositories.DirectorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class MovieControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  DirectorRepository directorRepository ;

  @Autowired
  ActorRepository actorRepository;


  @Test
  @WithMockUser(username = "test", password = "test", roles = "USER")
  public void post_create_movie() throws Exception{
    Faker faker = new Faker();
    var director =directorRepository.findAny();
    var actors= actorRepository.findAny();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(faker.date().birthday());

    var castMap = actors.stream().collect(Collectors.toMap(Actor::getId , e -> faker.name().fullName() ));
log.debug("castMap {}" , castMap);
    var movieDto  =  new MovieDto(faker.lorem().characters(5, 254), calendar.get(Calendar.YEAR), new Random().nextInt(Integer.MAX_VALUE), faker.country().name() , faker.date().birthday(), faker.country().name(), director.getId() ,castMap );
    


    var result = mockMvc.perform(MockMvcRequestBuilders.post("/movie").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(movieDto))).andReturn();
    
   assertEquals(201, result.getResponse().getStatus());
  }


  @Test
  @WithMockUser(username = "test", password = "test", roles = "USER")
  public void patch_update_movie() throws Exception {
    
  }
}
