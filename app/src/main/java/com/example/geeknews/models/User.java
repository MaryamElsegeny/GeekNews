package com.example.geeknews.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("password2")
    private String passwordConfirmation ;
    @SerializedName("first_name")
    private String firstname ;
    @SerializedName("last_name")
    private String lastname ;
    @SerializedName("email")
    private String email ;
    @SerializedName("user_category")
    private ArrayList<String> CategoryName;
    @SerializedName("access")
    private String accessToken;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String passwordConfirmation, String firstname, String lastname, String email, ArrayList<String> categoryName) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        CategoryName = categoryName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public ArrayList<String> getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(ArrayList<String> categoryName) {
        CategoryName = categoryName;
    }
}
