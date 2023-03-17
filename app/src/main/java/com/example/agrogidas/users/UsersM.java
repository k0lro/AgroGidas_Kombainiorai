package com.example.agrogidas.users;

public class UsersM {

    String name, sname, email, passwd;

    public UsersM(){
    }

    public UsersM(String name, String sname, String email, String pass){
        this.name = name;
        this.sname = sname;
        this.email = email;
        this.passwd = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
