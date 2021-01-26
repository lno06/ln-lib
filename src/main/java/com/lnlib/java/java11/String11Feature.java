package com.lnlib.java.java11;

import static java.util.stream.Collectors.toList;

public class String11Feature
{

    public static void main(String[] args)
    {
        new String11Feature()
                .isBlank()
                .lines()
                .strip()
                .stripLeading()
                .stripTrailing()
                .repeat();
    }

    private String11Feature isBlank()
    {
        System.out.println("-- StringFeature.isBlank --");
        System.out.println("Contains only whitespace: " + " ".isBlank()); // true
        System.out.println("Empty string: " + " ".isBlank()); // true
        System.out.println("New line: " + "\n".isBlank()); // true
        System.out.println("Any string: " + "Abc".isBlank()); // false
        System.out.println("Any string with whitespace: " + " Abc ".isBlank()); // false
        return this;
    }

    private String11Feature lines()
    {
        System.out.println("-- StringFeature.lines --");
        var str = "first line\n second line\r\n  third line\r    fourth line";
        System.out.println(str);

        System.out.println(str.lines().collect(toList()));
        return this;
    }

    private String11Feature strip()
    {
        System.out.println("-- StringFeature.strip --");
        System.out.println("Removes leading and trailing whitespace: (" + " ABC ".strip() + ")");
        System.out.println("Removes new line according to Character.isWhitespace: (" + " ABC\n".strip() + ")");
        System.out.println("Removes tab according to Character.isWhitespace: (" + " ABC\t".strip() + ")");
        System.out.println("Removes carriage return according to Character.isWhitespace: (" + " ABC\r".strip() + ")");
        System.out.println("Removes form feed according to Character.isWhitespace: (" + " ABC\f".strip() + ")");
        return this;
    }

    private String11Feature stripLeading()
    {
        System.out.println("-- StringFeature.stripLeading --");
        System.out.println("Removes leading whitespace: (" + " ABC ".stripLeading() + ")");
        return this;
    }

    private String11Feature stripTrailing()
    {
        System.out.println("-- StringFeature.stripTrailing --");
        System.out.println("Removes trailing whitespace: (" + " ABC ".stripTrailing() + ")");
        return this;
    }

    private String11Feature repeat()
    {
        System.out.println("-- StringFeature.repeat --");
        System.out.println("Repeats 3 times: " + "A".repeat(3));
        return this;
    }
}
