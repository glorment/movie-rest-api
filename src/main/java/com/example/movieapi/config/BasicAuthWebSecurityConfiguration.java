package com.example.movieapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class BasicAuthWebSecurityConfiguration  {

@Value("${spring.security.user.name}")
String BASIC_AUTH_USER ; 

@Value("${spring.security.user.password}")
String BASIC_AUTH_USER_PASSWORD;

@Value("${spring.security.admin.name}")
String BASIC_AUTH_ADMIN ; 

@Value("${spring.security.admin.password}")
String BASIC_AUTH_ADMIN_PASSWORD;



  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .httpBasic();

    return http.build();
  }


  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    var m = new InMemoryUserDetailsManager();
    UserDetails admin = User
        .withUsername(BASIC_AUTH_ADMIN)
        .password("{noop}"+BASIC_AUTH_ADMIN_PASSWORD)
        .roles("ADMIN")
        .build();
        m.createUser(admin);
        
    UserDetails user = User
      .withUsername(BASIC_AUTH_USER)
      .password("{noop}"+BASIC_AUTH_USER_PASSWORD)
      .roles("USER")
      .build();
      m.createUser(user);
    return m;
  }
}
