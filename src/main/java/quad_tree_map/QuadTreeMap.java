package quad_tree_map;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class QuadTreeMap<K1 extends Comparable<K1>, K2 extends Comparable<K2>, T> implements QuadTreeInterface<K1, K2, T> {

    private static class Node<K1, K2, T> implements NodeInterface<K1, K2, T> {
        private final K1 first_key;
        private final K2 second_key;
        private T data;

        public Node(K1 first_key, K2 second_key, T data) {
            this.first_key = first_key;
            this.second_key = second_key;
            this.data = data;
        }

        public K1 getFirstKey() {
            return first_key;
        }

        public K2 getSecondKey() {
            return second_key;
        }

        public T getData() {
            return data;
        }

        public void setData(T new_data) {
            data = new_data;
        }
    }

    private QuadTreeMap<K1, K2, T> NorthWest = null;
    private QuadTreeMap<K1, K2, T> NorthEast = null;
    private QuadTreeMap<K1, K2, T> SouthWest = null;
    private QuadTreeMap<K1, K2, T> SouthEast = null;
    private Node<K1, K2, T> node = null;

    @Override
    public void insert(K1 first_key, K2 second_key, T value) {
        if (node == null) {
            node = new Node<>(first_key, second_key, value);
        }
        K1 node_first_key = node.getFirstKey();
        K2 node_second_key = node.getSecondKey();
        if (node_first_key.compareTo(first_key) == 0 && node_second_key.compareTo(second_key) ==0) {
            node.setData(value);
        } else if (node_first_key.compareTo(first_key) <= 0 && node_second_key.compareTo(second_key) >= 0) {
            if (NorthEast == null) {
                NorthEast = new QuadTreeMap<>();
            }
            NorthEast.insert(first_key, second_key, value);
        } else if (node_first_key.compareTo(first_key) >= 0 && node_second_key.compareTo(second_key) >= 0) {
            if (NorthWest == null) {
                NorthWest = new QuadTreeMap<>();
            }
            NorthWest.insert(first_key, second_key, value);
        } else if (node_first_key.compareTo(first_key) >= 0 && node_second_key.compareTo(second_key) <= 0) {
            if (SouthWest == null) {
                SouthWest = new QuadTreeMap<>();
            }
            SouthWest.insert(first_key, second_key, value);
        } else if (node_first_key.compareTo(first_key) <= 0 && node_second_key.compareTo(second_key) <= 0) {
            if (SouthEast == null) {
                SouthEast = new QuadTreeMap<>();
            }
            SouthEast.insert(first_key, second_key, value);
        } else {
            System.out.println("Проблемы со вставкой!!!");
        }
    }

    @Override
    public T get(K1 first_key, K2 second_key) {
        if (node == null) return null;
        K1 node_first_key = node.getFirstKey();
        K2 node_second_key = node.getSecondKey();
        if (node_first_key.compareTo(first_key) == 0 && node_second_key.compareTo(second_key) == 0) {
            return node.getData();
        }
        if (node_first_key.compareTo(first_key) <= 0 && node_second_key.compareTo(second_key) >= 0) {
            if (NorthEast == null) {
                System.out.println("Проблемы с получением по ключу");
                return null;
            }
            return NorthEast.get(first_key, second_key);
        } else if (node_first_key.compareTo(first_key) >= 0 && node_second_key.compareTo(second_key) >= 0) {
            if (NorthWest == null) {
                System.out.println("Проблемы с получением по ключу");
                return null;
            }
            return NorthWest.get(first_key, second_key);
        } else if (node_first_key.compareTo(first_key) >= 0 && node_second_key.compareTo(second_key) <= 0) {
            if (SouthWest == null) {
                System.out.println("Проблемы с получением по ключу");
                return null;
            }
            return SouthWest.get(first_key, second_key);
        } else if (node_first_key.compareTo(first_key) <= 0 && node_second_key.compareTo(second_key) <= 0) {
            if (SouthEast == null) {
                System.out.println("Проблемы с получением по ключу");
                return null;
            }
            return SouthEast.get(first_key, second_key);
        } else {
            System.out.println("Проблемы с получением по ключу");
            return null;
        }
    }

    @Override
    public void delete(K1 first_key, K2 second_key) {
        if (node == null) return;
        K1 node_first_key = node.getFirstKey();
        K2 node_second_key = node.getSecondKey();
        if (node_first_key.compareTo(first_key) == 0 && node_second_key.compareTo(second_key) == 0) {
            LinkedList<Node<K1, K2, T>> nodes = new LinkedList<>();
            this.addLeaves(nodes);
            nodes.remove(0);
            NorthEast = null;
            NorthWest = null;
            SouthEast = null;
            SouthWest = null;
            this.node = null;
            for (Node<K1, K2, T> t : nodes) {
                this.insert(t.getFirstKey(), t.getSecondKey(), t.data);
            }
        } else if (node_first_key.compareTo(first_key) <= 0 && node_second_key.compareTo(second_key) >= 0) {
            if (NorthEast == null) {
                System.out.println("Проблемы с получением по ключу");
                return;
            }
            NorthEast.delete(first_key, second_key);
        } else if (node_first_key.compareTo(first_key) >= 0 && node_second_key.compareTo(second_key) >= 0) {
            if (NorthWest == null) {
                System.out.println("Проблемы с получением по ключу");
                return;
            }
            NorthWest.delete(first_key, second_key);
        } else if (node_first_key.compareTo(first_key) >= 0 && node_second_key.compareTo(second_key) <= 0) {
            if (SouthWest == null) {
                System.out.println("Проблемы с получением по ключу");
                return;
            }
            SouthWest.delete(first_key, second_key);
        } else if (node_first_key.compareTo(first_key) <= 0 && node_second_key.compareTo(second_key) <= 0) {
            if (SouthEast == null) {
                System.out.println("Проблемы с получением по ключу");
                return;
            }
            SouthEast.delete(first_key, second_key);
        } else {
            System.out.println("Проблемы с получением по ключу");
        }
    }

    private void addLeaves(LinkedList<Node<K1, K2, T>> nodes) {
        if (node == null) return;
        nodes.add(node);
        if(NorthWest != null ) NorthWest.addLeaves(nodes);
        if(NorthEast != null ) NorthEast.addLeaves(nodes);
        if(SouthEast != null ) SouthEast.addLeaves(nodes);
        if(SouthWest != null ) SouthWest.addLeaves(nodes);
    }
    @Override
    public Iterator<T> iterator() {
        QuadTreeMap<K1, K2, T> root = this;
        return new Iterator<T>() {

            Stack<QuadTreeMap<K1, K2, T>> stack= new Stack<>();
            {
                stack.addElement(root);
            }

            public boolean hasNext() {
                return !stack.empty();
            }

            public T next() {
                QuadTreeMap<K1, K2, T> node = stack.pop();
                if(node.NorthWest != null && node.NorthWest.node != null) stack.push(node.NorthWest);
                if(node.NorthEast != null && node.NorthEast.node != null) stack.push(node.NorthEast);
                if(node.SouthEast != null && node.SouthEast.node != null) stack.push(node.SouthEast);
                if(node.SouthWest != null && node.SouthWest.node != null) stack.push(node.SouthWest);

                return node.node.getData();
            }
        };
    }
}
