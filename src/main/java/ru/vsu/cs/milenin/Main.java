package ru.vsu.cs.milenin;


import ru.vsu.cs.quad_tree_map.QuadTreeMap;

public class Main {
    public static void main(String[] args) {
        QuadTreeMap<Integer,Integer, Integer> map = new QuadTreeMap<>();
        map.insert(1, 1, 3);
        map.insert(3, 2, 5);
        map.insert(2, 2, 113);
        map.insert(1, 7, 11);
        map.insert(3, 7, 22);
        for (Integer t:map) {
            System.out.print(t);
            System.out.print(" ");
        }
        System.out.println();
        map.delete(1, 1);
        for (Integer t:map) {
            System.out.print(t);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println(map.get(3, 2));
        System.out.println(map.get(2, 2));
        System.out.println(map.get(1, 7));
        System.out.println(map.get(3, 7));
    }
}