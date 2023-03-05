package cz.stkl.app;

public class Person {
    Integer id;
    String name;
    Integer salary;

    public Person(Integer _id, String _name, Integer _salary) {
        this.id = _id;
        this.name = _name;
        this.salary = _salary;
    }

    public String getName() {
        return this.name;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(int new_salary) {
        this.salary = new_salary;
    }

}
