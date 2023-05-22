package com.example.globalbank.Model;

public class User {
    public String ribNumber;
    public String email;
    public String password;

    public float balance;

    public String sname;

    public User() {
    }

    public User( String email, String password, float balance, String sname) {

        this.email = email;
        this.password = password;
        this.balance = balance;
        this.sname = sname;
    }

    public String getRibNumber() {
        return ribNumber;
    }

    public void setRibNumber(String ribNumber) {
        this.ribNumber = ribNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
