package cz.stkl.app;

public class Statistics {
    Employees ppl;

    public Statistics(Employees empl) {
        ppl = empl;
    }

    public int computeAverageSalary() {
        // loop&calculate
        return 35400;
    }

    public int getMinSalary() {
        // loop&keepMin
        return 14000;
    }

    void printSalariesByName() {
        // pairs
        // foreach (p in ppl)
        // {
        // take <p.name, p.salary>
        // }
        // pairs sort by name
        // foreach(pair in pairs)
        // {
        // print(p.name, p.salary)
        // }
    }
}
