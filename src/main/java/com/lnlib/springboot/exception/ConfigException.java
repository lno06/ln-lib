package com.lnlib.springboot.exception;

import com.lnlib.springboot.configuration.enums.EErrorCode;

/**
 * A more specialized exception for configuration
 */
public class ConfigException extends GeneralException
{

    /**
     * the message of the exception is taken from the errorCode
     */
    public ConfigException()
    {
        super(EErrorCode.ERROR_CONFIGURATION);
    }

    /**
     * The message is overriden, but the code remains the same.
     * But the message should be in the format "xxx.xxx.xx"
     * and the key should be present in exception_message.properties
     */
    public ConfigException(String message)
    {
        super(message, EErrorCode.ERROR_CONFIGURATION);
    }
}
