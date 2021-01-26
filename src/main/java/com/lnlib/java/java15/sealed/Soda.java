package com.lnlib.java.java15.sealed;

/**
 * This subclass restricts the inherited classes
 */
public abstract sealed class Soda extends Drink permits Coke, Orangina
{
    @Override
    public String getType()
    {
        return "General Soda";
    }

    public abstract int getSugar();
}
