package com.lambdas;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class APIFunctionalInterfaces {

    public static void main(String[] args) {

        APIFunctionalInterfaces fiAPI = new APIFunctionalInterfaces();
        fiAPI.predicate();
        fiAPI.supplier();
        fiAPI.consumer();
        fiAPI.function();
        fiAPI.unaryBinaryOperator();

    }

    public void predicate() {
        Predicate<String> pStr = s -> s.contains("City");
        System.out.println(pStr.test("Vatican City")); // true

        // BiPredicate<T, U> is a functional interface, one abstract method: boolean test(T t, U u);
        BiPredicate<String, Integer> checkLength = (str, len) -> str.length() == len;
        System.out.println(checkLength.test("Vatican City", 8)); // false
    }

    public void supplier() {
        // Supplier<T> is a functional interface, one abstract method: T get()
        Supplier<StringBuilder> supSB = () -> new StringBuilder();
        System.out.println("Supplier SB: " + supSB.get().append("SK")); // Supplier SB: SK

        Supplier<LocalTime> supTime = () -> LocalTime.now();
        System.out.println("Supplier time: " + supTime.get());

        Supplier<Double> sRandom = () -> Math.random();
        System.out.println(sRandom.get());
    }

    public void consumer() {
        // Consumer<T> is a functional interface, one abstract method: void accept(T t);
        Consumer<String> printC = s -> System.out.println(s); // lambda
        printC.accept("To be or not to be, that is the question");

        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Mary");
        names.forEach(printC); // John, Mary


        // BiConsumer<T, U> is a functional interface, one abstract method: void accept(T t, U u);
        Map<String, String> mapCapitalCities = new HashMap<>();
        BiConsumer<String, String> biCon = (key, value) -> mapCapitalCities.put(key, value);
        // The return value of put(k,v) is just ignored (and not returned from the lambda)
        biCon.accept("Dublin", "Ireland");
        biCon.accept("Washington D.C.", "USA");
        System.out.println(mapCapitalCities);

        BiConsumer<String, String> mapPrint = (key, value) -> System.out.println(key + " is the capital of: " + value);
        mapCapitalCities.forEach(mapPrint);
    }

    public void function() {
        // Function<T, R> is a functional interface, one abstract method: R apply(T t)
        Function<String, Integer> fn2 = s -> s.length();
        System.out.println("Function: " + fn2.apply("Moscow")); // 6

        // BiFunction<T, U, R> is a functional interface, one abstract method: R apply(T t, U u)
        BiFunction<String, String, Integer> biFn = (s1, s2) -> s1.length() + s2.length();
        System.out.println("BiFunction: " + biFn.apply("William", "Shakespeare")); // 18

        BiFunction<String, String, String> biFn2 = (s1, s2) -> s1.concat(s2);
        System.out.println("BiFunction: " + biFn2.apply("William ", "Shakespeare"));
    }

    public void unaryBinaryOperator() {
        // UnaryOperator<T> extends Function<T, T> is a functional interface: one abstract method: T apply(T t)
        UnaryOperator<String> unaryOp = name -> "My name is: " + name;
        System.out.println("UnaryOperator: " + unaryOp.apply("Sean"));

        // BinaryOperator<T> extends BiFunction<T, T, T> is a functional interface: one abstract method: T apply(T t1, T t2)
        BinaryOperator<String> binaryOp = (s1, s2) -> s1.concat(s2);
        System.out.println("BinaryOperator: " + binaryOp.apply("William ", "Shakespeare"));
    }
}
