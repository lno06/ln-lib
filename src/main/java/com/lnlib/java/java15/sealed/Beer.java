package com.lnlib.java.java15.sealed;

/**
 * No inheritence restriction
 */
public class Beer extends Alcohol
{
    @Override
    public String getType()
    {
        return "Beer";
    }

    @Override
    public int getDegree()
    {
        return 7;
    }
}
