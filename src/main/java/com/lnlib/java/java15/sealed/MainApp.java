package com.lnlib.java.java15.sealed;

public class MainApp
{
    public static void main(String[] args)
    {
        oldWay();
        newWay();
    }

    private static void newWay()
    {
        var glass = new Drink();
        if (glass instanceof Alcohol alcohol) {
            var degree = alcohol.getDegree();
            System.out.println(degree);
        } else if (glass instanceof Soda soda) {
            var sugar = soda.getSugar();
            System.out.println(sugar);
        } else {
            System.out.println("Unknown drink");
        }
    }

    private static void oldWay()
    {
        var glass = new Drink();
        if (glass instanceof Alcohol) {
            var degree = ((Alcohol) glass).getDegree();
            System.out.println(degree);
        } else if (glass instanceof Soda) {
            var sugar = ((Soda) glass).getSugar();
            System.out.println(sugar);
        } else {
            System.out.println("Unknown drink");
        }
    }


}
