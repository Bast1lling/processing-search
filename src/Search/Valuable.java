package Search;

public interface Valuable<T, U> {
    T getValue(U obj1, U obj2, Action action);
}
