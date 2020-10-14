package com.lnlib.designpattern;

/**
 * This is the same as {@link Runnable} but allows to have a run() method that throws an unhandled exception.
 * <p>
 * The annotation {@link FunctionalInterface} checks that there is only one abstract method in this class.
 * Method {@link #run()}is not abstract because it has an implementation.
 */
@FunctionalInterface
public interface RunnableWithException extends Runnable
{
    /**
     * This is the functional method, like {@link #run()}, but supports throwing exceptions
     */
    void runWithException() throws Exception;

    /**
     * Implementation of the run method from {@link Runnable}
     */
    @Override
    default void run()
    {
        try
        {
            this.runWithException();
        }
        catch (Exception e)
        {
            // need to convert to a RuntimeException
            throw new RuntimeException(e);
        }
    }
}
