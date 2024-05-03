package fr.foxelia.tools.java.pair;

/**
 * A simple class to store a pair of objects.
 * <br><br>License: CC BY-SA 4.0
 * @param <T1> The type of the first object.
 *            <br>Example: String, Integer, etc.
 * @param <T2> The type of the second object.
 *            <br>Example: String, Integer, etc.
 *
 * @author Foxelia, Zarinoow
 * @version 1.0
 */
public class Pair<T1, T2> {

    private T1 first;
    private T2 second;

    public Pair(T1 key, T2 value) {
        this.first = key;
        this.second = value;
    }

    public T1 getFirst() {
        return this.first;
    }

    public T2 getSecond() {
        return this.second;
    }
}
