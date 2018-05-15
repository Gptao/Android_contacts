package com.example.mx.task1;

/**
 * Created by MX on 2018/5/12.
 */

public class Info {
   // CREATE TABLE TxlTbl (_id INTEGER DEFAULT '1' NOT NULL PRIMARY KEY AUTOINCREMENT,Name TEXT,MobileNum TEXT,PhoneNum TEXT,Email TEXT,Address TEXT)";
    private int Id;
    private String Name;
    private String Mobilenum;
    private String PhoneNum;
    private String Email;
    private String Address;

    public Info(int id,String name, String mobilenum, String phoneNum, String email, String address) {
        this.Id=id;
        this.Name = name;
        this.Mobilenum = mobilenum;
        this.PhoneNum=phoneNum;
        this.Email=email;
        this.Address=address;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobilenum() {
        return Mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        Mobilenum = mobilenum;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
