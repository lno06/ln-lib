package com.lnlib.springboot.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonFactoryTest
{
    @Test
    void smallBuilder()
    {
        var person = PersonFactory.smallBuilder()
                .firstname("f")
                .lastname("l")
                .build();

        assertAll(
                () -> assertNull(person.getId()),
                () -> assertNull(person.getAge()),
                () -> assertEquals("f", person.getFirstname()),
                () -> assertEquals("l", person.getLastname()),

                () -> assertEquals("Person(firstname=f, lastname=l, age=null, internalField=null)", person.toString())
        );
    }

    @Test
    void fullBuilder()
    {
        var person = PersonFactory.fullBuilder()
                .id(1)
                .age(2)
                .firstname("f")
                .lastname("l")
                .internalField("internal")
                .build();

        assertAll(
                () -> assertEquals(1, person.getId()),
                () -> assertEquals(2, person.getAge()),
                () -> assertEquals("f", person.getFirstname()),
                () -> assertEquals("l", person.getLastname()),

                () -> assertEquals("Person(firstname=f, lastname=l, age=2, internalField=internal)", person.toString())
        );
    }
}