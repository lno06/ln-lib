package com.lnlib.java.java15.sealed;

/**
 * The sealed class only allows some classes to be extended
 */
public sealed class Drink permits Water, Alcohol, Soda
{
    public String getType()
    {
        return "General Drink";
    }
}
