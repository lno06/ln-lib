package com.lnlib.java.java13;

public class TextBlock
{
    public static void main(String[] args)
    {
        var text = """
                Hi!
                How are you?""";
        var s = "Hi!\nHow are you?";

        System.out.println("Both strings are equals: " + text.equals(s)); // true
        System.out.println("Both strings are equals: " + (text == s)); // true
    }
}
