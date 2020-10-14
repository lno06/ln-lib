package com.lnlib.designpattern;

import com.lnlib.logger.AppLogger;

import java.util.LinkedList;
import java.util.List;

/**
 * This tool enables to execute all methods in the piped order.
 * <p>
 * By default, the piping does not fail when an exception is thrown.
 * You can change this behavior by calling returnOnFirstFailure(),
 * so the next piped items are not executed.
 * <p>
 * The piped items have no interaction with the previous call.
 * <p>
 * Reminder, a runnable is a functional interface.
 * It takes no parameter and returns nothing.
 * <p>
 * Future improvement: the piped methods can be chained:
 * https://medium.com/@deepakbapat/the-pipeline-design-pattern-in-java-831d9ce2fe21
 */
public class Pipeline
{
    private final static AppLogger LOGGER = AppLogger.getLogger(Pipeline.class);

    /**
     * Tells the execution to propagate an exception. Else this exception is just logged.
     */
    private boolean returnOnFirstFailure = false;

    /**
     * Contains the list of Runnable that will be executed.
     */
    private final List<Runnable> runnables = new LinkedList<>();

    /**
     * Method to set the returnOnFirstFailure to true
     *
     * @return the current instance
     */
    public Pipeline returnOnFirstFailure()
    {
        this.returnOnFirstFailure = true;
        return this;
    }

    /**
     * Add the given runnable to the list of tasks to be executed
     *
     * @param runnable the functional interface that will be executed.
     *                 It takes no parameter and returns nothing.
     *                 It supports to have exception thrown in the functional method.
     * @return the current instance
     */
    public Pipeline add(RunnableWithException runnable)
    {
        runnables.add(runnable);
        return this;
    }

    /**
     * Starts the execution of all the piped items, in the order they were piped.
     * <p>
     * If an exception is thrown in a Runnable, and the flag returnOnFirstFailure is true,
     * a {@link PipelineException} is thrown, else the exception is just logged.
     */
    public void execute()
    {
        runnables.forEach(this::runItem);
    }

    /**
     * Execute a single runnable
     *
     * @param runnable the functional interface that is executed.
     */
    private void runItem(Runnable runnable)
    {
        try
        {
            runnable.run();
        }
        catch (Exception e)
        {
            if (returnOnFirstFailure)
            {
                // throw exception
                throw new PipelineException(e);
            }
            else
            {
                // swallow exception
                LOGGER.error("Exception thrown while running a task, but process is still executed", e);
            }
        }
    }

}
