package com.lnlib.java.java12;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * It enables to apply 2 Collectors
 */
public class Teeing
{
    public static void main(String[] args)
    {
        minMax();
        listCount();
    }

    private static void listCount()
    {
        var result = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(
                        Collectors.filtering(i -> i > 3, Collectors.toList()),
                        Collectors.filtering(i -> i > 3, Collectors.counting()),
                        (list, count) -> "list filtered " + list + ", count filtered " + count));
        System.out.println(result);
    }

    private static void minMax()
    {
        var result = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(Collectors.maxBy(Integer::compareTo),
                        Collectors.minBy(Integer::compareTo),
                        (max, min) -> "min " + min + ", max " + max));
        System.out.println(result);
    }
}
