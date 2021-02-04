package com.lnlib.springboot.exception;

import com.lnlib.springboot.configuration.enums.EErrorCode;
import lombok.Getter;

/**
 * A common exception for this application
 */
public class GeneralException extends RuntimeException
{
    @Getter
    private final EErrorCode errorCode;

    /**
     * the message of the exception is taken from the errorCode
     */
    public GeneralException(EErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * the message can be specialized, and the errorCode could be a category
     */
    public GeneralException(String message, EErrorCode errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
