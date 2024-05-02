package Flights_Management_System;

import java.util.List;

public class Customer extends Person{
    private String name;
    private String email;
    private int phone;
    private int personalID;


    // create
    public Customer(String name, String email, int phone, int personalID) {
        super(name, email, phone, personalID);
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Personal ID: " + getPersonalID();
    }

}
