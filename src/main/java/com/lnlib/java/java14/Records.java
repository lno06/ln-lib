package com.lnlib.java.java14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A record stores pure data.
 * The java compiler generates a constructor private fields, accessors, equals/hashcode, toString
 * <p>
 * record properties:
 * <li>cannot extend another class, cannot be inherited
 * <li>can implement interfaces
 * <li>cannot be abstract
 * <li>fields are private and final
 * <li>can have static fields and methods
 * <li>allows multiple constructor
 * <p>
 * <p>
 * <p>
 * Looks like kotlin
 */
public class Records
{
    public static void main(String[] args)
    {
        var person = new Person("john", new Address("Main street", "NYC"), new ArrayList<>(List.of("bob")));

        // person.name = "smith"; // not possible
        person.children.add("ted"); // possible

        System.out.println("toString: " + person.toString());
        System.out.println("description: " + person.description());

        try {
            person = new Person("A");
        } catch (IllegalArgumentException e) {
            System.out.println("Message is: " + e.getMessage()); // Name too short
        }

        // Utils
        System.out.println(person.getClass().isRecord()); // true
        System.out.println(Arrays.asList(person.getClass().getRecordComponents())); // [name, Address, List]
    }

    record Person(String name, Address address, List<String> children) implements Information
    {
        static int visitors;

        public static int visitorCount()
        {
            return visitors;
        }

        /**
         * Compact constructor.
         * Used for validation cases and would be invoked at the start of the main constructor.
         */
        public Person
        {
            if (name.length() < 2) {
                throw new IllegalArgumentException("Name too short");
            }
        }

        /**
         * Multiple constructors
         */
        public Person(String name)
        {
            this(name, null, null);
        }

        @Override
        public String description()
        {
            return "Person " + name + " is living at " + address;
        }

        /**
         * Can modify accessor
         */
        public String name()
        {
            return "My name is " + name;
        }
    }

    record Address(String street, String city)
    {
    }

    interface Information
    {
        String description();
    }
}
