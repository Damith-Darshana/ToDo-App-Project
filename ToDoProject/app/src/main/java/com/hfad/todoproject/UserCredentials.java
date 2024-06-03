package com.hfad.todoproject;

public class UserCredentials {

    private  String UserName;
    private  String Email;

    private  String Password;

    public  UserCredentials(String username,String email,String password){
        this.UserName = username;
        this.Email = email;
        this.Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
