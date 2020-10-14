package com.lnlib.logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main Logger class to use to log
 */
public class AppLogger
{
    private final Logger logger;

    public static AppLogger getLogger(Class clazz)
    {
        var logger = LoggerFactory.getLogger(clazz);

        return new AppLogger(logger);
    }

    public static AppLogger getLogger(String name)
    {
        var logger = LoggerFactory.getLogger(name);

        return new AppLogger(logger);
    }

    private AppLogger(Logger logger)
    {
        this.logger = logger;
    }

    public void debug(String message)
    {
        logger.debug(message);
    }

    public void trace(String message)
    {
        logger.trace(message);
    }

    /**
     * Format is a string containing {} which is an arg.
     * ex: my taylor is {}.
     * log.info("my taylor is {}", "rich");
     *
     * @param format a string containing {}
     * @param args the arguments to fo in the brackets
     */
    public void info(String format, Object... args)
    {
        logger.info(format, args);
    }

    public void warn(String message)
    {
        logger.warn(message);
    }

    /**
     * Format is a string containing {} which is an arg.
     * ex: my taylor is {}.
     * log.warn("my taylor is {}", "rich");
     *
     * @param format a string containing {}
     * @param args the arguments to fo in the brackets
     */
    public void warn(String format, Object... args)
    {
        logger.warn(format, args);
    }

    public void warn(String message, Exception e)
    {
        logger.warn(message, e);
    }

    /**
     * Format is a string containing {} which is an arg.
     * ex: my taylor is {}.
     * log.error("my taylor is {}", "rich");
     *
     * @param format a string containing {}
     * @param args the arguments to fo in the brackets
     */
    public void error(String format, Object... args)
    {
        logger.error(format, args);
    }

    public void error(String message)
    {
        logger.error(message);
    }

    public void error(String message, Exception e)
    {
        logger.error(message, e);
    }


    public boolean isDebugEnabled()
    {
        return logger.isDebugEnabled();
    }

    public boolean isTraceEnabled()
    {
        return logger.isTraceEnabled();
    }

    public boolean isInfoEnabled()
    {
        return logger.isInfoEnabled();
    }
}
