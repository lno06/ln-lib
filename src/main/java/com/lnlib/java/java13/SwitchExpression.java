package com.lnlib.java.java13;

public class SwitchExpression
{
    public static void main(String[] args)
    {
        classic();
        newWay();
    }

    private static void newWay()
    {
        var what = "house";

        // no need to add 'break'
        var result = switch (what) {
            case "house", "flat" -> "immobile";
            case "human", "dog" -> "mobile";
            default -> {
                if (what.length() > 2) {
                    yield "Big NA";
                } else {
                    yield "Small NA";
                }
            }
        };
        System.out.println("result = " + result);
    }

    private static void classic()
    {
        String result;

        var what = "house";

        switch (what) {
            case "house":
            case "flat":
                result = "immobile";
                break;
            case "human":
            case "dog":
                result = "mobile";
                break;
            default:
                result = "NA";
        }

        System.out.println("result = " + result);

    }

}
