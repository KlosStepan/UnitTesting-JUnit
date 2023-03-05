package cz.stkl.app;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void InsertFourUniqueTestKeys() {
        TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
        // - adding new mapping with the key different from all present mappings
        tm.put(100, "Stepan");
        tm.put(105, "Matylda");
        tm.put(110, "Tomas");
        tm.put(115, "Lukas");
        Assert.assertThat(tm.keySet(), Matchers.contains(100, 105, 110, 115));
        /*
         * for (Map.Entry<Integer, String> m : tm.entrySet()) {
         * System.out.println(m.getKey() + " " + m.getValue());
         * }
         */
    }

    @Test
    public void Insert7UniqueTestSize7() {
        TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
        tm.put(1, "Kocka");
        tm.put(2, "Pes");
        tm.put(3, "Papousek");
        tm.put(4, "Jesterka");
        tm.put(5, "Krokodyl");
        tm.put(6, "Zelva");
        tm.put(7, "Lachtan");
        Assert.assertEquals(tm.size(), 7);
    }

    @Test
    public void Insert3But2SameKeyTestRecentOnKey() {
        TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
        tm.put(40, "Cesnek");
        tm.put(50, "Paprika");
        tm.put(50, "Brambory");
        for (Map.Entry<Integer, String> m : tm.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
        // test if Brabory on 50;
    }

    @Test
    public void Insert50DeleteAllTestSize0() {
        TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
        for (int i = 0; i < 50; i++) {
            tm.put(i, "val" + i);
        }
        tm.clear();
        Assert.assertEquals(tm.size(), 0);
    }
}
