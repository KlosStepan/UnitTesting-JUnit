package cz.stkl.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class StatisticsTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void AverageOf3Salaries() {
        People p = new People();
        p.add("Svoboda", 32000);
        p.add("Hufnagl", 30000);
        p.add("Novak", 31000);
        Statistics s = new Statistics(p);
        // s.LoopAll();
        // System.out.println(s.computeAverageSalary());
        assertEquals(s.computeAverageSalary(), 31000);
    }

    @Test
    public void MinSalaryOf5() {
        People p = new People();
        p.add("Novotny", 35000);
        p.add("Pokora", 17000);
        p.add("Stedry", 9000);
        p.add("Dlouhy", 25000);
        p.add("Pospisil", 28000);
        Statistics s = new Statistics(p);
        assertEquals(s.getMinSalary(), 9000);
    }

    @Test
    public void Sort3GuysPrint() {
        People p = new People();
        p.add("Yvorsky", 15000);
        p.add("Brauner", 29500);
        p.add("Zabilov", 14000);
        p.add("Kohutek", 28000);
        p.add("Alsovsky", 17500);
        Statistics s = new Statistics(p);
        s.printSalariesByName();
    }
}
