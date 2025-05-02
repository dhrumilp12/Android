package com.example.finalsec;

public class contact {
    private int sno;

    private String name;
    private String phoneNo;

    public contact(int sno, String name, String phoneNo) {
        this.sno = sno;

        this.name = name;
        this.phoneNo = phoneNo;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
