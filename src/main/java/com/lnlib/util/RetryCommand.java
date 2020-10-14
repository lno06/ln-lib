package com.lnlib.util;

import com.lnlib.logger.AppLogger;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * This class enables to wait for a result before throwing an exception.
 * It can be useful to wait for a remote status, or a remote execution.
 * <p>
 * Configurable: number of retries, wait interval, wait time unit.
 * <p>
 * Default values : it retries every 1000 MILLISECONDS
 */
public class RetryCommand<T>
{
    private static final AppLogger logger = AppLogger.getLogger(RetryCommand.class);

    /**
     * Count the current number of retry
     */
    private Integer retryCounter = 0;

    /**
     * Max number of retries
     */
    private Integer maxRetries = null;

    /**
     * Condition that should be be
     */
    private T value;

    /**
     * Wait between 2 retries.
     * <p>
     * Default is 1000
     */
    private Integer waitInterval = 1000;

    /**
     * The unit of the waiting time.
     * <p>
     * Default is milliseconds
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    /**
     * Sets the number of max retries
     *
     * @param maxRetries the max number of retry
     * @return itself
     */
    public RetryCommand<T> maxRetries(Integer maxRetries)
    {
        this.maxRetries = maxRetries;
        return this;
    }

    /**
     * Set the waiting interval between 2 retries
     * Default: 1000
     *
     * @param waitInterval the interval to wait
     * @return itself
     */
    public RetryCommand<T> waitInterval(Integer waitInterval)
    {
        this.waitInterval = waitInterval;
        return this;
    }

    /**
     * Sets the time unit
     * Default: TimeUnit#MILLISECONDS
     *
     * @param timeUnit the time unit
     * @return itself
     */
    public RetryCommand<T> timeUnit(TimeUnit timeUnit)
    {
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * Excepted value that the command execution should return.
     * The command will be executed until this value is reached.
     *
     * @param value the expected value
     * @return itself
     */
    public RetryCommand<T> expectedStopValue(T value)
    {
        this.value = value;
        return this;
    }

    /**
     * Method to execute the given command.
     * It returns the result of the supplier, when the number of retry is reached.
     *
     * @param command the supplier
     * @return the value returned by the command when the expected condition is met
     * @throws InterruptedException when there is a thread interruption while waiting
     */
    public T execute(Supplier<T> command) throws InterruptedException
    {
        while (retryCounter < maxRetries) {

            try {
                var c = command.get();

                if (Objects.equals(value, c)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Condition met. Attempt(" + retryCounter + "/" + maxRetries + ").");
                    }
                    return c;
                }
                if (logger.isDebugEnabled()) {
                    logger.debug(
                            "Attempt(" + retryCounter + "/" + maxRetries + "). Expected(" + value + ") Actual(" +
                                    c + ")... Waiting to retry...");
                }
            } catch (Exception e) {
                logger.warn(
                        "Command retry failed, attempt(" + retryCounter + "/" + maxRetries + "): " + e.getMessage());
            }

            retryCounter++;
            timeUnit.sleep(waitInterval);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Condition not met after all attempts(" + retryCounter + "/" + maxRetries + ")");
        }
        throw new RetryCommandException("Condition (" + value + ") not met after " + maxRetries + " retries");
    }
}
