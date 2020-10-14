package com.lnlib.designpattern;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PipelineTest
{
    @Test
    void test()
    {
        var results = new ArrayList<>();
        new Pipeline()
                .add(() -> results.add(1))
                .add(() -> results.add(2))
                .execute();
        assertEquals(List.of(1, 2), results);
    }

    @Test
    void withExceptionNoStop()
    {
        var results = new ArrayList<>();
        new Pipeline()
                .add(() -> results.add(1))
                .add(() -> {
                    throw new RuntimeException("houla");
                })
                .add(() -> results.add(2))
                .execute();
        assertEquals(List.of(1, 2), results);
    }

    @Test
    void withExceptionWithStop()
    {
        var results = new ArrayList<>();

        try {
            new Pipeline()
                    .returnOnFirstFailure()
                    .add(() -> results.add(1))
                    .add(() -> {
                        throw new RuntimeException("houla");
                    })
                    .add(() -> results.add(2))
                    .execute();
            fail("Exception should have been thrown");
        } catch (Exception e) {
            assertThat(e, instanceOf(PipelineException.class));
            assertEquals("houla", e.getCause().getCause().getMessage());

            assertEquals(1, results.size());
            assertEquals(List.of(1), results);
        }
    }
}