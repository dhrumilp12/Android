package com.example.database;

public class Employee {
    int sno;
    String name;
    double increment;



    public Employee(String name, double increment, int sno) {
        this.name = name;
        this.increment = increment;
        this.sno = sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    public int getSno() {
        return sno;
    }

    public String getName() {
        return name;
    }

    public double getIncrement() {
        return increment;
    }

}