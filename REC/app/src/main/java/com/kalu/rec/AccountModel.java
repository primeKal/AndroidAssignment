package com.kalu.rec;

public class AccountModel {
    private String fullname;
    private String username;
    private String email;
    private String phoneno;
    private String sex;


    public AccountModel() {
    }


    public AccountModel(String email, String fullname, String phoneno, String sex, String username) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phoneno = phoneno;
        this.sex = sex;

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}