package com.lambdas.assignmentOne;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

interface Printable<T> {
    void print(T t);
}

interface Retrievable<T> {
    T retrieve();
}

interface Evaluate<T> {
    boolean isNegative(T t);
}

interface Functionable<T, R> {
    R applyThis(T t);
}


public class BasicLambdas {

    public static void main(String[] args) {
        BasicLambdas assignment = new BasicLambdas();
        assignment.consumer();
        assignment.supplier();
        assignment.predicate();
        assignment.function();

        List<Person> listPeople = getPeople();
        sortAge(listPeople);
        sortName(listPeople);
        sortHeight(listPeople);
    }

    private void consumer() {
        Printable<String> oneA = (s) -> System.out.println(s);
        oneA.print("Printable lambda");

        Consumer<String> oneBLambda = (s) -> System.out.println(s);
        Consumer<String> oneBMR = System.out::println;
        oneBLambda.accept("Consumer lambda");
        oneBMR.accept("Consumer method reference");
    }

    private void supplier() {
        Retrievable<Integer> twoA = () -> 77;
        System.out.println("Retrievable: " + twoA.retrieve());
        Supplier<Integer> twoB = () -> 77;
        System.out.println("Supplier: " + twoB.get());
    }

    private void predicate() {
        Evaluate<Integer> threeA = (i) -> i < 0;
        System.out.println("3. a) Evaluate -1 -> " + threeA.isNegative(-1));
        System.out.println("3. a) Evaluate +1 -> " + threeA.isNegative(+1));

        Predicate<Integer> threeB = (i) -> i < 0;
        System.out.println("3. b) Predicate -1 -> " + threeB.test(-1));
        System.out.println("3. b) Predicate +1 -> " + threeB.test(+1));

        Predicate<Integer> evenNumber = (i) -> i % 2 == 0;
        Predicate<String> startsWithMr = (s) -> s.startsWith("Mr.");
        Predicate<Person> isAdult = (person) -> person.getAge() >= 18;

        System.out.println("3. c) Predicate even num 4 -> " + check(4, evenNumber));
        System.out.println("3. c) Predicate even num 7 -> " + check(7, evenNumber));

        System.out.println("3. c2) check Mr. Joe startsWithMr Predicate -> " + check("Mr. Joe Bloggs", startsWithMr));
        System.out.println("3. c2) check Ms. Ann startsWithMr Predicate -> " + check("Ms. Ann Bloggs", startsWithMr));

        System.out.println("3. c3 check 33 age isAdult Predicate -> " + check(new Person("Mike", 33, 1.8), isAdult));
        System.out.println("3. c3 check 13 age isAdult Predicate-> " + check(new Person("Ann", 13, 1.4), isAdult));
    }

    private static <T> boolean check(T a, Predicate<T> predicate) {
        return predicate.test(a);
    }

    private void function() {
        Functionable<Integer, String> fourA = (i) -> "Number is: " + i;
        System.out.println("4. a) Functionable -> " + fourA.applyThis(25));

        Function<Integer, String> fourB = (i) -> "Number is: " + i;
        System.out.println("4. b) Function -> " + fourB.apply(25));
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));

        return result;
    }

    private static void sortAge(List<Person> listPeople) {
        Function<Person, Integer> ascAge = Person::getAge;
        listPeople.sort(Comparator.comparing(ascAge));
        System.out.println("==== Age Sorted ====");
        listPeople.forEach((System.out::println));
    }

    private static void sortHeight(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(Person::getHeight));
        System.out.println("==== Height Sorted ====");
        listPeople.forEach(System.out::println);
    }

    private static void sortName(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(Person::getName));
        System.out.println("==== Name Sorted ====");
        listPeople.forEach(System.out::println);
    }


}
