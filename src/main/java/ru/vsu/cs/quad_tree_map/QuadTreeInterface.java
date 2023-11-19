package ru.vsu.cs.quad_tree_map;
import java.util.Iterator;

public interface QuadTreeInterface<K1 extends Comparable<K1>, K2 extends Comparable<K2>, T> extends Iterable<T>{
    void insert(K1 first_key, K2 second_key, T value);
    void delete(K1 first_key, K2 second_key);
    T get(K1 first_key, K2 second_key);
    Iterator<T> iterator();
}
