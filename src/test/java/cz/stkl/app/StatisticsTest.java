package cz.stkl.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    public void Sort5GuysPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        People p = new People();
        p.add("Yvorsky", 15000);
        p.add("Brauner", 29500);
        p.add("Zabilov", 14000);
        p.add("Kohutek", 28000);
        p.add("Alsovsky", 17500);

        Statistics s = new Statistics(p);
        s.printSalariesByName();

        String expected = "<Alsovsky, 17500>\n<Brauner, 29500>\n<Kohutek, 28000>\n<Yvorsky, 15000>\n<Zabilov, 14000>";
        assertEquals(expected, outContent.toString().trim());

        System.setOut(null);
        System.setErr(null);
    }
}
