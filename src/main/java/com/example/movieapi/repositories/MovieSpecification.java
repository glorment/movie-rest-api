package com.example.movieapi.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.example.movieapi.controller.dto.SearchCriteria;
import com.example.movieapi.entities.Movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class MovieSpecification implements Specification<Movie>{

  @Getter
  @Setter
  private SearchCriteria criteria;

  
  @Override
  @Nullable
  public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return builder.greaterThanOrEqualTo(
        root.<String> get(criteria.getKey()), criteria.getValue().toString());
  } 
  else if (criteria.getOperation().equalsIgnoreCase("<")) {
      return builder.lessThanOrEqualTo(
        root.<String> get(criteria.getKey()), criteria.getValue().toString());
  } 
  else if (criteria.getOperation().equalsIgnoreCase("=")) {
      if (root.get(criteria.getKey()).getJavaType() == String.class) {
          return builder.like(
            root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
      } else {
          return builder.equal(root.get(criteria.getKey()), criteria.getValue());
      }
  }
  return null;
    
  }
  
}
