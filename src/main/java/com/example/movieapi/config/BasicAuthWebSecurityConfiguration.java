package com.example.movieapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthWebSecurityConfiguration {

@Value("${spring.security.user.name}")
String BASIC_AUTH_USER ; 

@Value("${spring.security.user.password}")
String BASIC_AUTH_PASSWORD;


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();

    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User
        .withUsername(BASIC_AUTH_USER)
        .password("{noop}"+BASIC_AUTH_PASSWORD)
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }
}
