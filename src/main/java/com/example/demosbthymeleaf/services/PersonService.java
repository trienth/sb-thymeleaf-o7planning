package com.example.demosbthymeleaf.services;

import com.example.demosbthymeleaf.dao.PersonRepository;
import com.example.demosbthymeleaf.entities.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public List<Person> getAll() {
        return repository.findAll();
    }

    public Person findById(Integer id) {
        return repository.findById(id).get();
    }

    public void save(Person person) {
        repository.save(person);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
