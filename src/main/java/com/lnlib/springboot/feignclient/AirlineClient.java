package com.lnlib.springboot.feignclient;

import com.lnlib.springboot.configuration.FeignClientConfiguration;
import com.lnlib.springboot.domain.AirlineResponse;
import com.lnlib.springboot.domain.PaginatedPassengerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Interface getting the data from the feign.url remote site
 */
@FeignClient(name = "${feign.name}", url = "${feign.url}", configuration = FeignClientConfiguration.class)
public interface AirlineClient
{
    /**
     * Getting all airlines
     */
    @RequestMapping(value = "/airlines")
    List<AirlineResponse> getAirlines();

    /**
     * Getting one airline
     */
    @RequestMapping(value = "/airlines/{id}")
    AirlineResponse getAirline(@PathVariable("id") String id);

    /**
     * Getting passengers with pagination
     */
    @RequestMapping(value = "/passenger")
    PaginatedPassengerResponse getPassengers(@RequestParam("page") Long page, @RequestParam("size") Long size);
}
