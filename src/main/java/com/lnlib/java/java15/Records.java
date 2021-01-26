package com.lnlib.java.java15;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * There can be local records
 */
public class Records
{
    public static void main(String[] args)
    {
        var persons = List.of(new Person("ted"), new Person("bob"));

        // local record
        record Inhabitant(String name, Address address)
        {
        }

        var list = persons.stream()
                .map(m -> new Inhabitant(m.name(), m.address()))
                .sorted(Comparator.comparing(Inhabitant::name))
                .map(Inhabitant::name)
                .collect(Collectors.toList());

        System.out.println(list); // [bob, ted]

    }

    record Address(String street, String city)
    {
    }


    record Person(String name, Address address)
    {

        public Person(String name)
        {
            this(name, null);
        }

    }
}
