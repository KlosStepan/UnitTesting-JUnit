# UnitTesting-JUnit
We wrote tests for Java structure, classes etc. to learn JUnit (w/ hamcrest) and Apache Maven project setup. 
# I. TreeMap<K,V> tests
main/.. **App.java** & test/.. **AppTest.java**
- public void InsertFourUniqueTestKeys()  
- public void Insert7UniqueTestSize7()  
- public void Insert3But2SameKeyTestRecentOnKey()  
- public void Insert50DeleteAllTestSize0()  
# II. Custom structure - interfaces, more complex functions
Interface Employees   
```java
    int add(String name, int salary); // returns ID
    Set<Integer> getAll(); // returns a set of IDs
    String getName(int id);
    int getSalary(int id);
    void changeSalary(int id, int newSalary);
```
Functions for Statistics
```java
    int computeAverageSalary();
    int getMinSalary();
    void printSalariesByName(); // prints the list of pairs <name, salary> that is sorted by employee names
```
 Person -> People implements Employees -> Statistics(ctor (object&: Employees))
- AverageOf3Salaries()  
- MinSalaryOf5()  
- Sort5GuysPrint()    
# Idk what's 3rd - some data stuff
TODO..