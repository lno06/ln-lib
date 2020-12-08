package com.lnlib.springboot.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest
{
    @Test
    void settersGetters()
    {
        var person = new Person("f", "l");
        person.setId(1);
        person.setAge(2);
//        setter not available because field is set as final
//        person.setFirstname("first");
        person.setLastname("last");

        assertAll(
                () -> assertEquals(1, person.getId()),
                () -> assertEquals(2, person.getAge()),
                () -> assertEquals("f", person.getFirstname()),
                () -> assertEquals("last", person.getLastname()),

                () -> assertEquals("Person(firstname=f, lastname=last, age=2, internalField=null)", person.toString())
        );

    }

    @Test
    void completeConstructor()
    {
        var person = new Person(1, "f", "l", 2, "internal");

        assertAll(
                () -> assertEquals(1, person.getId()),
                () -> assertEquals(2, person.getAge()),
                () -> assertEquals("f", person.getFirstname()),
                () -> assertEquals("l", person.getLastname()),

                () -> assertEquals("Person(firstname=f, lastname=l, age=2, internalField=internal)", person.toString())
        );
    }

    @Test
    void equalsAndHashcode()
    {
        var person = new Person("f", "l");
        person.setId(1);
        person.setAge(2);
        person.setLastname("last");

        // this object is equally the same, because of annotations
        // 'id' is excluded from the comparison
        var anotherPerson = new Person("f", "l");
        anotherPerson.setId(111111);
        anotherPerson.setAge(2);
        anotherPerson.setLastname("last");


        assertEquals(person, person);
        assertEquals(person, anotherPerson);
    }

    @Test
    void builder()
    {
        var person = Person.builder()
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