package examples;

record Person(String name, Integer age) {
}

enum Ability {WEAK, AVERAGE, STRONG}

record Player(Person person, Ability ability) {
}

record Doubles(Player player1, Player player2) {
}

record Pair<T>(T x, T y) {
}

public class RecordPatterns {
    public static void main(String[] args) {

        records();
    }

    private static void records() {
        Person p1 = new Person("Joe Bloggs", 22);
        System.out.println(p1);
        System.out.println(p1.name() + "; " + p1.age());
        patternMatching(p1);
        patternMatching("abc");

        Pair<String> pairS = new Pair<>("Sean", "Kennedy");
        Pair<Integer> pairI = new Pair<>(10, 20);
        patternMatching(pairS);
        patternMatching(pairI);
    }

    private static void patternMatching(Object obj) {

        if (obj instanceof Person person) {
            System.out.println("PM: " + person.name() + "; " + person.age());
        } else if (obj instanceof Pair pair) {
            System.out.println("PM: " + pair.x() + "; " + pair.y());
        }
    }
}
