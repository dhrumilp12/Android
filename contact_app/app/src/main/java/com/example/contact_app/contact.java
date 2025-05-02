package com.example.contact_app;

public class contact {
    public int sno;
    public String name;
    public String number;
    public contact(int sno, String name, String number) {
        this.sno = sno;
        this.name = name;
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;}
    public int getSno() {
        return sno;
    }
    public void setSno(int sno) {
        this.sno = sno;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
