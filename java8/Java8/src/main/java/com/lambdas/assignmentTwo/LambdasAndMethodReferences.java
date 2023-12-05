package com.lambdas.assignmentTwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class LambdasAndMethodReferences {
    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    private static void staticMR() {
        List<Integer> ints = Arrays.asList(1, 2, 7, 4, 5);
        Consumer<List<Integer>> sorter = (list) -> Collections.sort(list);
        sorter.accept(ints);
        System.out.println("Consumer sorted: " + ints);

        ints = Arrays.asList(1, 2, 7, 4, 5);
        Consumer<List<Integer>> sorterMR = Collections::sort;
        sorterMR.accept(ints);
        System.out.println("Consumer sorted MR: " + ints);
    }

    private static void boundMR() {
        String name = "Mr. Joe Bloggs";
        Predicate<String> startsWithPrefix = (prefix) -> name.startsWith(prefix);
        String prefix = "Mr.";
        System.out.println("Does name: " + name + " starts with prefix" + prefix + " -> " + startsWithPrefix.test(prefix));
        String secPrefix = "Ms.";
        System.out.println("Does name: " + name + " starts with prefix" + secPrefix + " -> " + startsWithPrefix.test(secPrefix));

        Predicate<String> startsWithPrefixMR = name::startsWith;
        System.out.println("Does name: " + name + " starts with prefix" + prefix + " -> " + startsWithPrefixMR.test(prefix));
        System.out.println("Does name: " + name + " starts with prefix" + secPrefix + " -> " + startsWithPrefixMR.test(secPrefix));
    }

    private static void unboundMR() {
        Predicate<String> isEmpty = (str) -> str.isEmpty();
        System.out.println("Is empty string empty -> " + isEmpty.test(""));
        System.out.println("Is xyz string empty -> " + isEmpty.test("xyz"));

        Predicate<String> isEmptyMR = String::isEmpty;
        System.out.println("Is empty string empty -> " + isEmptyMR.test(""));
        System.out.println("Is xyz string empty -> " + isEmptyMR.test("xyz"));


        BiPredicate<String, String> startsWith = (first, second) -> first.startsWith(second);
        System.out.println("Does string Mr. Joe start with Mr. -> " + startsWith.test("Mr. Joe", "Mr."));
        System.out.println("Does string Mr. Joe start with Ms. -> " + startsWith.test("Mr. Joe", "Ms."));

        BiPredicate<String, String> startsWithMR = String::startsWith;
        System.out.println("Does string Mr. Joe start with Mr. -> " + startsWithMR.test("Mr. Joe", "Mr."));
        System.out.println("Does string Mr. Joe start with Ms. -> " + startsWithMR.test("Mr. Joe", "Ms."));
    }


    private static void constructorMR() {
        Supplier<List<String>> newArrayList = () -> new ArrayList<>();
        List<String> list = newArrayList.get();
        list.add("Lambda");
        System.out.println(list);

        Supplier<List<String>> newArrayListMR = ArrayList::new;
        list = newArrayListMR.get();
        list.add("Method Reference");
        System.out.println(list);

        Function<Integer, List<String>> newArrayListWithParam = (n) -> new ArrayList<>(n);
        list = newArrayListWithParam.apply(10);
        list.add("Lambda");
        System.out.println(list);

        Function<Integer, List<String>> newArrayListWithParamMR = ArrayList::new;
        list = newArrayListWithParamMR.apply(10);
        list.add("Method Reference");
        System.out.println(list);
    }
}
