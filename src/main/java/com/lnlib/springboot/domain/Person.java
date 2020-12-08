package com.lnlib.springboot.domain;

import lombok.*;

/**
 * Using lombok
 * Generates all getters/setters as public (except 'internalField')
 * Generates a constructor with parameters 'firstname' and 'lastname'
 * Generates a constructor with all parameters
 * Generates toString (except with 'id')
 * Generates equals and hashCode (except with 'id')
 * Creates a builder
 * <p>
 * All usages can be seen in corresponding junit test class
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Person
{
    /**
     * For testing purpose, excluding id from toString and equals/hashCode
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Integer id;

    /**
     * final field is included in the constructor
     */
    private final String firstname;

    /**
     * NonNull field is included in the constructor
     */
    @NonNull
    private String lastname;

    private Integer age;

    /**
     * Getters and setters are set as private
     */
    @Getter(value = AccessLevel.PRIVATE)
    @Setter(value = AccessLevel.PRIVATE)
    private String internalField;
}
