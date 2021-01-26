package com.lnlib.java.java12;

import java.text.NumberFormat;
import java.util.Locale;

public class CompactNumber
{
    public static void main(String[] args)
    {
        // -- US --

        var formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        formatter.setMaximumFractionDigits(1);
        System.out.println(formatter.format(12345)); // 12.3K

        formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        formatter.setMaximumFractionDigits(1);
        System.out.println(formatter.format(12345)); // 12.3 thousand

        // -- FR --

        formatter = NumberFormat.getCompactNumberInstance(Locale.FRENCH, NumberFormat.Style.SHORT);
        formatter.setMaximumFractionDigits(2);
        System.out.println(formatter.format(12345)); // 12,35 k

        formatter = NumberFormat.getCompactNumberInstance(Locale.FRENCH, NumberFormat.Style.LONG);
        formatter.setMaximumFractionDigits(2);
        System.out.println(formatter.format(12345)); // 12,35 mille
    }
}
