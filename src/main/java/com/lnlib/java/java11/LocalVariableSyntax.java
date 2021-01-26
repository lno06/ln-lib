package com.lnlib.java.java11;

import org.springframework.lang.Nullable;

import java.util.function.BiFunction;

/**
 * Type can be mandatory if you have to add an annotation
 */
public class LocalVariableSyntax
{
    public static void main(String[] args)
    {
        BiFunction<String, Integer, String> noType = (s, s2) -> s + s2;
        BiFunction<String, Integer, String> stringType = (@Nullable String s, Integer s2) -> s + s2;
        BiFunction<String, Integer, String> varType = (@Nullable var s, var s2) -> s + s2;

        // this is not possible
//        BiFunction<String, String, String> mixType = (String s, var s2) -> s + s2;
//        BiFunction<String, Integer, String> noType = (@Nullable s, s2) -> s + s2;
    }
}
