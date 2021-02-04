package com.lnlib.springboot.controller;

import com.lnlib.springboot.domain.ErrorResponse;
import com.lnlib.springboot.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @Autowired
    private MessageSource messageSource;

    /**
     * Handles all exceptions
     */
    @ExceptionHandler({Exception.class})
    ResponseEntity<String> handleException(Exception e, Locale locale)
    {
        return ResponseEntity
                .badRequest() // code 400
                .body("Exception caught: " + e.getMessage());
    }

    /**
     * Example with inheritence:
     * <p>
     * Handles IllegalArgumentException, like NumberFormatException, which extends IllegalArgumentException
     */
    @ExceptionHandler({IllegalArgumentException.class})
    ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e, Locale locale)
    {
        return ResponseEntity
                .badRequest() // code 400
                .body("IllegalArgumentException caught: " + e.getMessage());
    }

    /**
     * Example with localization:
     * <p>
     * This is an example on how to deal with the locale in the exception.
     * <p>
     * It returns a json {code: 1000, message: "general exception"}
     * <p>
     * The message is taken from exception_message.properties
     */
    @ExceptionHandler({GeneralException.class})
    ResponseEntity<ErrorResponse> handleGeneralException(GeneralException e, Locale locale)
    {
        return ResponseEntity
                .badRequest() // code 400
                .body(ErrorResponse.builder()
                        .code(e.getErrorCode().getCode())
                        .message(messageSource.getMessage(e.getMessage(), null, locale))
                        .build());
    }
}
