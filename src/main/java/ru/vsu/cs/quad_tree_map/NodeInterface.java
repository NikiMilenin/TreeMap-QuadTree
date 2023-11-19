package ru.vsu.cs.quad_tree_map;

public interface NodeInterface<K1, K2, T> {
    K1 getFirstKey();
    K2 getSecondKey();
    void setData(T data);
    T getData();
}
