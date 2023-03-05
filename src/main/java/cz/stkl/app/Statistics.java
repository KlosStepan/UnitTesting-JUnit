package cz.stkl.app;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Statistics {
    Employees ppl;

    public Statistics(Employees empl) {
        ppl = empl;
    }

    public int computeAverageSalary() {
        Integer sum = 0;
        Integer it = 0;
        //
        Set<Integer> ids = ppl.getAll();
        Iterator<Integer> idsIterator = ids.iterator();
        while (idsIterator.hasNext()) {
            // System.out.println(idsIterator.next());
            sum += ppl.getSalary(idsIterator.next());
            it++;
        }
        return (sum / it);
    }

    public int getMinSalary() {
        Integer min = Integer.MAX_VALUE;
        //
        Set<Integer> ids = ppl.getAll();
        Iterator<Integer> idsIterator = ids.iterator();
        while (idsIterator.hasNext()) {
            // System.out.println(idsIterator.next());
            Integer currently_iterated = ppl.getSalary(idsIterator.next());
            if (currently_iterated < min) {
                min = currently_iterated;
            }
        }
        return min;
    }

    void printSalariesByName() {
        SortedMap<String, Integer> sm = new TreeMap<String, Integer>();
        //
        Set<Integer> ids = ppl.getAll();
        Iterator<Integer> idsIterator = ids.iterator();
        // iterate all ids and prepare sorted SortedMap of .getName(id) .getSalary(id)
        while (idsIterator.hasNext()) {
            Integer curr_idx = idsIterator.next();
            sm.put(ppl.getName(curr_idx), ppl.getSalary(curr_idx));
        }
        // sorted <name, salary> lines
        for (String name : sm.keySet()) {
            System.out.println("<" + name + ", " + sm.get(name) + ">");
        }
    }

    void LoopAll() {
        Set<Integer> ids = ppl.getAll();
        Iterator<Integer> idsIterator = ids.iterator();
        while (idsIterator.hasNext()) {
            System.out.println(idsIterator.next());
        }
    }
}
