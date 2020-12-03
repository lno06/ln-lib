package com.lnlib.springboot.repository;

import com.lnlib.springboot.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * In-Memory database
 */
@Repository
public class PersonRepository
{
    private final List<Person> list = new ArrayList<>();

    public List<Person> findAll()
    {
        return list;
    }

    public Person findById(Integer id)
    {
        return list.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Person add(Person person)
    {
        person.setId(list.size());
        list.add(person);
        return person;
    }

    public Person update(Person person)
    {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(person.getId())) {
                list.set(i, person);
            }
        }
        return person;
    }

    public void delete(Integer id)
    {
        list.removeIf(person -> person.getId().equals(id));
    }
}
