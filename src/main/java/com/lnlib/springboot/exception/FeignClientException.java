package com.lnlib.springboot.exception;

import com.lnlib.springboot.configuration.enums.EErrorCode;

/**
 * An exception for all Feign issues
 */
public class FeignClientException extends GeneralException
{

    /**
     * the message can be specialized, and the errorCode could be a category
     */
    public FeignClientException(String message)
    {
        super(message, EErrorCode.ERROR_FEIGN);
    }
}
