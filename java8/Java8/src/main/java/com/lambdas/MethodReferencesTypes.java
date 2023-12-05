package com.lambdas;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodReferencesTypes {


    public static void main(String[] args) {
        boundMethodReferences();
        unboundMethodReferences();
        staticMethodReferences();
        constructorMethodReferences();
    }

    public static void boundMethodReferences() {
        String name = "Mr. Joe Bloggs";
        Supplier<String> lowerL = () -> name.toLowerCase(); // lambda
        Supplier<String> lowerMR = name::toLowerCase;       // method reference

        // Bound MR (bounded to name var) -> don't have to specify which instance to call it on
        System.out.println(lowerMR.get());

        Predicate<String> titleL = (title) -> name.startsWith(title);
        Predicate<String> titleMR = name::startsWith;
//        Even though startsWith is overloaded, boolean startsWith(String) and boolean startsWith(String, int),
//        because we are creating a Predicate which has a functional method of test(T t), the startsWith(String) is used
//        This is where "context" is important

        System.out.println(titleL.test("Mr."));
        System.out.println(titleMR.test("Ms."));
    }

    public static void unboundMethodReferences() {
        Function<String, String> upperL = s -> s.toUpperCase();
        Function<String, String> upperMR = String::toUpperCase;
        // The function is unbound, so you need to specify which instance to call it on
        System.out.println(upperL.apply("sean"));
        System.out.println(upperMR.apply("sean"));

        BiFunction<String, String, String> concatL = (s1, s2) -> s1.concat(s2);
        BiFunction<String, String, String> concatMR = String::concat;
        System.out.println(concatL.apply("Sean ", "Kennedy"));

        // 1st parameter is used for executing the instance method
        // "Sean ".concat("Kennedy");
        System.out.println(concatMR.apply("Sean ", "Kennedy"));
    }

    public static void staticMethodReferences() {
//        Static method references are considered UNBOUND also. An example for static method is
//        Collections.sort(List)

        Consumer<List<Integer>> sortL = list -> Collections.sort(list);
        Consumer<List<Integer>> sortMR = Collections::sort;

        List<Integer> listOfNumbers = Arrays.asList(2, 1, 5, 4, 9);
        sortL.accept(listOfNumbers);    // execution
        System.out.println(listOfNumbers);

        listOfNumbers = Arrays.asList(8, 12, 4, 3, 7);
        sortMR.accept(listOfNumbers);   // execution
        System.out.println(listOfNumbers);
    }

    public static void constructorMethodReferences() {
        Supplier<StringBuilder> sbL = () -> new StringBuilder(); // lambda
        Supplier<StringBuilder> sbMR = StringBuilder::new;

        StringBuilder sb1 = sbL.get();
        sb1.append("lambda version");
        System.out.println(sb1);

        StringBuilder sb2 = sbMR.get();
        sb2.append("method reference version");
        System.out.println(sb2);

        Function<Integer, List<String>> alL = x -> new ArrayList<>(x);
        Function<Integer, List<String>> alMR = ArrayList::new;

        List<String> ls1 = alL.apply(10);   // size 10
        ls1.add("21");
        System.out.println(ls1);
        List<String> ls2 = alMR.apply(5);
        ls2.add("88");
        System.out.println(ls2);
    }
}
