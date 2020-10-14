package com.lnlib.bestpractice;

import java.util.Map;
import java.util.function.Function;

/**
 * The idea is to get rid of the big IFs when there are actions associated to each case.
 * A function (or any functional interface) can be associated to each case, then
 * just call the method to execute the functional interface.
 * <p>
 * https://www.developer.com/java/data/seven-ways-to-refactor-java-switch-statements.html
 * <p>
 * https://medium.com/swlh/5-ways-to-replace-if-else-statements-857c0ff19357
 */
public class FunctionalIf
{
    /**
     * common if with a default value
     */
    public String normalIf(Integer id)
    {
        if (id == 1) {
            return doSomethingWithMyInt(id);
        } else if (id == 2) {
            return doSomethingElseWithMyInt(id);
        } else if (id == 3) {
            return doSomethingWithMyInt(id);
        }
        return "NULL";
    }

    /**
     * This is the functional map. it does not need to be final if we are in a singleton bean instance in Spring.
     * Function can be changed to Supplier, Runnable, etc.
     */
    private final Map<Integer, Function<Integer, String>> map = Map.of(
            1, this::doSomethingWithMyInt,
            2, this::doSomethingElseWithMyInt,
            3, this::doSomethingWithMyInt);

    /**
     * Just call the map, and handle the default value
     */
    public String functionalIf(Integer id)
    {
        return map.getOrDefault(id, integer -> "NULL").apply(id);
    }


    private String doSomethingWithMyInt(Integer id)
    {
        System.out.println("FunctionalIf.doSomethingWithMyInt " + id);
        return id.toString();
    }

    private String doSomethingElseWithMyInt(Integer id)
    {
        System.out.println("FunctionalIf.doSomethingElseWithMyInt: " + id);
        return id.toString();
    }


}
