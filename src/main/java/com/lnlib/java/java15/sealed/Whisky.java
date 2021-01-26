package com.lnlib.java.java15.sealed;

/**
 * No inheritence restriction
 */
public class Whisky extends Alcohol
{

    @Override
    public String getType()
    {
        return "Whiskey";
    }

    @Override
    public int getDegree()
    {
        return 40;
    }
}

