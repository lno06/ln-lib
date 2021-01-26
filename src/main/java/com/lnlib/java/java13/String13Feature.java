package com.lnlib.java.java13;

public class String13Feature
{
    public static void main(String[] args)
    {
        new String13Feature()
                .formatted()
                .stripIndent()
                .translateEscape();
    }

    private String13Feature translateEscape()
    {
        System.out.println("-- String13Feature.translateEscape --");
        var s = "Hi \\n /u0022 you";
        System.out.println(s); // Hi \n /u0022 you
        // Hi
        //  /u0022 you
        System.out.println(s.translateEscapes());
        return this;
    }

    private String13Feature stripIndent()
    {
        System.out.println("-- String13Feature.stripIndent --");
        var s = "Hi        \n" +
                "  Hello\n" +
                "\tMr\n" +
                "You";
        System.out.println(s.replace(" ", "*"));
        System.out.println(s.stripIndent().replace(" ", "*"));
        return this;
    }

    private String13Feature formatted()
    {
        System.out.println("-- String13Feature.formatted --");
        var s = "Hi %s, here is my phone %d that costs €%.2f"
                .formatted("You", 12345, 199.9999);
        var another = "Hi %s, here is my phone %d".formatted(123, 123);
        System.out.println(s); // Hi You, here is my phone 12345 that costs €200,00
        System.out.println(another); // Hi 123, here is my phone 123
        return this;
    }
}
