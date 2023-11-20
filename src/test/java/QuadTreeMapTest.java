import org.junit.Assert;
import org.junit.Test;
import quad_tree_map.QuadTreeMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class QuadTreeMapTest {

    @Test
    public void insertionTest() {
        QuadTreeMap<Integer,Integer, Integer> map = new QuadTreeMap<>();
        map.insert(1, 1, 3);
        map.insert(3, 2, 5);
        map.insert(2, 2, 113);
        map.insert(1, 7, 11);
        map.insert(3, 7, 22);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(5);
        expected.add(113);
        expected.add(11);
        expected.add(22);
        Collections.sort(expected);

        ArrayList<Integer> actual = new ArrayList<>();
        for(Integer t: map) {
            actual.add(t);
        }
        Collections.sort(actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deletionTest() {
        QuadTreeMap<Integer,Integer, Integer> map = new QuadTreeMap<>();
        map.insert(1, 1, 3);
        map.insert(3, 2, 5);
        map.insert(2, 2, 113);
        map.insert(1, 7, 11);
        map.insert(3, 7, 22);
        map.insert(1, 11, 223);
        map.insert(220, 13, 90);
        map.delete(2, 2);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(5);
        expected.add(11);
        expected.add(22);
        expected.add(223);
        expected.add(90);
        Collections.sort(expected);

        ArrayList<Integer> actual = new ArrayList<>();
        for(Integer t: map) {
            actual.add(t);
        }
        Collections.sort(actual);
        Assert.assertEquals(expected, actual);

        map.delete(3, 7);
        expected = new ArrayList<>();
        expected.add(3);
        expected.add(5);
        expected.add(11);
        expected.add(223);
        expected.add(90);
        Collections.sort(expected);
        actual = new ArrayList<>();
        for(Integer t: map) {
            actual.add(t);
        }
        Collections.sort(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void gettingTest() {
        QuadTreeMap<Integer,Integer, Integer> map = new QuadTreeMap<>();
        map.insert(1, 1, 3);
        map.insert(3, 2, 5);
        map.insert(2, 2, 113);
        map.insert(1, 7, 11);
        map.insert(3, 7, 22);

        Integer expected = 3;
        Integer actual = map.get(1, 1);
        Assert.assertEquals(expected, actual);

        expected = 5;
        actual = map.get(3, 2);
        Assert.assertEquals(expected, actual);

        expected = 113;
        actual = map.get(2, 2);
        Assert.assertEquals(expected, actual);
    }
}
