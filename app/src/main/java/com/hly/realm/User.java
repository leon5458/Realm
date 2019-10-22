package com.hly.realm;

import io.realm.RealmObject;

public class User extends RealmObject {
    private String phone;
    private String pw;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
