package com.lnlib.java.java14;

public class NPEEnhancement
{
    public static void main(String[] args)
    {
        try {
            var city = new Person().address.city;
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Cannot read field "city" because "address" is null
            e.printStackTrace();
        }
    }


    private static class Person
    {
        String name;
        Address address;
    }

    private static class Address
    {
        String street;
        String city;
    }
}
