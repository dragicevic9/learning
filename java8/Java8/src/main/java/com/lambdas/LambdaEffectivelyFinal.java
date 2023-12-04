package com.lambdas;

import java.util.*;
import java.util.function.Predicate;

public class LambdaEffectivelyFinal {
    String name = "";

    public static void main(String[] args) {

        List<String> a1 = new ArrayList<>();
        a1.add("John");

        int x = 12; // final or effectively final

        // Lambdas take a snapshot of local variables;
        // these local vars MUST NOT change. Only setting up lambda here.
        Predicate<String> lambda = s -> {
//             x++; //(error)
            new LambdaEffectivelyFinal().name = "Kennedy"; // instance/class vars are ok
            System.out.println("x = " + x);
            return s.isEmpty() && x % 2 == 0;
        };
        filterData(a1, lambda); // lambda views 'x' as 12
        System.out.println(a1);

        new LambdaEffectivelyFinal().name = "Sean"; // instance/class vars are ok

        // If 'x' was allowed to change, then the method and the lambda would
        // have 2 different views of 'x'

        // x++; (error)
        filterData(a1, lambda); // lambda views 'x' as 12
    }

    private static void filterData(List<String> list, Predicate<String> lambda) {
        Iterator<String> i = list.iterator();

        while (i.hasNext()) {
            if (lambda.test(i.next()))  // lambda execution here
                i.remove();
        }
    }
}
