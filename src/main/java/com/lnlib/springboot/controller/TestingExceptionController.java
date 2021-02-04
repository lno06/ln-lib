package com.lnlib.springboot.controller;

import com.lnlib.springboot.exception.ConfigException;
import com.lnlib.springboot.feignclient.AirlineClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class TestingExceptionController
{
    @Autowired
    private AirlineClient airlineClient;

    /**
     * Throws a generic exception. Should be caught by {@link GlobalExceptionHandler#handleException(Exception, Locale)}
     * <p>
     * Note: not localized response
     */
    @GetMapping("/throwexception")
    public void throwException() throws Exception
    {
        throw new Exception("I'm a general exception");
    }

    /**
     * Throws a NumberFormatException. Should be caught by {@link GlobalExceptionHandler#handleIllegalArgumentException(IllegalArgumentException, Locale)}
     * <p>
     * Note: not localized response
     */
    @GetMapping("/thrownfe")
    public void throwNumberFormatException()
    {
        Integer.parseInt("abc");
    }

    /**
     * Throws an exception in FeignClient (calling a non existing end point)
     * <p>
     * This shows how ErrorDecoder works in Feign
     * <p>
     * Note: localized response
     */
    @GetMapping("/throwfeign")
    public void throwFeign()
    {
        airlineClient.getUnknownEndpoint();
    }

    /**
     * Throws an exception handle with the GeneralException
     * <p>
     * Note: localized response
     */
    @GetMapping("/throwlocalized")
    public void throwLocalized()
    {
        throw new ConfigException();
    }
}
