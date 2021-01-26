package com.lnlib.java.java15.sealed;

/**
 * Subclasses are not restricted
 */
public non-sealed class Alcohol extends Drink
{
    @Override
    public String getType()
    {
        return "General Alcohol";
    }

    public int getDegree()
    {
        return 100;
    }
}
