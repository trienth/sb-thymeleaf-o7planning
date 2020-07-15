package com.example.demosbthymeleaf.dao;

import com.example.demosbthymeleaf.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> { }
