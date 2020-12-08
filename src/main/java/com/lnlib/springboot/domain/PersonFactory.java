package com.lnlib.springboot.domain;

import lombok.Builder;
import lombok.experimental.UtilityClass;

/**
 * Using lombok
 * <p>
 * Creates a local constructor, final class
 * <p>
 * Usage can be seen in corresponding junit test class
 */
@UtilityClass
public class PersonFactory
{

    /**
     * Creates a static method 'smallBuilder'
     */
    @Builder(builderClassName = "SmallFactory", builderMethodName = "smallBuilder")
    public static Person newPerson(String firstname, String lastname)
    {
        return Person.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }

    /**
     * Creates a static method 'fullBuilder'
     */
    @Builder(builderClassName = "FullFactory", builderMethodName = "fullBuilder")
    public static Person newPerson(Integer id, String firstname, String lastname, Integer age,
                                   String internalField)
    {
        return Person.builder()
                .id(id)
                .age(age)
                .firstname(firstname)
                .lastname(lastname)
                .internalField(internalField)
                .build();
    }
}
