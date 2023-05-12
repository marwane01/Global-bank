package com.example.globalbank.Model;

public class L_User {

    public int RIB;

    public String email;

    public String password;

    public L_User() {
    }

    public L_User(int RIB, String email, String password) {
        this.RIB = RIB;
        this.email = email;
        this.password = password;
    }


    public int getRIB() {
        return RIB;
    }

    public void setRIB(int RIB) {
        this.RIB = RIB;
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
}
