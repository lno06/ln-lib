package com.lnlib.util;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class RetryCommandTest
{
    @Test
    void test() throws InterruptedException
    {
        var i = new AtomicInteger(0);

        var result = new RetryCommand<>()
                .expectedStopValue(10)
                .maxRetries(11)
                .timeUnit(TimeUnit.SECONDS)
                .waitInterval(1)
                .execute(i::incrementAndGet);

        assertEquals(10, i.get());
        assertEquals(10, result);
    }

    @Test
    void notEnoughRetry() throws InterruptedException
    {
        var i = new AtomicInteger(0);

        try {
            new RetryCommand<>()
                    .expectedStopValue(10)
                    .maxRetries(2)
                    .timeUnit(TimeUnit.SECONDS)
                    .waitInterval(1)
                    .execute(i::incrementAndGet);
            fail("Should have thrown an exception because there was not enough retry");
        } catch (RetryCommandException e) {
            assertEquals(2, i.get());
        }


    }

}