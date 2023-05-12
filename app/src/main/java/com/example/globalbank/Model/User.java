package com.example.globalbank.Model;

public class User {


    public int RIB;
    public String f_name;
    public String l_name;
    public float balance;





    public User() {
    }

    public User(int RIB, String f_name, String l_name, float balance) {
        this.RIB = RIB;
        this.f_name = f_name;
        this.l_name = l_name;
        this.balance = balance;
    }

    public int getRIB() {
        return RIB;
    }

    public void setRIB(int RIB) {
        this.RIB = RIB;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
