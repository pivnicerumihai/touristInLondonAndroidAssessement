package com.example.londontourism.Model;

public class Users {

    String first_name, last_name, email_address;

    public Users(String first_name, String last_name, String email_address){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
    }

    public Users(){

    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public String getEmail_address(){
        return email_address;
    }
}
