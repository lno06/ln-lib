package com.lnlib.springboot.controller;

import com.lnlib.springboot.feignclient.AirlineClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The exposed controller which retrieves the values from the remote AirlineClient
 */
@RestController
public class AirlineController
{
    @Autowired
    private AirlineClient airlineClient;

    /**
     * Getting all airlines
     */
    @GetMapping(value = "/airlines")
    public ResponseEntity getAirlines()
    {
        return ResponseEntity.ok(airlineClient.getAirlines());
    }

    /**
     * Getting one airline
     */
    @GetMapping(value = "/airlines/{id}")
    public ResponseEntity getAirline(@PathVariable String id)
    {
        return ResponseEntity.ok(airlineClient.getAirline(id));
    }

    /**
     * Getting passengers with pagination
     */
    @GetMapping(value = "/passengers")
    public ResponseEntity getPassengers(@RequestParam Long page, @RequestParam Long size)
    {
        return ResponseEntity.ok(airlineClient.getPassengers(page, size));
    }
}
