# UnitTesting-JUnit
We wrote tests for Java structure, classes etc. in order to learn JUnit (w/ hamcrest) and Apache Maven project setup. 
# I. TreeMap<K,V> tests
main/.. **App.java** & test/.. **AppTest.java**
# II. Custom structure - interfaces, more complex functions
Interface Employee   
```java
    int add(String name, int salary); // returns ID
    Set<Integer> getAll(); // returns a set of IDs
    String getName(int id);
    int getSalary(int id);
    void changeSalary(int id, int newSalary);
```
Person->People:Employees->Statistics(ctor accepts &object:Employees)   
# Idk what's 3rd