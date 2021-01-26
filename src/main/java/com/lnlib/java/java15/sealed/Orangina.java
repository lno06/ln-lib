package com.lnlib.java.java15.sealed;

/**
 * Cannot be extended
 */
public final class Orangina extends Soda
{

    @Override
    public String getType()
    {
        return "Orangina";
    }

    @Override
    public int getSugar()
    {
        return 10;
    }
}
