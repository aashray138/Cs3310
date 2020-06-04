package edu.wmich.cs3310.Spring2020.ShresthaA;

public interface MyList {
    void add(Character character);
    void append(MyList myList);
    PerfectList.Node getFirst();
    PerfectList.Node getLast();
    int count(char c);
    void insert(int position, char c);
    void sortedInsert(char c);
    void clear();
    boolean remove(Character character);
    Object[] toArray();
    int size();
    boolean contains(Character character);
    Object get(int index); //additional
}
