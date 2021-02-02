package com.lnlib.springboot.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class PassengerResponse
{
    /**
     * remote property is '_id', but we want to display 'id'
     */
    @JsonAlias({"id", "_id"})
    private String id;
    private String name;
    private Long trips;
    private AirlineResponse airline;

}
