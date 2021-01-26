package com.lnlib.java.java12;

public class String12Feature
{
    public static void main(String[] args)
    {
        new String12Feature()
                .indent()
                .transform()
                .describeConstable();
    }

    private String12Feature indent()
    {
        System.out.println(" a \nb".indent(2));
        return this;
    }

    private String12Feature transform()
    {
        var result = " a ".transform(s -> "length: " + s.length());
        System.out.println(result);
        return this;
    }

    private String12Feature describeConstable()
    {
        var result = " a ".describeConstable();
        System.out.println(result);
        System.out.println(result.get());
        return this;
    }


}
