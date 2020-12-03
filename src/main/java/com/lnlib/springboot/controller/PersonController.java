package com.lnlib.springboot.controller;

import com.lnlib.springboot.domain.Person;
import com.lnlib.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController
{
    @Autowired
    private PersonRepository repository;

    @GetMapping
    public List<Person> findAll()
    {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable int id)
    {
        return repository.findById(id);
    }

    @PutMapping
    public Person update(@RequestBody Person person)
    {
        return repository.update(person);
    }

    @PostMapping
    public Person add(@RequestBody Person person)
    {
        return repository.add(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id)
    {
        repository.delete(id);
    }

}
