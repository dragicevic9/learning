package collectionsAndGenerics;

interface Moveable<T> {
    void move(T t);
}

class MoveInteger implements Moveable<Integer> {

    @Override
    public void move(Integer integer) {

    }
}

class SomeMoveable<T> implements Moveable<T> {
    @Override
    public void move(T c) {

    }
}

public class Testing {
}
