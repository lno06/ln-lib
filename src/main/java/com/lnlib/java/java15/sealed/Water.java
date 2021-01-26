package com.lnlib.java.java15.sealed;

/**
 * Class is final so it cannot be extended
 */
public final class Water extends Drink
{
    @Override
    public String getType()
    {
        return "Water";
    }
}
