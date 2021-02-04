package com.lnlib.springboot.configuration.enums;

import lombok.Getter;

/**
 * List of all the errors handled in the application
 */
@Getter
public enum EErrorCode
{
    ERROR_GENERAL("exception.general", 1000),
    ERROR_CONFIGURATION("exception.configuration", 1001),
    ERROR_FEIGN("exception.feign", 1002);

    private final String message;
    private final int code;

    EErrorCode(String message, int code)
    {

        this.message = message;
        this.code = code;
    }
}
