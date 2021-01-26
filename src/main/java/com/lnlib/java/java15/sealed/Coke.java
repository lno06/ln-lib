package com.lnlib.java.java15.sealed;

/**
 * Cannot be extended
 */
public final class Coke extends Soda
{

    @Override
    public String getType()
    {
        return "Coca Cola";
    }

    @Override
    public int getSugar()
    {
        return 15;
    }
}
