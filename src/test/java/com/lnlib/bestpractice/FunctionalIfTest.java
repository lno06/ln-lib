package com.lnlib.bestpractice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalIfTest
{
    private final FunctionalIf functionalIf = new FunctionalIf();

    @Test
    void normalIf()
    {
        assertAll(
                () -> assertEquals("1", functionalIf.normalIf(1)),
                () -> assertEquals("2", functionalIf.normalIf(2)),
                () -> assertEquals("3", functionalIf.normalIf(3)),
                () -> assertEquals("NULL", functionalIf.normalIf(4))
        );
    }

    @Test
    void functionalIf()
    {
        assertAll(
                () -> assertEquals("1", functionalIf.functionalIf(1)),
                () -> assertEquals("2", functionalIf.functionalIf(2)),
                () -> assertEquals("3", functionalIf.functionalIf(3)),
                () -> assertEquals("NULL", functionalIf.functionalIf(4))
        );
    }
}