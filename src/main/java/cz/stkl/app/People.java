package cz.stkl.app;

import java.util.Set;
import java.util.HashSet;
import java.util.Hashtable;

interface Employees {
    int add(String name, int salary); // returns ID

    Set<Integer> getAll(); // returns a set of IDs

    String getName(int id);

    int getSalary(int id);

    void changeSalary(int id, int newSalary);
}

public class People implements Employees {
    Hashtable<Integer, Person> list;
    Integer sequence_id; // for stamping people

    public People() {
        list = new Hashtable<Integer, Person>();
        sequence_id = 0;
    }

    public int add(String name, int salary) {
        sequence_id += 1;
        list.put(sequence_id, new Person(sequence_id, name, salary));
        return sequence_id;
    }

    public Set<Integer> getAll() {
        return list.keySet();
    }

    public String getName(int id) {
        return ((list.get(id)).getName());
    }

    public int getSalary(int id) {
        return ((list.get(id)).getSalary());
    }

    public void changeSalary(int id, int newSalary) {
        (list.get(id)).setSalary(newSalary);
    }
}
