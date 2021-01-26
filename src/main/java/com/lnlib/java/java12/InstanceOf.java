package com.lnlib.java.java12;

public class InstanceOf
{
    public static void main(String[] args)
    {
        oldWay();
        newWay();
    }

    private static void newWay()
    {
        Object object = "Hello";
        if (object instanceof String s) {
            System.out.println("Printing: " + s.toLowerCase());
        }
    }

    private static void oldWay()
    {
        Object object = "Hello";
        if (object instanceof String) {
            String s = (String) object;

            System.out.println("Printing: " + s.toLowerCase());
        }
    }
}
